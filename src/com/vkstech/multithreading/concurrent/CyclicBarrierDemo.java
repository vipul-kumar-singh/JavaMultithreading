package com.vkstech.multithreading.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier barrier = new CyclicBarrier(2);
        System.out.println("Barrier initialized with parties " + barrier.getParties());

        TestThread t1 = new TestThread("T1", barrier, 5000);
        TestThread t2 = new TestThread("T2", barrier, 3000);

        t1.start();
        t2.start();

        System.out.println("Barrier ended with parties " + barrier.getParties());
    }
}

class TestThread extends Thread {

    private final CyclicBarrier barrier;
    private final long timeout;

    TestThread(String name, CyclicBarrier barrier, long timeout) {
        this.barrier = barrier;
        this.timeout = timeout;
        this.setName(name);
    }

    @Override
    public void run() {
        try {
            System.out.println("Thread " + getName() + " Running");
            Thread.sleep(timeout);
            System.out.println("Thread " + getName() + " awaiting");
            barrier.await();
            System.out.println("Thread " + getName() + " ended");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

