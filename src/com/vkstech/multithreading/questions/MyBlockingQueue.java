package com.vkstech.multithreading.questions;

import java.util.LinkedList;
import java.util.Queue;

public class MyBlockingQueue {

    private final Queue<Integer> queue;
    private final int limit;

    public MyBlockingQueue(int limit) {
        this.limit = limit;
        this.queue = new LinkedList<>();
    }

    public synchronized void put(int data) throws InterruptedException {
        while (queue.size() == limit) {
            wait();
        }

        queue.add(data);
        notifyAll();
    }

    public synchronized int take() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        int data = queue.remove();
        notifyAll();
        return data;
    }


}
