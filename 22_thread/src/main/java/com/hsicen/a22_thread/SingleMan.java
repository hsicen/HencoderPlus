package com.hsicen.a22_thread;

/**
 * <p>作者：Hsicen  2019/9/17 8:23
 * <p>邮箱：codinghuang@163.com
 * <p>作用：
 * <p>描述：HencoderPlus
 */
public class SingleMan {
    //volatile在赋值过程过程中不能访问对象
    // 轻量级的synchronized  保证数据的同步性
    // 使用于引用类型和基本类型的赋值
    private static volatile SingleMan sInstance;

    /**
     * 双检查机制单例模式
     *
     * @return 单例类
     */
    public static SingleMan getInstance() {
        if (null == sInstance) {
            synchronized (SingleMan.class) {
                if (null == sInstance) {
                    sInstance = new SingleMan();
                }
            }
        }

        return sInstance;
    }

    private SingleMan() {
    }
}
