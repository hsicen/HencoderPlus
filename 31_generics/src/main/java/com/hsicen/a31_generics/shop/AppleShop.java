package com.hsicen.a31_generics.shop;

import com.hsicen.a31_generics.fruit.Apple;

/**
 * 作者：hsicen  2020/2/23 20:04
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
public class AppleShop implements Shop<Apple> {

    @Override
    public Apple buy() {
        return null;
    }

    @Override
    public float refund(Apple item) {
        return 0;
    }
}
