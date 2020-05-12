package com.hsicen.a35_jvm;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 作者：hsicen  2020/5/12 17:38
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：泛型测试
 */
public class ReflectTest {

    //获取泛型类信息
    @Test
    public void getGenericClass() {
        //方式一
        Class<Son> sonClass = Son.class;
        String cName = sonClass.getName();
        String canonicalName = sonClass.getCanonicalName();
        String simpleName = sonClass.getSimpleName();


        System.out.println(cName);
        System.out.println(canonicalName);
        System.out.println(simpleName);

        try {
            Son son = sonClass.newInstance();
            String weapon = son.getWeapon();
            System.out.println(weapon);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void change() {
        try {
            Class<?> clzz = Class.forName("com.hsicen.a35_jvm.Son");
            Object son = clzz.newInstance();
            Field age = clzz.getDeclaredField("age");
            age.setAccessible(true);


            Method printAge = clzz.getDeclaredMethod("printAge");
            printAge.setAccessible(true);
            printAge.invoke(son);

            age.set(son, 44);
            printAge.invoke(son);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


}
