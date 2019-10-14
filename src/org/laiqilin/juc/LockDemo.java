package org.laiqilin.juc;


import java.util.concurrent.TimeUnit;

class Phone {

    public static synchronized void sendEmail() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println(Thread.currentThread().getName() + "----Phone.sendEmail");
    }

    public  synchronized void sendSMS() {
        System.out.println(Thread.currentThread().getName() + "----Phone.sendSMS");
    }

    //普通方法
    public String sayHello() {
        return Thread.currentThread().getName() + "----Hello Thread......";
    }
}

/**
 * 1.synchronized修饰的方法，当创建实例调用它，锁是当前对象，
 * 只要有一个方法去调用了synchronized方法，其他的线程都只能等待，等待那条线程释放完。
 * 2.synchronized锁实现同步的基础是：Java中的每一个对象都可以作为锁
 * 2.1创建两个资源对象了，锁是不同一把锁。
 * 3.表现为一下三种
 * 3.1对于静态同步方法锁是当前类的Class对象
 * 3.2对于普通同步方法，锁是当前对象，即为锁的是当前this
 * 3.2对于同步代码块，锁是括号里面配置的对象。
 *
 */
public class LockDemo {

    public static void main(String[] args) throws Exception {
        //资源类
        Phone phone = new Phone();
        Phone phone2 = new Phone();
        //创建线程
        new Thread(() -> {
            try {
                phone.sendEmail();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        //第二条线程
        new Thread(() -> {
            phone.sendSMS();
        }, "B").start();
        //第三条线程
        new Thread(() -> {
            System.out.println(phone.sayHello());
        }, "C").start();
    }
}
