package com.vkstech.multithreading.questions;

/**
 * Create a program that starts two threads. One thread prints numbers from 1 to 5, and the other thread prints numbers from 6 to 10. Ensure that the threads run concurrently.
 */
public class NumberThread extends Thread {

    private final int start;
    private final int end;

    public NumberThread(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        if (start > end)
            return;

        for (int i = start; i <= end; i++) {
            System.out.println(i);
        }

    }

    public static void main(String[] args) {
        NumberThread n1 = new NumberThread(1, 5);
        NumberThread n2 = new NumberThread(6, 10);

        n1.start();
        n2.start();
    }
}
