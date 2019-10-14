package org.laiqilin.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache {
    private volatile Map<String, Object> map = new HashMap<> ();
    //创建读写锁对象
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock ();

    //创建写操作
    public void put(String key, Object value) {
        try {
            //写入锁
            readWriteLock.writeLock ().lock ();
            System.out.println (Thread.currentThread ().getName () + "\t正在写入" + key);

            try {
                TimeUnit.SECONDS.sleep (2);
            } catch (InterruptedException e) {
                e.printStackTrace ();
            }
            map.put (key, value);
            System.out.println (Thread.currentThread ().getName () + "\t写入完成--" + key);
        } finally {
            //释放锁
            readWriteLock.writeLock ().unlock ();
        }

    }

    //创建读操作
    public Object get(String key) {
        Object result = null;
        try {
            readWriteLock.readLock ().lock ();
            System.out.println (Thread.currentThread ().getName () + "\t正在读出" + key);
            try {
                TimeUnit.SECONDS.sleep (2);
            } catch (InterruptedException e) {
                e.printStackTrace ();
            }
            result = map.get (key);
            System.out.println (Thread.currentThread ().getName () + "\t读出" + result);
        } finally {
            readWriteLock.readLock ().unlock ();
        }

        return result;
    }
}

/**
 * @author laiqilin
 * @create 2019-10-11 9:22
 */

public class ReadWriteLockDemo {
    /**
     * 使用读写锁模拟缓存机制
     */
    public static void main(String[] args) {
        MyCache myCache = new MyCache ();
        for (int i = 1; i <= 5; i++) {
            final int num = i;
            new Thread (() -> {
                myCache.put (num + "", num + "");
            }, String.valueOf (i)).start ();
        }
        for (int i = 1; i <= 5; i++) {
            final int num = i;
            new Thread (() -> {
                myCache.get (num + "");
            }, String.valueOf (i)).start ();
        }
    }
}