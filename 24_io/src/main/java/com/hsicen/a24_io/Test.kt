package com.hsicen.a24_io

import okio.Buffer
import okio.buffer
import okio.source
import java.io.*
import java.net.InetSocketAddress
import java.net.ServerSocket
import java.nio.ByteBuffer
import java.nio.channels.SelectionKey
import java.nio.channels.Selector
import java.nio.channels.ServerSocketChannel
import java.nio.charset.Charset

/**
 * <p>作者：hsicen  2019/12/12 22:24
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：HencofderPlus
 */
fun main() {

    okio2()
}

/*** 普通网络IO
 *  telnet localhost 8080   (连接本地服务器)
 * */
fun netIo() {
    val serverSocket = ServerSocket(8080)
    val socket = serverSocket.accept()

    val reader = BufferedReader(InputStreamReader(socket.getInputStream()))
    val writer = BufferedWriter(OutputStreamWriter(socket.getOutputStream()))

    var data: String
    while (reader.readLine().also { data = it } != null) {
        data = data.replace("吗", "")
        data = data.replace("?", "!")
        data = data.replace("？", "!")
        writer.write(data)
        writer.write(System.lineSeparator())
        writer.flush()
    }
}


fun copyFile() {
    val fis = FileInputStream("./24_io/hsc.txt")
    val fos = FileOutputStream("./24_io/hscCopy.txt")

    val bytes = ByteArray(1024)
    var len: Int
    while (fis.read(bytes).also { len = it } != -1) {
        fos.write(bytes, 0, len)
    }
}

fun read() {
    val fis = FileInputStream("./24_io/hsc.txt")
    println(fis.read().toChar())
    println(fis.read().toChar())
    println(fis.read())
    println(fis.read())

    fis.close()
}

fun write() {
    val fos = FileOutputStream("./24_io/hsc.txt")
    fos.write('a'.toInt())
    fos.write('b'.toInt())
    fos.write(3)
    fos.write(15)

    fos.close()
}

fun write1() {
    val fos = FileOutputStream("./24_io/hsc.txt")
    val writer = OutputStreamWriter(fos)
    writer.append("你是谁啊？")
    writer.append("你在干嘛？")
    writer.append("这个是追加的！")
    fos.flush()
    writer.flush()

    writer.close()
    fos.close()
}

fun read1() {
    val fis = FileInputStream("./24_io/hsc.txt")
    val reader = InputStreamReader(fis)
    println(reader.readText())

    reader.close()
    fis.close()
}


fun nio1() {
    val raf = RandomAccessFile("./24_io/hsc.txt", "r")
    val channel = raf.channel
    val byteBuffer = ByteBuffer.allocate(1024)
    channel.read(byteBuffer)
    byteBuffer.flip()
    println(Charset.defaultCharset().decode(byteBuffer))
    byteBuffer.clear()
}

/*** 网络交互的阻塞式*/
fun nio2() {
    val serverSocketChannel = ServerSocketChannel.open()
    serverSocketChannel.bind(InetSocketAddress(8080))
    val socketChannel = serverSocketChannel.accept()
    val byteBuffer = ByteBuffer.allocate(1024)

    while (socketChannel.read(byteBuffer) != -1) {
        byteBuffer.flip()
        socketChannel.write(byteBuffer)
        //println("接收到：${Charset.defaultCharset().decode(byteBuffer)}")
        byteBuffer.clear()
    }
}

/*** 网络交互的非阻塞式*/
fun nio3() {

    val serverSocketChannel = ServerSocketChannel.open()
    serverSocketChannel.bind(InetSocketAddress(8080))
    serverSocketChannel.configureBlocking(false)
    val selector = Selector.open()
    serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT)
    val socketChannel = serverSocketChannel.accept()
    val byteBuffer = ByteBuffer.allocate(1024)

    while (socketChannel.read(byteBuffer) != -1) {
        byteBuffer.flip()
        socketChannel.write(byteBuffer)
        byteBuffer.clear()
    }
}

fun okio1() {
    val source = File("./24_io/hsc.txt").source()
    val buffer = Buffer()
    source.read(buffer, 1024)

    println("读取：${buffer.readUtf8()}")
}

fun okio2() {
    val source = File("./24_io/hsc.txt").source().buffer()
    println("读取：${source.readUtf8()}")
}

