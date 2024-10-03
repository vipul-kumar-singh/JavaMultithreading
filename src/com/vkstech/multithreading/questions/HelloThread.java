package com.vkstech.multithreading.questions;

/**
 * Write a program that creates a thread using the Runnable interface. The thread should print "Hello from thread!" five times, with a delay of 1 second between each message.
 */
public class HelloThread implements Runnable {

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println("Hello from thread!");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        HelloThread t1 = new HelloThread();
        t1.run();
    }
}
