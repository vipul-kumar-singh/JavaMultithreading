package com.vkstech.multithreading.threadGroup;

public class ThreadGroupDemo implements Runnable {
    public void run() {
        try {
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ThreadGroupDemo runnable = new ThreadGroupDemo();
        ThreadGroup threadGroup = new ThreadGroup("Parent ThreadGroup");

        Thread t1 = new Thread(threadGroup, runnable, "one");
        t1.start();
        Thread t2 = new Thread(threadGroup, runnable, "two");
        t2.start();
        Thread t3 = new Thread(threadGroup, runnable, "three");
        t3.start();

        Thread.activeCount();

        System.out.println("Thread Group Name: " + threadGroup.getName());
        System.out.println("Active threads: " + threadGroup.activeCount());
        threadGroup.list();

    }
}