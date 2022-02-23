package com.vkstech.multithreading.threadPool;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ExecutorDemo {

    public static void main(String[] args) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> System.out.println("Hello World"));
    }

}
