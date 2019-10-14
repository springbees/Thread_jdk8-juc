package org.laiqilin.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 第三种线程池创建方式
 */
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<String> task = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Callable线程...";
            }
        });
        new Thread(task,"A").start();
        String s = task.get();
        System.out.println("s = " + s);
    }


}
