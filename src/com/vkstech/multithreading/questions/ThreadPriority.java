package com.vkstech.multithreading.questions;

// Thread Priority Example
class SharedResource {

    private boolean dataAvailable = false;
    private String data;

    public synchronized void produceData(String data) {
        System.out.println("Producer is producing the data...");
        this.data = data;
        dataAvailable = true;
        notifyAll();
        System.out.println("Producer has produced the data: " + data);
    }

    public synchronized void consumeData(String threadName) {
        while (!dataAvailable) {
            try {
                System.out.println(threadName + " is waiting for data...");
                wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        System.out.println(threadName + " consumed data: " + data);
    }
}

public class ThreadPriority {

    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        Thread consumer1 = new Thread(() -> resource.consumeData("Consumer 1"));
        Thread consumer2 = new Thread(() -> resource.consumeData("Consumer 2"));
        Thread consumer3 = new Thread(() -> resource.consumeData("Consumer 3"));

        consumer1.setPriority(Thread.NORM_PRIORITY);
        consumer2.setPriority(Thread.NORM_PRIORITY);
        consumer3.setPriority(Thread.MAX_PRIORITY);

        Thread producer = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            resource.produceData("Sample Data");
        });

        consumer1.start();
        consumer2.start();
        consumer3.start();

        producer.start();

    }

}