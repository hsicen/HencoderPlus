package com.hsicen.a22_thread;

import android.os.SystemClock;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <p>作者：Hsicen  2019/9/16 8:13
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：线程测试
 */
public class Main {

    public static void main(String[] args) {
        //thread();
        //runnable();
        //threadFactory();
        //executor();
        //callable();
        //runSynchronized1Demo();
        runSynchronized2Demo();
        //runSynchronized3Demo();

    }

    /**
     * 使用Thread来启动线程工作
     */
    private static void thread() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();  //执行Runnable方法
                System.out.println("Thread start");
            }
        };

        thread.start();
    }

    /**
     * 使用Runnable来启动线程工作
     */
    private static void runnable() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                System.out.println("Thread with runnable start");
            }
        });

        thread.start();
    }

    /**
     * 使用工厂模式创建线程工作
     */
    private static void threadFactory() {
        ThreadFactory threadFactory = new ThreadFactory() {
            int count = 0;  //线程安全问题

            @Override
            public Thread newThread(Runnable r) {
                count++;
                return new Thread(r, "Thread-" + count);
            }
        };

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " start");
            }
        };

        threadFactory.newThread(runnable).start();
        threadFactory.newThread(runnable).start();
        threadFactory.newThread(runnable).start();
    }

    /**
     * 使用Executor创建线程工作
     * ThreadPoolExecutor 线程池
     * 线程池大小和CPU的核数没有必然的关系
     * <p>
     * newCachedThreadPool 创建Cache线程池
     * newSingleThreadExecutor 创建单个线程的线程池
     * newSingleThreadScheduledExecutor  创建单个线程的定时线程池
     * newScheduledThreadPool 创建定时线程池
     * newFixedThreadPool  创建固定线程的线程池
     */
    private static void executor() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000L);
                    System.out.println("Thread with runnable start - " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(20);
        executorService.execute(runnable);
        executorService.execute(runnable);
        executorService.execute(runnable);

        executorService.shutdown();
        executorService.shutdownNow();
    }


    private static void threadPool() {
        int coreSize = Runtime.getRuntime().availableProcessors();
        LinkedBlockingDeque<Runnable> deque = new LinkedBlockingDeque<>();


        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(coreSize, coreSize, 1, TimeUnit.MINUTES, deque);
        poolExecutor.setThreadFactory(new ThreadFactory() {

            @Override
            public Thread newThread(Runnable r) {


                return null;
            }
        });

    }


    /**
     * 带返回值的线程
     * 会阻塞当前线程,使用while没做完可以做其它事情
     */
    private static void callable() {
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return "Done";
            }
        };

        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(callable);

        AtomicLong count = new AtomicLong(1);
        try {
            while (!future.isDone()) {
                // do sth else
                System.out.println("CPU current is free.");
                count.getAndIncrement();
            }
            System.out.println("count:" + count.get());
            String result = future.get();
            System.out.println("result = " + result);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

    /**
     * 多线程测试
     */
    private static void runSynchronized1Demo() {
        new Synchronized1Demo().runTest();
    }

    private static void runSynchronized2Demo() {
        new Synchronized2Demo().runTest();
    }

    private static void runSynchronized3Demo() {
        new Synchronized3Demo().runTest();
    }

    private static void runReadWriteLockDemo() {

    }
}
