package com.hsicen.a31_generics.shop;

/**
 * 作者：hsicen  2020/2/23 20:05
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
public interface SimShop<T, S> extends Shop<T> {

    S buySim(String name, String id);

}
