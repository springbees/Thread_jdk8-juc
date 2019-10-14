package org.laiqilin.juc;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//创建资源类
class Aircondition {
    private int number = 0;
    //获取锁对象
    private Lock lock = new ReentrantLock();
    //返回用于此锁实例的条件实例
    Condition condition = lock.newCondition();

    public void decrement() {
        lock.lock();
        try {
            while (number == 0) {
                //使当前线程等待，直到发出信号或中断为止
                condition.await();
            }
            //干活
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //通知
            //唤醒所有等待的线程
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void increment() {
        lock.lock();
        try {
            while (number != 0) {
                condition.await();
            }
            //干活
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //通知
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    //====================================================
   /* public synchronized void decrement() throws InterruptedException {
        //判断
        while (number == 0) {
            this.wait();
        }
        //干活
        number--;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        //通知
        this.notifyAll();
    }

    public synchronized void increment() throws InterruptedException {
        //判断
        while (number != 0) {
            this.wait();
        }
        //干活
        number++;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        //通知
        this.notifyAll();

    }*/
}

/**
 * 使用线程模拟生产消费
 * 案例：
 * 现在两个线程，可以操作初始值为0的一个变量，
 * 实现一个线程对该变量加1，一个线程对该变量减1，
 * 实现交替，来回10轮，变量初始值为0.
 */
public class ProdConsumerDemo {

    public static void main(String[] args) {
        Aircondition aircondition = new Aircondition();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    aircondition.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    aircondition.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    aircondition.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    aircondition.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}
