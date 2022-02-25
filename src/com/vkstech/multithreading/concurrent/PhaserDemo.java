package com.vkstech.multithreading.concurrent;

import java.util.concurrent.Phaser;

public class PhaserDemo {
    public static void main(String[] args) {
        Phaser phaser = new Phaser();
        phaser.register();
        int currentPhase;

        System.out.println("Starting");

        new TestingThread(phaser, "A");
        new TestingThread(phaser, "B");
        new TestingThread(phaser, "C");

        currentPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Phase " + currentPhase + " Complete");
        System.out.println("Phase Zero Ended");

        currentPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Phase " + currentPhase + " Complete");
        System.out.println("Phase One Ended");

        currentPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Phase " + currentPhase + " Complete");
        System.out.println("Phase Two Ended");

        phaser.arriveAndDeregister();
        if (phaser.isTerminated()) {
            System.out.println("Phaser is terminated");
        }
    }
}

class TestingThread implements Runnable {
    private final Phaser phaser;
    private final String title;

    public TestingThread(Phaser phaser, String title) {
        this.title = title;
        this.phaser = phaser;
        phaser.register();
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            System.out.println("Thread: " + title + " Phase Zero Started");
            phaser.arriveAndAwaitAdvance();

            Thread.sleep(10);

            System.out.println("Thread: " + title + " Phase One Started");
            phaser.arriveAndAwaitAdvance();

            Thread.sleep(10);

            System.out.println("Thread: " + title + " Phase Two Started");
            phaser.arriveAndDeregister();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}
