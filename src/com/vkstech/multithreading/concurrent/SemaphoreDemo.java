package com.vkstech.multithreading.concurrent;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    public static void main(String args[]) throws InterruptedException {
        Semaphore sem = new Semaphore(3, true);
        System.out.println("is Fairness enabled : " + sem.isFair());

        sem.tryAcquire(2);
        System.out.println("Available permits : " + sem.availablePermits());

        System.out.println("number of permits drain by Main thread : " + sem.drainPermits());

        sem.release(1);

        MyThread mt1 = new MyThread(sem, "A");
        MyThread mt2 = new MyThread(sem, "B");

        mt1.start();
        mt2.start();

        System.out.println(sem);

        mt1.join();
        mt2.join();
    }
}

class MyThread extends Thread {
    private final Semaphore sem;
    private final String threadName;

    public MyThread(Semaphore sem, String threadName) {
        super(threadName);
        this.sem = sem;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        System.out.println(threadName + " is waiting for a permit.");

        try {
            sem.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(threadName + " gets a permit");

        for (int i = 0; i < 2; i++) {
            if (sem.hasQueuedThreads())
                System.out.println("Length of Queue : " + sem.getQueueLength());

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(threadName + " releases the permit.");
        sem.release();
    }
}