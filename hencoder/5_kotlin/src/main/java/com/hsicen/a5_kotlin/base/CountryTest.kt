package com.hsicen.a5_kotlin.base

/**
 * 作者：hsicen  1/27/21 9:18 PM
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：国家测试
 */
object CountryTest {

  /*** 根据filter函数筛选指定的国家*/
  fun filterCountry(
    sourceCountry: List<Country>,
    filter: (Country) -> Boolean
  ): List<Country> {
    val filterCountry = mutableListOf<Country>()

    sourceCountry.forEach {
      if (filter.invoke(it)) {
        filterCountry.add(it)
      }
    }

    return filterCountry
  }


  //左侧是fun，必须通过invoke调用
  fun foo(x: Int) = { ->
    println(x)
  }


  fun <A, B> Array<A>.correspond(that: Array<B>, p: (A, B) -> Boolean): Boolean {
    val i = this.iterator()
    val j = that.iterator()

    while (i.hasNext() && j.hasNext()) {
      if (!p(i.next(), j.next())) {
        return false
      }
    }

    return !i.hasNext() && !j.hasNext()
  }
}

fun main() {
  val data = mutableListOf<Country>()
  data.add(Country("中国", 88888))
  data.add(Country("美国", 66666))
  data.add(Country("日本", 55555))
  data.add(Country("韩国", 22222))
  data.add(Country("新加坡", 33333))

  val result = CountryTest.filterCountry(data) {
    it.name.contains("国")
  }

  println(result.toString())

  val sum = result.let {
    "Hello" + 12
    888
  }

  val sum2 = with(12) {
    123
  }

  println(sum)
  println(sum2)


  listOf(1, 2, 3, 4).forEach { CountryTest.foo(it).invoke() }


  var mCountry: Country? = Country("China", 232)
  mCountry?.apply {
    mCountry.name
    mCountry.population
  }

  mCountry?.let {
    mCountry.name
    mCountry.population
  }

}