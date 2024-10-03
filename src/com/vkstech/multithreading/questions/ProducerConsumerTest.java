package com.vkstech.multithreading.questions;


/**
 * Write a program that simulates a producer-consumer problem using a BlockingQueue. One thread should produce 5 items, and the other thread should consume them.
 */
public class ProducerConsumerTest {

    public static void main(String[] args) {
        MyBlockingQueue bq = new MyBlockingQueue(5);

        MyProducer producer = new MyProducer(bq);
        MyConsumer consumer = new MyConsumer(bq);

        producer.start();
        consumer.start();
    }

}

class MyProducer extends Thread {
    private final MyBlockingQueue bq;

    MyProducer(MyBlockingQueue bq) {
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


class MyConsumer extends Thread {
    private final MyBlockingQueue bq;

    MyConsumer(MyBlockingQueue bq) {
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