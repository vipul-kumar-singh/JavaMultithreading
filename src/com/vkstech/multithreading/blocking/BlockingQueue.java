package com.vkstech.multithreading.blocking;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue<T> {

    private final Queue<T> queue;

    private final int limit;

    public BlockingQueue(int limit) {
        this.limit = limit;
        queue = new LinkedList<>();
    }

    public static void main(String[] args) {
        BlockingQueue<Integer> bq = new BlockingQueue<>(10);

        Thread t1 = new Thread(() -> {
            System.out.println("T1 Start");
            try {
                bq.enqueue(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            System.out.println("T2 Start");
            try {
                bq.dequeue();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t3 = new Thread(bq::display);

        System.out.println("T2 Called");
        t2.start();

        System.out.println("T1 Called");
        t1.start();

        System.out.println("T3 Called");
        t3.start();
    }

    public synchronized void enqueue(T item) throws InterruptedException {
        System.out.println("ENQUEUE START");

        System.out.println("ENQUEUE : WHILE START");
        while (this.queue.size() == this.limit) {
            System.out.println("ENQUEUE : WAIT START");
            wait();
            System.out.println("ENQUEUE : WAIT END");
        }
        System.out.println("ENQUEUE : WHILE END");

        System.out.println("ENQUEUE : IF START");
        if (this.queue.size() == 0) {
            System.out.println("ENQUEUE : NOTIFYALL START");
            notifyAll();
            System.out.println("ENQUEUE : NOTIFYALL END");
        }
        System.out.println("ENQUEUE : IF END");

        Thread.sleep(5000);
        System.out.println("ADDING DATA");
        this.queue.add(item);

        System.out.println("ENQUEUE END");
    }

    public synchronized T dequeue() throws InterruptedException {
        System.out.println("DEQUEUE START");
        Thread.sleep(10000);

        System.out.println("DEQUEUE : WHILE START");
        while (this.queue.size() == 0) {
            System.out.println("DEQUEUE : WAIT START");
            wait();
            System.out.println("DEQUEUE : WAIT END");
        }
        System.out.println("DEQUEUE : WHILE END");

        System.out.println("DEQUEUE : IF START");
        if (this.queue.size() == this.limit) {
            System.out.println("DEQUEUE : NOTIFYALL START");
            notifyAll();
            System.out.println("DEQUEUE : NOTIFYAL END");
        }
        System.out.println("DEQUEUE : IF END");

        System.out.println("REMOVING DATA");
        T data = this.queue.remove();

        System.out.println("DEQUEUE END");
        return data;
    }

    public synchronized void display() {
        System.out.println("DISPLAY START");
        System.out.println("DATA");
        System.out.println("DISPLAY END");
    }
}
