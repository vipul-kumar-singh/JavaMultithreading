package com.vkstech.multithreading.concurrent;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Exchanger;

public class ExchangerDemo {

    public static void main(String[] args) throws InterruptedException {
        Exchanger<String> exchanger = new Exchanger<>();
        CountDownLatch latch = new CountDownLatch(2);

        Runnable runnable = () -> {
            try {
                String randomString = UUID.randomUUID().toString();
                System.out.println(Thread.currentThread().getName() + "::SENT::" + randomString);
                randomString = exchanger.exchange(randomString);
                System.out.println(Thread.currentThread().getName() + "::RECEIVED::" + randomString);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                latch.countDown();
            }
        };

        new Thread(runnable, "T1").start();
        new Thread(runnable, "T2").start();

        latch.await();
    }
}
