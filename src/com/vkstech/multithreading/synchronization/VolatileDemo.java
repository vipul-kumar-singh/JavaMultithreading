package com.vkstech.multithreading.synchronization;

import java.util.concurrent.CountDownLatch;

public class VolatileDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);

        Shared sharedObj = new Shared();

        Runnable runnable = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " started");
            for (int i = 0; i < 100; i++) {
                Shared.staticVar++;
                sharedObj.volatileVar++;
                Shared.staticVolatileVar++;
            }
            countDownLatch.countDown();
            System.out.println(threadName + " ended");
        };

        new Thread(runnable, "T1").start();
        new Thread(runnable, "T2").start();

        countDownLatch.await();

        System.out.println("volatileVar = " + sharedObj.volatileVar);
        System.out.println("staticVar = " + Shared.staticVar);
        System.out.println("staticVolatileVar = " + Shared.staticVolatileVar);
    }

}

class Shared {

    public static int staticVar = 0;
    public static volatile int staticVolatileVar = 0;
    public volatile int volatileVar = 0;
}