package com.vkstech.multithreading.thread;

public class ThreadDemo extends Thread {
    public void run() {
        try {
            System.out.println("Thread " + Thread.currentThread().getId() + " is running");
        } catch (Exception e) {
            System.out.println("Exception is caught");
        }
    }
}

class Main {
    public static void main(String[] args) {
        int n = 5;
        for (int i = 0; i < n; i++) {
            ThreadDemo object = new ThreadDemo();
            object.start();
        }
    }
}
