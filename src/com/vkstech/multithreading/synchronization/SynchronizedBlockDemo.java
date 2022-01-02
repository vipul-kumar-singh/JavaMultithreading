package com.vkstech.multithreading.synchronization;

public class SynchronizedBlockDemo {

    public static void main(String[] args) {
        Power obj = new Power();

        Thread1 p1 = new Thread1(obj);
        Thread2 p2 = new Thread2(obj);

        p1.start();
        p2.start();

    }
}

class Power {

    public void printPower(int n) {
        synchronized (this) {
            int temp = 1;
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + ":- " + n + "^" + i + " value: " + n * temp);
                temp = n * temp;
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class Thread1 extends Thread {
    private final Power p;

    public Thread1(Power p) {
        this.p = p;
    }

    public void run() {
        p.printPower(5);
    }

}

class Thread2 extends Thread {
    private final Power p;

    public Thread2(Power p) {
        this.p = p;
    }

    public void run() {
        p.printPower(8);
    }
}