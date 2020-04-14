package com.hsicen.a22_thread_interaction;

/**
 * 作者：hsicen  2020/4/14 9:28
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：Wait和Notify/NotifyAll
 */
public class WaitNotify {

    private String mStr;

    public static void main(String[] args) {
        WaitNotify waitNotify = new WaitNotify();
        waitNotify.testWaitNotify();
    }

    private synchronized void initStr() {
        mStr = "Hello World !!!";
        notifyAll();
    }

    private synchronized void printStr() {
        if (mStr == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("The String is " + mStr);
    }


    private void testWaitNotify() {

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                printStr();
            }
        };

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                initStr();
            }
        };

        thread.start();
        thread2.start();
    }
}
