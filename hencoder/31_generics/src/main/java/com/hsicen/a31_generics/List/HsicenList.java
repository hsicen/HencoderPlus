package com.hsicen.a31_generics.List;

import java.util.Arrays;

/**
 * 作者：hsicen  2020/2/13 18:25
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：Hencoder Plus
 */
public class HsicenList<T> {
    private Object[] data = new Object[0];

    public T get(int index) {
        return (T) data[index];
    }

    public void add(T item) {
        data = Arrays.copyOf(data, data.length + 1);
        data[data.length - 1] = item;
    }
}
