package org.laiqilin.juc;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 案例：模拟六个学生在教室，班长值班，六个同学走了之后班长才能走。
 * 原理：
 * CountDownLatch主要有两个方法，当一个或多个线程调用await方法时，这些线程会阻塞。
 * 其它线程调用countDown方法会将计数器减1(调用countDown方法的线程不会阻塞)，
 * 当计数器的值变为0时，因await方法阻塞的线程会被唤醒，继续执行。
 *
 * @author laiqilin
 * @create 2019-10-11 8:40
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch (6);
        for (int i = 1; i <= 6; i++) {
            TimeUnit.SECONDS.sleep (2);
            new Thread (() -> {
                System.out.println (Thread.currentThread ().getName () + "\t号同学离开了教室");
                countDownLatch.countDown (); //进入次数减一
            }, String.valueOf (i)).start ();
        }
        //主线程等待
        countDownLatch.await ();
        System.out.println (Thread.currentThread ().getName () + "\t班长离开了教室");
    }
}
