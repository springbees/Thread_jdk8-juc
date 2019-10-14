package org.laiqilin.juc;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author laiqilin
 * @create 2019-10-08 14:37
 */
public class HashSetDemo {
    /**
     * 问题：java.util.ConcurrentModificationException
     * HashSet也是线程不安全的多线程情况下会发生并发修改异常。
     * 解决方案：
     * 使用java.util.concurrent.CopyOnWriteArraySet
     */

    public static void main(String[] args) {
        Set<String> set = new CopyOnWriteArraySet<> ();

        for (int i = 0; i < 10; i++) {
            new Thread (() -> {
                set.add (UUID.randomUUID ().toString ().substring (0, 5));
                System.out.println (Thread.currentThread ().getName () + "---->" + set);
            }, String.valueOf (i)).start ();
        }
    }
}
