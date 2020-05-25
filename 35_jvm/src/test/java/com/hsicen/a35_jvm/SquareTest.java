package com.hsicen.a35_jvm;

import org.junit.Test;

import java.util.ArrayList;

/**
 * 作者：hsicen  2020/5/12 22:52
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：HencoderPlus
 */
public class SquareTest {

    @Test
    public void test() {
        ArrayList<ArrayList<Integer>> data = new ArrayList<>();
        ArrayList<Integer> samp1 = new ArrayList<>();
        samp1.add(81);
        samp1.add(4);
        data.add(samp1);

        ArrayList<Integer> samp2 = new ArrayList<>();
        samp2.add(2);
        samp2.add(2);
        data.add(samp2);

        ArrayList<Double> result = test(data);
        for (Double aFloat : result) {
            System.out.println(aFloat);
        }
    }

    ArrayList<Double> test(ArrayList<ArrayList<Integer>> data) {
        ArrayList<Double> temp = new ArrayList<>();
        if (data == null || data.isEmpty()) return temp;

        for (ArrayList<Integer> num : data) {
            double first = num.get(0);
            double sum = first;

            for (int i = 0; i < num.get(1) - 1; i++) {
                first = Math.sqrt(first);
                sum += first;
            }

            temp.add(sum);
        }

        return temp;
    }

}
