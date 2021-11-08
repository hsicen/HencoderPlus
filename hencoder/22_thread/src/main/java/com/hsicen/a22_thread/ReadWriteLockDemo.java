package com.hsicen.a22_thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>作者：Hsicen  2019/9/17 8:40
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：读写锁
 */
public class ReadWriteLockDemo implements TestDemo {

    private final Lock lock = new ReentrantLock();
    private int num = 0;

    public void count() {
        lock.lock();

        try {
            num++;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void runTest() {

    }
}
