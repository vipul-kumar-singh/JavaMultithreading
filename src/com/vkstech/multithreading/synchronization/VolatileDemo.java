package com.vkstech.multithreading.synchronization;

import java.util.concurrent.CountDownLatch;

public class VolatileDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);

        Shared sharedObj1 = new Shared();
        Shared sharedObj2 = new Shared();

        Runnable runnable = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " started");
            for (int i = 0; i < 100; i++) {
                Shared.staticVar++;
                sharedObj1.volatileVar++;
                Shared.staticVolatileVar++;
            }
            countDownLatch.countDown();
            System.out.println(threadName + " ended");
        };

        new Thread(runnable, "T1").start();
        new Thread(runnable, "T2").start();

        countDownLatch.await();

        System.out.println("SharedObject1::staticVar = " + sharedObj1.staticVar);
        System.out.println("SharedObject1::volatileVar = " + sharedObj1.volatileVar);
        System.out.println("SharedObject1::staticVolatileVar = " + sharedObj1.staticVolatileVar);

        System.out.println("SharedObject2::staticVar = " + sharedObj2.staticVar);
        System.out.println("SharedObject2::volatileVar = " + sharedObj2.volatileVar);
        System.out.println("SharedObject2::staticVolatileVar = " + sharedObj2.staticVolatileVar);
    }

}

class Shared {

    public static int staticVar = 0;
    public static volatile int staticVolatileVar = 0;
    public volatile int volatileVar = 0;
}