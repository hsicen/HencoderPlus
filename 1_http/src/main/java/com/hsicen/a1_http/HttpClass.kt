package com.hsicen.a1_http

/**
 * 作者：hsicen  2020/4/7 10:41
 * 邮箱：codinghuang@163.com
 * 功能：
 * 描述：Http的原理和工作机制
 */
class HttpClass {

    //Hype Text Transfer Protocol

    //URL  协议类型://服务器地址:端口号/路径  http://www.baidu.com/search?content="Hello world"
    //HTTP端口号为80     HTTPS端口号为443

    //请求报文
    /**
     * 请求行： GET  /search  HTTP/1.1
     * Header: Host: www.baidu.com
     *         Content-Type: text/plain
     *         Content-Length: 21
     *
     * body body body body
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

    //请求方法
    /**
     * GET：   获取资源，无body，参数拼接在path后面  幂等操作
     * POST：  增加或修改资源，有body，参数在body里
     * PUT：   修改资源，有body，参数写在body里  幂等性操作
     * DELETE: 删除资源，无body，参数拼接在body后面  幂等性操作
     * HEAD：  和GET相同，但是响应中没有body
     */

    //状态码：对响应结果做出类型化描述
    /**
     * 1xx： 临时性消息（100：继续发送；101：正在切换协议）
     * 2xx： 成功（200：成功；201：创建成功）
     * 3xx： 重定向（301：永久移动；302：暂时移动；304：内容未改变）
     * 4xx： 客户端错误（400：客户端请求错误；401：认证失败；403：被禁止访问；404：内容未找到）
     * 5xx： 服务器错误（500：服务器内部错误）
     */

    //常见Header
    /**
     * Host： 目标主机，不是用于寻址，是用于在目标服务器上定位子服务器的
     * Content-Type：指定body的类型，主要有四种
     *      text/html：body返回html文本
     *      application/x-www.form-urlencode：提交表单  @FormURLEncode  @Field
     *      multipart/form-data：二进制文件表单提交  利用boundary分割参数   @Multipart  @Part RequestBody.create()
     *      application/json：body提交json内容  @Body 类对象
     *      image/jpeg：提交二进制内容   @Body  RequestBody.create()
     *
     * Content-Length： 23         body长度
     * Transfer-Encoding：chunked  分块传输，快速响应(服务器压力大时使用该策略)
     * Location：xxxxx   重定向地址
     * User-Agent：xxx   用户代理，区分是谁进行的访问，是什么平台，软件版本等相关信息
     *
     * 断点续传和多线程下载相关
     * Accept-Range: 65534  响应报文中出现，表示可以按字节范围来获取数据
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
