package com.hsicen.a2_encrypt

/**
 * 作者：hsicen  2020/4/7 13:57
 * 邮箱：codinghuang@163.com
 * 功能：
 * 描述：对称加密、非对称加密、数字签名
 */
class EncryptClass {

  //古典密码学：移位式加密，替换式加密

  //现代密码学：对称加密，非对称加密   可以加密任何二进制数据，非对称加密使得现代密码学有了更广泛的用途：数字签名

  //对称加密
  /**
   * 原理：通信双方使用同一密钥，使用加密算法+密钥来加密数据得到密文，解密时使用加密过程的完全逆过程+密钥来进行解密得到原数据
   * 经典加密算法：DES（56位密钥） AES（128,192,256位密钥）
   * 作用：加密通信，防止信息在不安全网络上被截获后，信息被别人读取或者篡改
   * 破解思路：拿到一组或多组 原文-密文 对，设法找到一个密钥，能够将这些原文加密成密文，能够将密文解密程原文
   * 缺点：密钥泄露，不能在不安全网络上传输密钥，一旦密钥泄露，则加密通信失败
   */

  //非对称加密
  /**
   * 原理：加密双方都有自己的公钥和私钥，通信时使用对方的公钥加密数据得到密文，然后对方得到密文后使用自己的私钥解密得到数据；
   *      在通信过程中只传输公钥，私钥不会在网络上传输
   * 应用：数字签名，原理是公钥和私钥互相可解，利用私钥对数据进行签名
   *      对原数据hash得到摘要，然后使用加密算法和私钥对数据进行签名得到签名后的摘要，将原数据和签名后的摘要传输给对方，
   *      然后接收方对原数据进行hash得到摘要，并使用对方传过来的公钥对签名后的摘要进行解密得到摘要，二者对比，就知道数据有无改动
   * 经典加密算法：RSA（可用于加密和签名） DSA（仅用于签名，速度快）
   * 优缺点：可以在不安全网络上传输密钥，但计算复杂，性能对比对称加密差很多
   * 破解思路：得到多组 原文-密文 对，找到一个正确的私钥，可以解密所有经过公钥加密的密文
   */

  /** Base64
   * 类型：数据编码方式(数据编码算法)
   * 目的：将任意二进制数据转换为由64个字符组成的 ASCII 字符串，以便在只支持文本传输或存储的系统中安全地表示这些数据，
   *      扩充了二进制数据的传输和存储途径
   * 输出字符集：A-Z, a-z, 0-9, +, /，并用 = 作为填充字符
   * 编码单位：每 3 个字节的二进制数据被编码为 4 个 Base64 字符
   *
   * 不是加密：Base64 可以很容易被解码还原成原始数据，不具备保密性
   * 数据膨胀：Base64 编码后的数据体积比原始数据大约 33%
   */

  //序列化
  /**
   * 原理：将内存中的 数据对象 的状态转换为可以存储或传输的格式（如字节序列、JSON、XML 等）的过程
   *      对象在内存中的存储是散乱的(存放在不同的内存区域，由引用进行连接)
   * 作用：序列化可以把内存中的对象转换成一个字节序列，从而使用byte[] 等形式进行本地存储和网络传输，在需要的时候重新组装(反序列化)来使用
   * 目的：让内存中的对象可以被存储和传输
   * 常见的序列化方式：Serializable，Parcelable，JSON，XML，Protobuf
   */

  //Hash(SHA1，SHA2，SHA3，MD5)
  /**
   * 定义：是一种将任意长度的数据映射为固定长度字符串（称为 摘要 或 指纹）的算法。
   *      这个过程是单向的，即无法从摘要还原原始数据
   * 作用：从数据中提取出摘要信息，主要用途是数字指纹，验证数据的完整性
   * 用途：Java中 hashCode() 用来验证唯一性，重写equals()时也要重写hashCode()，保证判断相等的元素一致性
   *      因为 Hash 集合依赖 hashCode() 实现快速查找，所以重写hashCode()时也要重写equals()，保证判断相等的元素一致性
   *
   *      验证数据的完整性
   *      快速查找(HashMap)
   *      隐私保护(选择暴露数据的hash值)
   */
}
