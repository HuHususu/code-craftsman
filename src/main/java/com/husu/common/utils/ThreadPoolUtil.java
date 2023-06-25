package com.husu.common.utils;

import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Ricardo.Y.Hu
 * @date 2023/6/12 9:02
 */
@Slf4j
public class ThreadPoolUtil {
    private ThreadPoolUtil() {
    }

    private static final ExecutorService EXECUTOR = new ThreadPoolExecutor(
            4,
            50,
            1,
            TimeUnit.HOURS,
            new ArrayBlockingQueue<>(100),
            new DefaultThreadFactory()
    );

    /**
     * 自定义异常工厂/异常处理类
     * 用于创建异常时命名，方便调试和排查，并且定义异常处理方式（打印日志）
     */
    private static class DefaultThreadFactory implements ThreadFactory, Thread.UncaughtExceptionHandler {
        private final AtomicInteger nextId = new AtomicInteger(1);

        @Override
        public Thread newThread(@NotNull Runnable task) {
            String name = "HCC-Worker-" + nextId.getAndIncrement();
            Thread thread = new Thread(null, task, name);
            thread.setUncaughtExceptionHandler(this);
            log.info("新的线程被创建：[{}]", name);
            return thread;
        }

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            log.error("线程[{}]发生异常", t.getName(), e);
        }
    }

    /**
     * 关闭线程池
     * 注意：只准在程序关闭时调用
     */
    public static void shutdown() {
        EXECUTOR.shutdown();
        log.info("线程池关闭");
    }

    /**
     * 异步执行任务，没有返回值
     *
     * @param command 要异步执行的逻辑
     */
    public static void execute(Runnable command) {
        EXECUTOR.execute(command);
    }

    /**
     * 异步执行任务，有返回值
     *
     * @param task 要异步执行的逻辑
     * @param <T>  Future 的数据类型
     * @return Future<T> 可通过get获取到具体的返回值，注意：get方法可能会阻塞
     */
    public static <T> Future<T> submit(Callable<T> task) {
        return EXECUTOR.submit(task);
    }
}
