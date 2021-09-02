package com.hsicen.a35_jvm;

/**
 * 作者：hsicen  2020/5/12 17:34
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
public class Son {
    String name = "唐舞桐";
    private final int age = 23;

    int getMoney() {

        return 0;
    }

    public static int add(int k) {
        int i = 1;
        int j = 2;

        return i + j + k;
    }

    private void printAge() {
        System.out.println("私有方法：年龄" + age);
    }
}
