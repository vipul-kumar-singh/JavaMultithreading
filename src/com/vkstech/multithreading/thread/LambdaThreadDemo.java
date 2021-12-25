package com.vkstech.multithreading.thread;

public class LambdaThreadDemo {

    public static void main(String[] args) {

        new Thread(() -> {
            try {
                System.out.println("Thread " + Thread.currentThread().getId() + " is running");
            } catch (Exception e) {
                System.out.println("Exception is caught");
            }
        }).start();
    }
}
