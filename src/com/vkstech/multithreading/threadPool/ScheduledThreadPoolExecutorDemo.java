package com.vkstech.multithreading.threadPool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ScheduledThreadPoolExecutorDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch lock = new CountDownLatch(3);
        AtomicInteger count = new AtomicInteger(0);
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);
        ScheduledFuture<?> future = executor.scheduleAtFixedRate(() -> {
            System.out.println("Hello World " + count.incrementAndGet());
            lock.countDown();
        }, 500, 100, TimeUnit.MILLISECONDS);

        lock.await(1000, TimeUnit.MILLISECONDS);
        future.cancel(true);

        executor.shutdown();
    }
}
