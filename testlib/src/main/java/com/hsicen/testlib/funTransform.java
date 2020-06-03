package com.hsicen.testlib;

/**
 * 作者：hsicen  2020/4/30 10:16
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
public class funTransform {

    public static void main(String[] args) {

        int init = 10;
        testBaseType(init);
        System.out.println("方法调用后：" + init);
        User user = new User();
    }

    private static void testBaseType(int value) {
        System.out.println("传入值：" + value);
        value = 20;
    }


}
