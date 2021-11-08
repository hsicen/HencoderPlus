package com.hsicen.a31_generics;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.hsicen.a31_generics.fruit.Apple;
import com.hsicen.a31_generics.fruit.Banana;
import com.hsicen.a31_generics.fruit.Fruit;

import java.util.ArrayList;

/**
 * 作者：hsicen  2020/2/13 10:56
 * 邮箱：codinghuang@163.com
 * 功能：
 * 描述：泛型讲解
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ArrayList<? extends Fruit> fruits = new ArrayList<Apple>();
        Banana banana = new Banana();

        Apple apple = new Apple();
        Banana banana1 = new Banana();

        ArrayList<Fruit> fruits1 = new ArrayList<>();
        fruits1.add(apple);
        fruits1.add(banana1);

    }
}
