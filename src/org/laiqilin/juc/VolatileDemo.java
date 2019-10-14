package org.laiqilin.juc;

/**
 * @author laiqilin
 * @create 2019-10-14 14:06
 * <p>
 * 单例模式
 * <p>
 * （推荐）DCL双端检查单例模式
 */
public class VolatileDemo {
    //禁止底层指令重排优化 加volatile
    public static volatile VolatileDemo instance = null;

    private VolatileDemo() {
        System.out.println ("私有构造方法.....");
    }

    /**
     * 单例模式
     * 问题：
     * 线程不安全
     * 解决方案：
     * 1. 在方法上加上synchronized关键字
     * 2. DCL双重检查机制
     */

    public static VolatileDemo getInstance() {
        if (instance == null) {
            synchronized (VolatileDemo.class) {
                if (instance == null) {
                    instance = new VolatileDemo ();
                }
            }
        }
        return instance;
    }


    public static void main(String[] args) {
        for (int i = 0; i <= 100; i++) {
            new Thread (() -> {
                VolatileDemo.getInstance ();
            }, String.valueOf (i)).start ();
        }
    }
}
