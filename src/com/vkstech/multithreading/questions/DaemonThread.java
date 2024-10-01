package com.vkstech.multithreading.questions;


// How can we create daemon threads?
public class DaemonThread extends Thread {

    public DaemonThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        if (Thread.currentThread().isDaemon()) {
            System.out.println(getName() + " is daemon thread.");
        } else {
            System.out.println(getName() + " is user thread.");
        }
    }

    public static void main(String[] args) {
        DaemonThread dt1 = new DaemonThread("dt1");
        DaemonThread dt2 = new DaemonThread("dt2");
        DaemonThread dt3 = new DaemonThread("dt3");

        dt1.setDaemon(true);
        dt1.start();

        dt2.start();

        dt3.start();
        dt3.setDaemon(true); // throws exception
    }
}
