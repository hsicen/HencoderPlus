package com.hsicen.a31_generics.shop;

/**
 * 作者：hsicen  2020/2/23 12:58
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
public interface Shop<T> {

    T buy();

    float refund(T item);
}
