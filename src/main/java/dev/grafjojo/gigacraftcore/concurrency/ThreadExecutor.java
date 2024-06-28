package dev.grafjojo.gigacraftcore.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadExecutor {

    private static final ExecutorService executor = Executors.newCachedThreadPool();

    public static void execute(Runnable runnable) {
        executor.execute(runnable);
    }

    public static void shutdown() {
        executor.shutdown();
    }

    public static ExecutorService getExecutor() {
        return executor;
    }
}