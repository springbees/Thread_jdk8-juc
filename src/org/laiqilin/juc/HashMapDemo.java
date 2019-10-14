package org.laiqilin.juc;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author laiqilin
 * @create 2019-10-08 14:43
 */
public class HashMapDemo {
    /**
     * 问题：java.util.ConcurrentModificationException
     * HashMap在多线程的情况下也是线程不安全的会发生并发修改异常
     * 解决方案：
     * java.util.concurrent.ConcurrentHashMap;
     */

    public static void main(String[] args) {
        Map<String, Object> map = new ConcurrentHashMap<> ();
        for (int i = 0; i < 10; i++) {
            new Thread (() -> {
                map.put (UUID.randomUUID ().toString ().substring (0, 2), UUID.randomUUID ().toString ().substring (0,5));
                System.out.println (map);
            }, String.valueOf (i)).start ();
        }
    }
}
