package com.android.hsicen.hiltsample.builder

/**
 * 作者：hsicen  2020/8/16 21:40
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
class Book(var id: Int, name: String, price: Float) {

    class Builder(var id: Int = 1, var name: String = "", var price: Float = 0f) {

        fun id(newId: Int) = run {
            id = newId
            this
        }

        fun name(newName: String) = run {
            name = newName
            this
        }

        fun price(newPrice: Float) = run {
            price = newPrice
            this
        }

        fun build() = Book(id, name, price)
    }
}