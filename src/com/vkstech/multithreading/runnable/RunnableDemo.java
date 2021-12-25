package com.vkstech.multithreading.runnable;

class RunnableImpl implements Runnable {

    @Override
    public void run() {
        System.out.println("Thread " + Thread.currentThread().getId() + " is running");
    }
}

public class RunnableDemo {

    public static void main(String[] args) {
        int n = 8;
        for (int i = 0; i < n; i++) {
            Thread object = new Thread(new RunnableImpl());
            object.start();
        }

    }
}