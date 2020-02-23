package com.hsicen.a31_generics.food;

/**
 * 作者：hsicen  2020/2/23 21:46
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
public class Eater<T extends Food> {

    void eat(T food) {
        food.eaten();
    }
    
}
