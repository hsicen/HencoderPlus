package com.hsicen.a22_thread;

/**
 * <p>作者：Hsicen  2019/9/16 19:15
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：HencoderPlus
 */
public class Synchronized3Demo implements TestDemo {

    private int x = 0;
    private int y = 0;
    private String name;

    private void count(int newValue) {
        x = newValue;
        y = newValue;
    }

    private void setName(String name) {
        this.name = name;
    }

    @Override
    public void runTest() {

    }
}
