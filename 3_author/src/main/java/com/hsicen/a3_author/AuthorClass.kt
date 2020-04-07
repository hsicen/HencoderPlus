package com.hsicen.a3_author

/**
 * 作者：hsicen  2020/4/7 15:21
 * 邮箱：codinghuang@163.com
 * 功能：
 * 描述：Cookie、Authorization、OAuth2
 */
class AuthorClass {
    //登录和授权：登录是确认你是你的过程，授权是授予某些权限；实际上登录过程中实质上也是授予了一定权限

    //Header：Cookie授权
    /**
     * 起源：购物车
     * 工作机制：服务器需要客户端保存内容时，返回 Set-Cookie header，客户端就会保存Set-Cookie中的内容
     *         客户端保存的Cookie数据，会在每次请求服务器时，放在Cookie header中
     *         客户端保存的Cookie是根据host来分类的
     *         客户端保存的Cookie有时效，没有时效的Cookie为Session Cookie，会在浏览器关闭时删除，服务器也可以主动删除客户端的Cookie
     * 作用：会话管理（登录状态，购物车）
     *      个性化（用户偏好，主题）
     *      Tracking（分析用户行为）
     */

    //Header： Authorization
    /**
     * 分类：Basic 和 Bearer
     * Basic：Authorization: Basic<username:password(base64ed)>
     *
     * Bearer: Authorization:Bearer<bearer token>
     * bearer token获取方式：通过OAuth2的授权流程
     *
     * OAuth2授权流程：
     *     1.第三方网站向授权方网站申请第三方授权合作，拿到client id和client secret
     *     2.用户在使用第三方网站时，点击通过xx(如微信，QQ)按钮登录，第三方网站跳转授权方网站，并传入client id作为自己的身份标识
     *     3.授权方网站根据client id，将第三方网站的信息和第三方网站需要的用户权限展示给用户，并询问用户是否同意授权
     *     4.用户点击[同意授权]按钮后，授权方网站将页面跳转回第三方网站，并传入Authorization Code作为用户认可的凭证
     *     5.第三方网站将Authorization Code发送回自己的服务器
     *     6.服务器将Authorization Code和自己的client secret一并发送给授权方的服务器，授权方服务器在验证通过后，返回access token，OAuth流程结束
     *     7.在上面里程结束后，第三方网站服务器就可以使用access token作为用户授权的令牌，向授权方网站发送请求来获取用户的信息或操作用户的账户
     *
     * 为什么OAuth要引入Authorization Code：为了安全
     *
     * 微信登录OAuth2流程：
     *     1.第三方App向腾讯申请合作，拿到client id和client secret
     *     2.用户在使用App时，点击通过微信登录，App将使用微信SDK跳转微信，并传入client id作为自己的身份标识
     *     3.微信通过和服务器交互，拿到第三方App的信息，并限制在界面中，然后询问用户是否同意授权该App使用微信登录
     *     4.用户点击同意微信登录后，微信和自己的服务器交互，得到许可后，跳转回第三方App，并传入Authorization Code作为用户认可的凭证
     *     5.第三方App调用自己服务器 微信登录Api，并传入Authorization Code，等待服务器的响应
     *     6.服务器在收到登录请求后，拿到Authorization Code和client secret向微信授权接口发送请求，微信在验证通过后，返回access token
     *     7.服务器在拿到access token后，请求微信SDK获取用户信息的接口，微信在验证通过后，返回用户的信息
     *     8.服务器在收到用户信息后，在自己的数据库中为用户创建一个账户，并将从微信拿到的用户信息填入自己的数据库，并将创建的用户id和微信id关联
     *     9.用户创建完成后，服务器响应客户端发送的请求，传回刚创建好的用户信息
     *     10.客户端收到服务器的响应，用户登录成功
     *
     * 在自己App中使用bearer token：有的 App 会在 Api 的设计中，将登录和授权设计成类似 OAuth2 的过程，
     *      但简化掉 Authorization code 概念。即：登录接⼝请求成功时，会返回 access token，
     *      然后客户端在之后的请求中，就可以使⽤这个 access token 来当做bearer token 进⾏用户操作了。
     *
     * Refresh token：
     *      ⽤法：access token有失效时间，在它失效后，调用refresh token接口，
     *           传⼊refresh_token 来获取新的access token
     *      目的：安全
     */

}
