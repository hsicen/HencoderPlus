package com.hsicen.hellocompose.model

import android.app.Activity
import android.content.Context
import android.util.Log
import com.hsicen.hellocompose.R
import com.microsoft.graph.authentication.BaseAuthenticationProvider
import com.microsoft.graph.authentication.IAuthenticationProvider
import com.microsoft.graph.requests.GraphServiceClient
import com.microsoft.identity.client.*
import com.microsoft.identity.client.exception.MsalException
import java.net.URL
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

  companion object {

    @get:Synchronized
    val instance = GraphHelper()
  }
}