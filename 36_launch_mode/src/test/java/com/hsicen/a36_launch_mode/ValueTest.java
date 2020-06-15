package com.hsicen.a36_launch_mode;

/**
 * 作者：hsicen  2020/6/12 16:49
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：引用传递测试
 */
class ValueTest {

    public static void main(String[] args) {
        User user = new User();
        System.out.println("实参地址：" + user);
        testObject(user);
    }

    static void testObject(User user) {
        System.out.println("形参地址：" + user);
        user = new User();
        System.out.println("形参地址：" + user);
    }

}
