package com.hsicen.kolitn;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>作者：Hsicen  2019/9/23 8:18
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：HencoderPlus
 */
public class JavaTest {
    //泛型支持多态
    TextView textView = new Button(null);

    //Java 集合不支持协变  需要加上<? extends T>
    List<TextView> textViews = new ArrayList<>();
    List<? extends View> views = textViews;

    List<?> objs = new ArrayList<>();
    List<? extends Object> objects = objs;


    public static void main(String[] args) {
        List<?> objs = new ArrayList<>();
        List<? extends Object> objects = new ArrayList<>();

        System.out.println("equals:  " + (objects.equals(objs)));

        checkType(new Animal(), Person.class);
        checkType(new Animal(), Animal.class);
    }

    private static <T> void checkType(Object item, Class<T> type) {
        if (type.isInstance(item)) {
            System.out.println("类型参数正确    " + item + "   " + type);
        } else {
            System.out.println("参数类型错误    " + item + "   " + type);
        }
    }
}
