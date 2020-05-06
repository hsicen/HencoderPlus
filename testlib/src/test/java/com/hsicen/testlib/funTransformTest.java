package com.hsicen.testlib;


import org.junit.Assert;
import org.junit.Test;

/**
 * 作者：hsicen  2020/4/30 10:24
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
public class funTransformTest {

    @Test
    public void testBaseType() {
        User user = new User("初始名字", "男", 18);
        referTypeChange(user);
        System.out.println("方法后打印：" + user.getName());
        Assert.assertEquals(user, user);
    }

    void baseInt(int value) {
        System.out.println("方法前打印：" + value);
        value = 20;
        System.out.println("方法中修改：" + value);
    }

    void baseString(String value) {
        System.out.println("方法前打印：" + value);
        value = "黄思程";
        System.out.println("方法中修改：" + value);
    }

    void baseLong(Long value) {
        System.out.println("方法前打印：" + value);
        value = 99L;
        System.out.println("方法中修改：" + value);
    }

    void referType(User value) {
        System.out.println("方法前打印：" + value.getName());
        value.setName("我是修改后的名字");
        System.out.println("方法中修改：" + value.getName());
    }

    void referTypeChange(User value) {
        User newUser = new User("后面", "女", 18);
        System.out.println("方法前打印：" + value.getName());
        value = newUser;
        value.setName("我是修改后的名字");
        value.setAge(23);
        System.out.println("方法中修改：" + value.getName());
    }

}