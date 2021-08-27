package com.hsicen.a22_thread_interaction;

/**
 * 作者：hsicen  2020/4/14 9:28
 * 邮箱：codinghuang@163.com
 * 作用：
 * 描述：Wait和Notify/NotifyAll
 */
public class WaitNotify {

    private String mStr;
    private boolean globalBol;

    public static void main(String[] args) {
        WaitNotify waitNotify = new WaitNotify();
        waitNotify.testWaitNotify();
        //waitNotify.testVariable();
    }

    private synchronized void initStr() {
        mStr = "Hello World !!!";
        System.out.println("init str: " + Thread.currentThread().getName());
        notifyAll();
        //notify();
    }

    private synchronized void printStr() {
        while (mStr == null) { // 使用while判断
            try {
                System.out.println("before wait: " + Thread.currentThread().getName());
                wait();
                System.out.println("after wait: " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("The String is " + mStr);
    }

    private void testWaitNotify() {
        System.out.println("start test: " + Thread.currentThread().getName());

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("before print: " + Thread.currentThread().getName());
                printStr();
                System.out.println("after print: " + Thread.currentThread().getName());
            }
        };

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(1800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("before init: " + Thread.currentThread().getName());
                initStr();
                System.out.println("after init: " + Thread.currentThread().getName());
            }
        };

        thread.start();
        thread2.start();
    }

    private void testVariable() {
        boolean localBol;

        System.out.println("The value is " + globalBol);
    }
}
