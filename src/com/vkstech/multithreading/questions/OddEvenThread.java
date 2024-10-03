package com.vkstech.multithreading.questions;

/**
 * Write a program where two threads alternate printing even and odd numbers from 1 to 10 using wait/notify.
 */
public class OddEvenThread {
    private final int start;
    private final int end;

    private boolean isOddTurn = true;

    public OddEvenThread(int start, int end) {
        this.start = start;
        this.end = end;

        if (isEven(start)) {
            isOddTurn = false;
        }

    }

    public synchronized void printOdd() {
        int startIndex = start;
        if (isEven(start)) {
            startIndex += 1;
        }

        for (int i = startIndex; i <= end; i = i + 2) {

            while (!isOddTurn) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }

            System.out.println("Odd: " + i);
            isOddTurn = false;
            notify();
        }
    }

    public synchronized void printEven() {
        int startIndex = start;
        if (!isEven(start)) {
            startIndex += 1;
        }

        for (int i = startIndex; i <= end; i = i + 2) {

            while (isOddTurn) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }

            System.out.println("Even: " + i);
            isOddTurn = true;
            notify();
        }
    }

    public static boolean isEven(int n) {
        if (n == 0)
            return true;

        return n % 2 == 0;
    }

    public static void main(String[] args) throws InterruptedException {
        OddEvenThread oe = new OddEvenThread(1, 10);

        Thread oddThread = new Thread(oe::printOdd);
        Thread evenThread = new Thread(oe::printEven);

        oddThread.start();
        evenThread.start();
    }
}
