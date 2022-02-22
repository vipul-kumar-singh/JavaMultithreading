package com.vkstech.multithreading.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {

    public static void main(String[] args) throws InterruptedException {
        Counter c = new Counter();

        Thread first = new Thread(c, "First");
        Thread second = new Thread(c, "Second");

        first.start();
        second.start();

        first.join();
        second.join();

        System.out.println(c.count);
    }
}

class Counter extends Thread {

    AtomicInteger count;

    Counter() {
        count = new AtomicInteger();
    }

    public void run() {
        int max = 1_000_00_000;

        for (int i = 0; i < max; i++) {
            count.addAndGet(1);
        }
    }
}