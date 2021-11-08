package com.hsicen.a31_generics.shop;

/**
 * 作者：hsicen  2020/2/23 13:00
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：修理店
 */
public class RepairShop<T> implements Shop<T> {

    @Override
    public T buy() {
        return null;
    }

    @Override
    public float refund(T item) {
        return 0;
    }

}
