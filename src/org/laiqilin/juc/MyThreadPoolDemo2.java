package org.laiqilin.juc;

import java.util.concurrent.*;

/**
 * @author laiqilin
 * @create 2019-10-11 22:05
 */
public class MyThreadPoolDemo2 {

    public static void main(String[] args) {
        /**
         * 自定义线程池
         */
        ExecutorService threadPool = new ThreadPoolExecutor (
                2, //常驻线程数
                5, //能够容纳的线程数
                2L,//多余线程存活时间
                TimeUnit.SECONDS,//keepAliveTime单位
                new ArrayBlockingQueue<> (3),//任务队列（阻塞队列），被提交但是没有执行在队列等待中
                Executors.defaultThreadFactory (),//用于生成线程池中工作线程的工厂，默认
                //new ThreadPoolExecutor.AbortPolicy ()
                //new ThreadPoolExecutor.DiscardPolicy ()
                //new ThreadPoolExecutor.CallerRunsPolicy ()
                new ThreadPoolExecutor.DiscardOldestPolicy ()
        );//当队列满了，jdk自带的拒绝策略，四种拒绝策略


        try {
            //假设10个线程请求线程池处理业务
            for (int i = 1; i <= 9; i++) {
                threadPool.execute (() -> {
                    System.out.println (Thread.currentThread ().getName () + "\t处理业务！");
                });
            }
        } catch (Exception e) {
            e.printStackTrace ();
        } finally {
            threadPool.shutdown ();
        }

    }

}
