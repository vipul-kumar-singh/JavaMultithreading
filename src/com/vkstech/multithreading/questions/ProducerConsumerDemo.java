package com.vkstech.multithreading.questions;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Write a program that simulates a producer-consumer problem using a BlockingQueue. One thread should produce 5 items, and the other thread should consume them.
 */
public class ProducerConsumerDemo {

    public static void main(String[] args) {
        BlockingQueue<Integer> bq = new ArrayBlockingQueue<>(5);

        Producer producer = new Producer(bq);
        Consumer consumer = new Consumer(bq);

        producer.start();
        consumer.start();
    }

}

class Producer extends Thread {
    private final BlockingQueue<Integer> bq;

    Producer(BlockingQueue<Integer> bq) {
        this.bq = bq;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                bq.put(i);
                System.out.println("Produced: " + i);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}


class Consumer extends Thread {
    private final BlockingQueue<Integer> bq;

    Consumer(BlockingQueue<Integer> bq) {
        this.bq = bq;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Integer i = bq.take();
                System.out.println("Consumed: " + i);
                Thread.sleep(1000);

                if (i == 5)
                    break;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}