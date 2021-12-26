package com.vkstech.multithreading.joining;

public class JoinDemo extends Thread {
    public JoinDemo(String name) {
        super(name);
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
        JoinDemo t1 = new JoinDemo("t1");
        JoinDemo t2 = new JoinDemo("t2");
        t1.start();

        try {
            t1.join();    //Waiting for t1 to finish
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        t2.start();
    }
}