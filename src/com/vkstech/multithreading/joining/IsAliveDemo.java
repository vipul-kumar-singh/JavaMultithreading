package com.vkstech.multithreading.joining;

public class IsAliveDemo extends Thread {

    public IsAliveDemo(String name) {
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
        IsAliveDemo t1 = new IsAliveDemo("t1");
        IsAliveDemo t2 = new IsAliveDemo("t2");
        t1.start();
        t2.start();
        System.out.println(t1.isAlive());
        System.out.println(t2.isAlive());
    }
}