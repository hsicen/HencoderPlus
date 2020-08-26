package com.hsicen.a1_http

/**
 * 作者：hsicen  2020/4/7 10:41
 * 邮箱：codinghuang@163.com
 * 功能：
 * 描述：Http的原理和工作机制
 *
 * 一次配置，终生使用
 */
class HttpClass {

    //定义：Hyper Text Transfer Protocol
    /**
     * Protocol：协议，通信规则，通信方能够简单，顺利的沟通
     *
     * HyperText 扩展文本 HTML文本
     * 用纯文本 传输 带格式的页面
     * 学术界的人需要在网上浏览文章
     * 传输HTML文本的协议
     *
     * 工作方式：
     * 浏览器：联网请求 -> 请求报文拼接 -> 服务器响应 -> 响应报文 -> 数据解析
     * 手机App：联网请求 -> 请求报文拼接 -> 服务器响应 -> 响应报文 -> 数据解析
     * 渲染引擎：显示HTML文本
     */

    //URL  协议类型://服务器地址:端口号/路径  http://www.baidu.com/search?content="Hello world"
    //HTTP端口号为80     HTTPS端口号为443

    //请求报文
    /**
     * 请求行： GET  /search(定位)  HTTP/1.1
     * Header: Host: www.baidu.com
     *         Content-Type: text/plain
     *         Content-Length: 21
     *
     * body body body body (提供给服务器的具体信息)
     * */

    //响应报文
    /**
     * 状态行： HTTP/1.1  200  OK
     * Header: Host: www.baidu.com
     *         Content-Type: text/plain
     *         Content-Length: 21
     *
     * body body body body
     * */

    //请求方法(Request method)
    /**
     * GET：   获取资源，无body，参数拼接在path后面(@Path)  幂等操作
     * POST：  增加或修改资源，有body，参数在body里(@Filed)
     * PUT：   修改资源，有body，参数写在body里(@Path, @Filed)  幂等性操作
     * DELETE: 删除资源，无body，参数拼接在path后面  幂等性操作
     * HEAD：  和GET相同，但是响应中没有body，只有状态行和Header
     */

    //状态码(Response Status Code)：对响应结果做出类型化描述   方便开发调试
    /**
     * 1xx： 临时性消息（100：继续发送 Expect:100-continue； 101：正在切换协议 Upgrade: h2c）
     * 2xx： 成功（200：成功；201：创建成功）
     * 3xx： 重定向（301：永久移动；302：暂时移动；304：内容未改变）
     * 4xx： 客户端错误（400：客户端请求错误；401：认证失败；403：被禁止访问；404：内容未找到）
     * 5xx： 服务器错误（500：服务器内部错误；503：服务器不可用；504：网关超时）
     */

    //常见Header
    /**
     * 作用：HTTP消息的元数据(关于数据的数据)
     *
     * Host： 目标主机，不是用于寻址，是用于在目标服务器上定位子服务器的
     *        发送请求前：主机域名 -> DNS系统 -> IP地址
     *        虚拟主机：由于域名定位出来的IP都是指向大主机的，大主机需要用域名来定位子主机
     *
     * Content-Type：指定body的类型，主要有四种(没有默认值)
     *      text/html：body返回html文本
     *      application/x-www-form-urlencoded：提交普通表单，纯文字表单    @FormURLEncode  @Field(拼接在body中) @Query(拼接在path后面)
     *      multipart/form-data：二进制文件表单提交  利用boundary分割参数   @Multipart      @Part RequestBody.create()
     *      application/json：body提交json内容/返回json数据               @Body(有converter) 类对象   @RequestBody(无converter)
     *      image/jpeg：提交二进制内容(单文件上传/下载)   @Body  RequestBody.create()
     *      application/zip: 单文件上传/下载
     *
     * Content-Length： 23         body长度
     * Transfer-Encoding：chunked  分块传输，快速响应(服务器压力大时使用该策略)
     * Location：xxxxx   重定向地址
     * User-Agent：xxx   用户代理，区分是谁进行的访问，是什么平台，软件版本等相关信息
     *
     * 断点续传和多线程下载相关
     * Accept-Range: 65534   响应报文中出现，表示可以按字节范围来获取数据
     * Range：bytes=xxx-xxx  请求报文中出现，表示获取指定范围数据
     * Content-Range：xxx-xxx/total 响应报文中出现，表示发送的是那一段数据
     *
     * Accept：客户端能接收的数据类型（如text/html）
     * Accept-Charset：客户端能接收的字符集（如UTF-8）
     * Accept-Encoding：客户端能接收的压缩编码类型
     * Content-Encoding：压缩类型
     *
     * Cookie：cookie信息，请求报文中携带
     * set-cookie：告知对方设置cookie
     * Authorization：请求报文中携带 授权信息，分为basic(base64编码)和bearer(token信息)两种
     * Cache：缓存，个性化推荐，中间结点缓存数据，降低从服务器获取数据的频率
     */
}
