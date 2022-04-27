package com.hsicen.hellocompose.model

import android.app.Activity
import android.content.Context
import android.util.Log
import com.hsicen.hellocompose.R
import com.microsoft.graph.authentication.BaseAuthenticationProvider
import com.microsoft.graph.authentication.IAuthenticationProvider
import com.microsoft.graph.models.Event
import com.microsoft.graph.options.HeaderOption
import com.microsoft.graph.options.Option
import com.microsoft.graph.options.QueryOption
import com.microsoft.graph.requests.EventCollectionPage
import com.microsoft.graph.requests.GraphServiceClient
import com.microsoft.identity.client.*
import com.microsoft.identity.client.exception.MsalException
import java.net.URL
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.concurrent.CompletableFuture
import javax.annotation.Nonnull


/**
 * @author: hsicen
 * @date: 2022/4/27 15:20
 * @email: codinghuang@163.com
 * description: office365
 */

interface IAuthenticationHelperCreatedListener {
  fun onCreated(authHelper: AuthenticationHelper?)
  fun onError(exception: MsalException?)
}

class AuthenticationHelper private constructor
  (ctx: Context, listener: IAuthenticationHelperCreatedListener) : BaseAuthenticationProvider() {
  private var mPCA: ISingleAccountPublicClientApplication? = null
  private val mScopes = arrayOf("User.Read", "MailboxSettings.Read", "Calendars.ReadWrite")

  init {
    PublicClientApplication.createSingleAccountPublicClientApplication(ctx, R.raw.msal_config,
      object : IPublicClientApplication.ISingleAccountApplicationCreatedListener {
        override fun onCreated(application: ISingleAccountPublicClientApplication?) {
          mPCA = application
          listener.onCreated(INSTANCE)
        }

        override fun onError(exception: MsalException?) {
          Log.e("AUTHHELPER", "Error creating MSAL application", exception)
          listener.onError(exception)
        }
      })
  }

  fun acquireTokenInteractively(activity: Activity): CompletableFuture<IAuthenticationResult> {
    val future: CompletableFuture<IAuthenticationResult> = CompletableFuture()
    mPCA?.signIn(activity, null, mScopes, getAuthenticationCallback(future))
    return future
  }

  fun acquireTokenSilently(): CompletableFuture<IAuthenticationResult> {
    // Get the authority from MSAL config
    val authority = mPCA?.configuration?.defaultAuthority?.authorityURL?.toString() ?: ""
    val future: CompletableFuture<IAuthenticationResult> = CompletableFuture()
    mPCA?.acquireTokenSilentAsync(mScopes, authority, getAuthenticationCallback(future))
    return future
  }

  fun signOut() {
    mPCA?.signOut(object : ISingleAccountPublicClientApplication.SignOutCallback {
      override fun onSignOut() {
        Log.d("AUTHHELPER", "Signed out")
      }

      override fun onError(exception: MsalException) {
        Log.d("AUTHHELPER", "MSAL error signing out", exception)
      }
    })
  }

  private fun getAuthenticationCallback(
    future: CompletableFuture<IAuthenticationResult>
  ): AuthenticationCallback {
    return object : AuthenticationCallback {
      override fun onCancel() {
        future.cancel(true)
      }

      override fun onSuccess(authenticationResult: IAuthenticationResult?) {
        future.complete(authenticationResult)
      }

      override fun onError(exception: MsalException?) {
        future.completeExceptionally(exception)
      }
    }
  }

  @Nonnull
  override fun getAuthorizationTokenAsync(requestUrl: URL): CompletableFuture<String> {
    return if (shouldAuthenticateRequestWithUrl(requestUrl)) {
      acquireTokenSilently()
        .thenApply { result -> result.accessToken }
    } else CompletableFuture.completedFuture(null)
  }

  companion object {
    private var INSTANCE: AuthenticationHelper? = null

    @Synchronized
    fun getInstance(ctx: Context): CompletableFuture<AuthenticationHelper> {
      return if (INSTANCE == null) {
        val future: CompletableFuture<AuthenticationHelper> = CompletableFuture()
        INSTANCE = AuthenticationHelper(ctx, object : IAuthenticationHelperCreatedListener {
          override fun onCreated(authHelper: AuthenticationHelper?) {
            future.complete(authHelper)
          }

          override fun onError(exception: MsalException?) {
            future.completeExceptionally(exception)
          }
        })
        future
      } else {
        CompletableFuture.completedFuture(INSTANCE)
      }
    }

    @get:Synchronized
    val instance: AuthenticationHelper
      get() = INSTANCE ?: checkNotNull(INSTANCE) { "AuthenticationHelper has not been initialized from MainActivity" }
  }
}

class GraphHelper() : IAuthenticationProvider {
  private val mClient by lazy {
    val authProvider = AuthenticationHelper.instance
    GraphServiceClient.builder()
      .authenticationProvider(authProvider)
      .buildClient()
  }

  override fun getAuthorizationTokenAsync(requestUrl: URL): CompletableFuture<String> {
    return CompletableFuture<String>()
  }

  fun getUser() = mClient.me()
    .buildRequest()
    .select("displayName,mail,mailboxSettings,userPrincipalName")
    .async

  fun getCalendarEvent(eventStart: ZonedDateTime, eventEnd: ZonedDateTime, timeZone: String): CompletableFuture<List<Event>>? =
    mClient.me()
      .calendarView()
      .buildRequest(LinkedList<Option>().apply {
        add(QueryOption("startDateTime", eventStart.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)))
        add(QueryOption("endDateTime", eventEnd.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)))
        add(HeaderOption("Prefer", "outlook.timezone=\"$timeZone\""))
      })
      ?.select("subject,organizer,start,end")
      ?.orderBy("start/dateTime")
      ?.top(5)
      ?.async
      ?.thenCompose {
        processPage(it, LinkedList<Event>(), LinkedList<Option>().apply {
          add(HeaderOption("Prefer", "outlook.timezone=\"$timeZone\""))
        })
      }

  private fun processPage(
    currentPage: EventCollectionPage,
    eventList: LinkedList<Event>,
    options: LinkedList<Option>
  ): CompletableFuture<List<Event>>? {
    eventList.addAll(currentPage.currentPage)
    val nextPage = currentPage.nextPage

    return nextPage?.buildRequest(options)
      ?.async
      ?.thenCompose { processPage(it, eventList, options) }
      ?: CompletableFuture.completedFuture(eventList)
  }

  fun serializeObject(obj: Any) = mClient.serializer?.serializeObject(obj) ?: ""

  companion object {

    @get:Synchronized
    val instance = GraphHelper()
  }
}

object GraphToIana {
  private val timeZoneIdMap = HashMap<String, String>()

  init {
    timeZoneIdMap["Dateline Standard Time"] = "Etc/GMT+12"
    timeZoneIdMap["UTC-11"] = "Etc/GMT+11"
    timeZoneIdMap["Aleutian Standard Time"] = "America/Adak"
    timeZoneIdMap["Hawaiian Standard Time"] = "Pacific/Honolulu"
    timeZoneIdMap["Marquesas Standard Time"] = "Pacific/Marquesas"
    timeZoneIdMap["Alaskan Standard Time"] = "America/Anchorage"
    timeZoneIdMap["UTC-09"] = "Etc/GMT+9"
    timeZoneIdMap["Pacific Standard Time (Mexico)"] = "America/Tijuana"
    timeZoneIdMap["UTC-08"] = "Etc/GMT+8"
    timeZoneIdMap["Pacific Standard Time"] = "America/Los_Angeles"
    timeZoneIdMap["US Mountain Standard Time"] = "America/Phoenix"
    timeZoneIdMap["Mountain Standard Time (Mexico)"] = "America/Chihuahua"
    timeZoneIdMap["Mountain Standard Time"] = "America/Denver"
    timeZoneIdMap["Central America Standard Time"] = "America/Guatemala"
    timeZoneIdMap["Central Standard Time"] = "America/Chicago"
    timeZoneIdMap["Easter Island Standard Time"] = "Pacific/Easter"
    timeZoneIdMap["Central Standard Time (Mexico)"] = "America/Mexico_City"
    timeZoneIdMap["Canada Central Standard Time"] = "America/Regina"
    timeZoneIdMap["SA Pacific Standard Time"] = "America/Bogota"
    timeZoneIdMap["Eastern Standard Time (Mexico)"] = "America/Cancun"
    timeZoneIdMap["Eastern Standard Time"] = "America/New_York"
    timeZoneIdMap["Haiti Standard Time"] = "America/Port-au-Prince"
    timeZoneIdMap["Cuba Standard Time"] = "America/Havana"
    timeZoneIdMap["US Eastern Standard Time"] = "America/Indianapolis"
    timeZoneIdMap["Turks And Caicos Standard Time"] = "America/Grand_Turk"
    timeZoneIdMap["Paraguay Standard Time"] = "America/Asuncion"
    timeZoneIdMap["Atlantic Standard Time"] = "America/Halifax"
    timeZoneIdMap["Venezuela Standard Time"] = "America/Caracas"
    timeZoneIdMap["Central Brazilian Standard Time"] = "America/Cuiaba"
    timeZoneIdMap["SA Western Standard Time"] = "America/La_Paz"
    timeZoneIdMap["Pacific SA Standard Time"] = "America/Santiago"
    timeZoneIdMap["Newfoundland Standard Time"] = "America/St_Johns"
    timeZoneIdMap["Tocantins Standard Time"] = "America/Araguaina"
    timeZoneIdMap["E. South America Standard Time"] = "America/Sao_Paulo"
    timeZoneIdMap["SA Eastern Standard Time"] = "America/Cayenne"
    timeZoneIdMap["Argentina Standard Time"] = "America/Buenos_Aires"
    timeZoneIdMap["Greenland Standard Time"] = "America/Godthab"
    timeZoneIdMap["Montevideo Standard Time"] = "America/Montevideo"
    timeZoneIdMap["Magallanes Standard Time"] = "America/Punta_Arenas"
    timeZoneIdMap["Saint Pierre Standard Time"] = "America/Miquelon"
    timeZoneIdMap["Bahia Standard Time"] = "America/Bahia"
    timeZoneIdMap["UTC-02"] = "Etc/GMT+2"
    timeZoneIdMap["Azores Standard Time"] = "Atlantic/Azores"
    timeZoneIdMap["Cape Verde Standard Time"] = "Atlantic/Cape_Verde"
    timeZoneIdMap["UTC"] = "Etc/GMT"
    timeZoneIdMap["GMT Standard Time"] = "Europe/London"
    timeZoneIdMap["Greenwich Standard Time"] = "Atlantic/Reykjavik"
    timeZoneIdMap["Sao Tome Standard Time"] = "Africa/Sao_Tome"
    timeZoneIdMap["Morocco Standard Time"] = "Africa/Casablanca"
    timeZoneIdMap["W. Europe Standard Time"] = "Europe/Berlin"
    timeZoneIdMap["Central Europe Standard Time"] = "Europe/Budapest"
    timeZoneIdMap["Romance Standard Time"] = "Europe/Paris"
    timeZoneIdMap["Central European Standard Time"] = "Europe/Warsaw"
    timeZoneIdMap["W. Central Africa Standard Time"] = "Africa/Lagos"
    timeZoneIdMap["Jordan Standard Time"] = "Asia/Amman"
    timeZoneIdMap["GTB Standard Time"] = "Europe/Bucharest"
    timeZoneIdMap["Middle East Standard Time"] = "Asia/Beirut"
    timeZoneIdMap["Egypt Standard Time"] = "Africa/Cairo"
    timeZoneIdMap["E. Europe Standard Time"] = "Europe/Chisinau"
    timeZoneIdMap["Syria Standard Time"] = "Asia/Damascus"
    timeZoneIdMap["West Bank Standard Time"] = "Asia/Hebron"
    timeZoneIdMap["South Africa Standard Time"] = "Africa/Johannesburg"
    timeZoneIdMap["FLE Standard Time"] = "Europe/Kiev"
    timeZoneIdMap["Israel Standard Time"] = "Asia/Jerusalem"
    timeZoneIdMap["Kaliningrad Standard Time"] = "Europe/Kaliningrad"
    timeZoneIdMap["Sudan Standard Time"] = "Africa/Khartoum"
    timeZoneIdMap["Libya Standard Time"] = "Africa/Tripoli"
    timeZoneIdMap["Namibia Standard Time"] = "Africa/Windhoek"
    timeZoneIdMap["Arabic Standard Time"] = "Asia/Baghdad"
    timeZoneIdMap["Turkey Standard Time"] = "Europe/Istanbul"
    timeZoneIdMap["Arab Standard Time"] = "Asia/Riyadh"
    timeZoneIdMap["Belarus Standard Time"] = "Europe/Minsk"
    timeZoneIdMap["Russian Standard Time"] = "Europe/Moscow"
    timeZoneIdMap["E. Africa Standard Time"] = "Africa/Nairobi"
    timeZoneIdMap["Iran Standard Time"] = "Asia/Tehran"
    timeZoneIdMap["Arabian Standard Time"] = "Asia/Dubai"
    timeZoneIdMap["Astrakhan Standard Time"] = "Europe/Astrakhan"
    timeZoneIdMap["Azerbaijan Standard Time"] = "Asia/Baku"
    timeZoneIdMap["Russia Time Zone 3"] = "Europe/Samara"
    timeZoneIdMap["Mauritius Standard Time"] = "Indian/Mauritius"
    timeZoneIdMap["Saratov Standard Time"] = "Europe/Saratov"
    timeZoneIdMap["Georgian Standard Time"] = "Asia/Tbilisi"
    timeZoneIdMap["Volgograd Standard Time"] = "Europe/Volgograd"
    timeZoneIdMap["Caucasus Standard Time"] = "Asia/Yerevan"
    timeZoneIdMap["Afghanistan Standard Time"] = "Asia/Kabul"
    timeZoneIdMap["West Asia Standard Time"] = "Asia/Tashkent"
    timeZoneIdMap["Ekaterinburg Standard Time"] = "Asia/Yekaterinburg"
    timeZoneIdMap["Pakistan Standard Time"] = "Asia/Karachi"
    timeZoneIdMap["Qyzylorda Standard Time"] = "Asia/Qyzylorda"
    timeZoneIdMap["India Standard Time"] = "Asia/Calcutta"
    timeZoneIdMap["Sri Lanka Standard Time"] = "Asia/Colombo"
    timeZoneIdMap["Nepal Standard Time"] = "Asia/Katmandu"
    timeZoneIdMap["Central Asia Standard Time"] = "Asia/Almaty"
    timeZoneIdMap["Bangladesh Standard Time"] = "Asia/Dhaka"
    timeZoneIdMap["Omsk Standard Time"] = "Asia/Omsk"
    timeZoneIdMap["Myanmar Standard Time"] = "Asia/Rangoon"
    timeZoneIdMap["SE Asia Standard Time"] = "Asia/Bangkok"
    timeZoneIdMap["Altai Standard Time"] = "Asia/Barnaul"
    timeZoneIdMap["W. Mongolia Standard Time"] = "Asia/Hovd"
    timeZoneIdMap["North Asia Standard Time"] = "Asia/Krasnoyarsk"
    timeZoneIdMap["N. Central Asia Standard Time"] = "Asia/Novosibirsk"
    timeZoneIdMap["Tomsk Standard Time"] = "Asia/Tomsk"
    timeZoneIdMap["China Standard Time"] = "Asia/Shanghai"
    timeZoneIdMap["North Asia East Standard Time"] = "Asia/Irkutsk"
    timeZoneIdMap["Singapore Standard Time"] = "Asia/Singapore"
    timeZoneIdMap["W. Australia Standard Time"] = "Australia/Perth"
    timeZoneIdMap["Taipei Standard Time"] = "Asia/Taipei"
    timeZoneIdMap["Ulaanbaatar Standard Time"] = "Asia/Ulaanbaatar"
    timeZoneIdMap["Aus Central W. Standard Time"] = "Australia/Eucla"
    timeZoneIdMap["Transbaikal Standard Time"] = "Asia/Chita"
    timeZoneIdMap["Tokyo Standard Time"] = "Asia/Tokyo"
    timeZoneIdMap["North Korea Standard Time"] = "Asia/Pyongyang"
    timeZoneIdMap["Korea Standard Time"] = "Asia/Seoul"
    timeZoneIdMap["Yakutsk Standard Time"] = "Asia/Yakutsk"
    timeZoneIdMap["Cen. Australia Standard Time"] = "Australia/Adelaide"
    timeZoneIdMap["AUS Central Standard Time"] = "Australia/Darwin"
    timeZoneIdMap["E. Australia Standard Time"] = "Australia/Brisbane"
    timeZoneIdMap["AUS Eastern Standard Time"] = "Australia/Sydney"
    timeZoneIdMap["West Pacific Standard Time"] = "Pacific/Port_Moresby"
    timeZoneIdMap["Tasmania Standard Time"] = "Australia/Hobart"
    timeZoneIdMap["Vladivostok Standard Time"] = "Asia/Vladivostok"
    timeZoneIdMap["Lord Howe Standard Time"] = "Australia/Lord_Howe"
    timeZoneIdMap["Bougainville Standard Time"] = "Pacific/Bougainville"
    timeZoneIdMap["Russia Time Zone 10"] = "Asia/Srednekolymsk"
    timeZoneIdMap["Magadan Standard Time"] = "Asia/Magadan"
    timeZoneIdMap["Norfolk Standard Time"] = "Pacific/Norfolk"
    timeZoneIdMap["Sakhalin Standard Time"] = "Asia/Sakhalin"
    timeZoneIdMap["Central Pacific Standard Time"] = "Pacific/Guadalcanal"
    timeZoneIdMap["Russia Time Zone 11"] = "Asia/Kamchatka"
    timeZoneIdMap["New Zealand Standard Time"] = "Pacific/Auckland"
    timeZoneIdMap["UTC+12"] = "Etc/GMT-12"
    timeZoneIdMap["Fiji Standard Time"] = "Pacific/Fiji"
    timeZoneIdMap["Chatham Islands Standard Time"] = "Pacific/Chatham"
    timeZoneIdMap["UTC+13"] = "Etc/GMT-13"
    timeZoneIdMap["Tonga Standard Time"] = "Pacific/Tongatapu"
    timeZoneIdMap["Samoa Standard Time"] = "Pacific/Apia"
    timeZoneIdMap["Line Islands Standard Time"] = "Pacific/Kiritimati"
  }

  fun getIanaFromWindows(windowsTimeZone: String): String {
    val iana = timeZoneIdMap[windowsTimeZone]
    return iana ?: windowsTimeZone
  }

  fun getZoneIdFromWindows(windowsTimeZone: String): ZoneId {
    val timeZoneId = getIanaFromWindows(windowsTimeZone)
    return ZoneId.of(timeZoneId)
  }
}