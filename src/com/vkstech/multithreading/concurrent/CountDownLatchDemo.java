package com.vkstech.multithreading.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

public class CountDownLatchDemo {

    public static Logger logger = Logger.getLogger(CountDownLatchDemo.class.getName());

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);
        logger.info("Latch initialized with count " + latch.getCount());

        DemoThread t1 = new DemoThread("T1", latch, 5000);
        DemoThread t2 = new DemoThread("T2", latch, 3000);

        t1.start();
        logger.info("Current latch count " + latch.getCount());

        t2.start();
        logger.info("Current latch count " + latch.getCount());

        latch.await();
        logger.info("Latch ended with count " + latch.getCount());
    }
}

class DemoThread extends Thread {

    private final CountDownLatch latch;
    private final long timeout;

    DemoThread(String name, CountDownLatch latch, long timeout) {
        this.latch = latch;
        this.timeout = timeout;
        this.setName(name);
    }

    @Override
    public void run() {
        try {
            CountDownLatchDemo.logger.info("Thread " + getName() + " Running");
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            latch.countDown();
            CountDownLatchDemo.logger.info("Current latch count " + latch.getCount());
        }
    }
}
