package com.vkstech.multithreading.runnable;

public class LambdaRunnableDemo {

    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println("Thread " + Thread.currentThread().getId() + " is running");
        };

        for (int i = 0; i < 5; i++) {
            new Thread(runnable).start();
        }
    }
}
