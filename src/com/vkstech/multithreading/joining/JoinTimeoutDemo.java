package com.vkstech.multithreading.joining;

public class JoinTimeoutDemo extends Thread {
    JoinTimeoutDemo(String str) {
        super(str);
    }

    public void run() {
        System.out.println("Started " + this.getName());
        try {
            Thread.sleep(500);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        System.out.println("Ended " + this.getName());
    }

    public static void main(String[] args) {
        JoinTimeoutDemo t1 = new JoinTimeoutDemo("first thread");
        JoinTimeoutDemo t2 = new JoinTimeoutDemo("second thread");
        t1.start();

        System.out.println("Started main thread");
        try {
            t1.join(1500); // Waiting for t1 to finish
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

        t2.start();

        try {
            t2.join(1500); // Waiting for t2 to finish
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

        System.out.println("Ended main thread");
    }
}