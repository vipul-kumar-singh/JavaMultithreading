package com.vkstech.multithreading.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadLocalRandom;

public class SynchronousQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);
        SynchronousQueue<Integer> queue = new SynchronousQueue<>();

        Runnable producer = () -> {
            Integer producedElement = ThreadLocalRandom
                    .current()
                    .nextInt();
            try {
                queue.put(producedElement);
                System.out.println("Produced " + producedElement);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                latch.countDown();
            }
        };

        Runnable consumer = () -> {
            try {
                Integer consumedElement = queue.take();
                System.out.println("Consumed " + consumedElement);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                latch.countDown();
            }
        };

        new Thread(consumer).start();
        new Thread(producer).start();

        latch.await();
    }
}


