package com.vkstech.multithreading.synchronization;

public class SynchronizedStaticMethodDemo {

    public static void main(String[] args) {
        Exponent ob1 = new Exponent();
        Exponent ob2 = new Exponent();
        ThreadT1 p1 = new ThreadT1(ob1);
        ThreadT2 p2 = new ThreadT2(ob1);
        ThreadT3 p3 = new ThreadT3(ob2);
        ThreadT4 p4 = new ThreadT4(ob2);

        p1.start();
        p2.start();
        p3.start();
        p4.start();
    }
}

class Exponent {
    synchronized static void printPower(int n) {
        int temp = 1;
        for (int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName() + ":- " + n + "^" + i + " value: " + n * temp);
            temp = n * temp;
            try {
                Thread.sleep(400);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}

class ThreadT1 extends Thread {
    private final Exponent p;

    ThreadT1(Exponent p) {
        this.p = p;
        this.setName("T1");
    }

    public void run() {
        Exponent.printPower(2);
    }

}

class ThreadT2 extends Thread {
    private final Exponent p;

    ThreadT2(Exponent p) {
        this.p = p;
        this.setName("T2");
    }

    public void run() {
        Exponent.printPower(3);
    }
}

class ThreadT3 extends Thread {
    private final Exponent p;

    ThreadT3(Exponent p) {
        this.p = p;
        this.setName("T3");
    }

    public void run() {
        Exponent.printPower(5);
    }
}

class ThreadT4 extends Thread {
    private final Exponent p;

    ThreadT4(Exponent p) {
        this.p = p;
        this.setName("T4");
    }

    public void run() {
        Exponent.printPower(8);
    }
}