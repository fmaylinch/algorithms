package com.codethen.concepts;

import java.util.concurrent.*;

public class Executors {

    public static void main(String... args) throws ExecutionException, InterruptedException {

        final ExecutorService executorService = java.util.concurrent.Executors.newFixedThreadPool(5);

        final ExecutorCompletionService<String> completionService = new ExecutorCompletionService<>(executorService);
        completionService.submit(() -> {
            Thread.sleep(3000);
            return "done!";
        });

        System.out.println("Taking the future...");
        final Future<String> future = completionService.take();

        System.out.println("Waiting for the result...");
        System.out.println(future.get());

        executorService.shutdown();
    }
}
