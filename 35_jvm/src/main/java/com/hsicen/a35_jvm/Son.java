package com.hsicen.a35_jvm;

/**
 * 作者：hsicen  2020/5/12 17:34
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
public class Son extends Father {
    String name = "唐舞桐";
    private int age = 23;

    String getWeapon() {
        return weapon;
    }

    int getMoney() {

        return 0;
    }

    private void printAge() {
        System.out.println("私有方法：年龄" + age);
    }
}
