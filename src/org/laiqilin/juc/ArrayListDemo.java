package org.laiqilin.juc;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author laiqilin
 * @create 2019-10-08 14:24
 */
public class ArrayListDemo {
    public static void main(String[] args) {

        /** 异常：
         * java.util.ConcurrentModificationException
         * ArrayList是线程不安全的
         *解决方案可以使用java.util.concurrent包下面的类进行解决
         * CopyOnWriteArrayList 解决线程安全
         */
        List<String> list = new CopyOnWriteArrayList<> ();

        for (int i = 0; i < 10; i++) {
            new Thread (() -> {
                list.add (UUID.randomUUID ().toString ().substring (0, 8));
                System.out.println (list);
            }, String.valueOf (i)).start ();

        }
    }
}
