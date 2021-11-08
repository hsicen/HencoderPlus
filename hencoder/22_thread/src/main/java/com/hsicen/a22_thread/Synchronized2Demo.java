package com.hsicen.a22_thread;

/**
 * <p>作者：Hsicen  2019/9/16 19:15
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：HencoderPlus
 */
public class Synchronized2Demo implements TestDemo {

    private int x = 0;

    private synchronized void count() {
        x++;
    }

    @Override
    public void runTest() {
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 1_000_000; i++) {
                    count();
                }

                System.out.println("final x from 1: " + x);
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 1_000_000; i++) {
                    count();
                }

                System.out.println("final x from 2: " + x);
            }
        }.start();
    }
}
