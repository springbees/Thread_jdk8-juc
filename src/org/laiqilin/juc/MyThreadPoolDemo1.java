package org.laiqilin.juc;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author laiqilin
 * @create 2019-10-11 21:10
 */
public class MyThreadPoolDemo1 {
    /**
     * 编写三种jdk提供的线程池技术
     * Executor
     */

    public static void main(String[] args) {

        // ExecutorService threadPool = Executors.newSingleThreadExecutor (); //比如一个银行网点，只有一个窗口服务，单线程
        //ExecutorService threadPool = Executors.newFixedThreadPool (5);//比如一个银行网点，开启了5个窗口
        ExecutorService threadPool = Executors.newCachedThreadPool ();//比如一个银行网点，可以拓展受理业务窗口
        try {
            //假设有10个顾客请求线程服务
            for (int i = 1; i <= 10; i++) {
                //TimeUnit.SECONDS.sleep (2);
                threadPool.execute (() -> {
                    System.out.println (Thread.currentThread ().getName () + "\t办理业务！");
                });
            }
        } catch (Exception e) {
            e.printStackTrace ();
        } finally {
            threadPool.shutdown ();
        }

    }


}
