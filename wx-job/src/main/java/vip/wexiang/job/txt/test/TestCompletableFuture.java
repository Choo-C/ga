package vip.wexiang.job.txt.test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestCompletableFuture {
    public static void main(String[] args) {
        // 创建一个线程池
        ExecutorService executor = Executors.newFixedThreadPool(3);

// 创建三个异步任务
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            // 模拟长时间的计算任务
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Result of Future 1";
        }, executor);

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            // 模拟长时间的计算任务
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Result of Future 2";
        }, executor);

        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            // 模拟长时间的计算任务
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Result of Future 3";
        }, executor);

// 使用allOf方法等待所有的future完成
        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future1, future2, future3);

        System.out.println("start wait");
// 阻塞当前线程，直到所有的future完成
        combinedFuture.join();

// 所有的future都完成后，我们可以安全地获取各个future的结果
        try {
            System.out.println(future1.get());
            System.out.println(future2.get());
            System.out.println(future3.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

// 关闭线程池
        executor.shutdown();

    }



}
