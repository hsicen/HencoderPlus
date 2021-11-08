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

    private final Object monitor1 = new Object();
    private final Object monitor2 = new Object();

    // Create a Virtual Monitor
    private void count(int newValue) {
        synchronized (monitor1) {
            x = newValue;
            y = newValue;
        }
    }

    private void setName(String name) {
        synchronized (monitor2) {
            this.name = name;
        }
    }

    @Override
    public void runTest() {

    }
}
