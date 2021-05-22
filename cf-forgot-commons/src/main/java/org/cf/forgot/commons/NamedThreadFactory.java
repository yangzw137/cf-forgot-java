package org.cf.forgot.commons;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description: todo
 * <p>
 * @date 2020/10/21
 * @since todo
 */
public class NamedThreadFactory implements ThreadFactory {

    private static final AtomicInteger poolCount = new AtomicInteger();

    final AtomicInteger threadCount = new AtomicInteger(1);
    final ThreadGroup group;
    final String namePrefix;
    final boolean isDaemon;
    private boolean withUncaughtExceptionHandler;

    public NamedThreadFactory(String prefix) {
        this(prefix, true);
    }

    public NamedThreadFactory(String prefix, boolean daemon) {
        this(prefix, daemon, false);
    }

    public NamedThreadFactory(String prefix, boolean daemon, boolean withUncaughtExceptionHandler) {
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
        namePrefix = prefix + "-" + poolCount.getAndIncrement() + "-T-";
        isDaemon = daemon;
        this.withUncaughtExceptionHandler = withUncaughtExceptionHandler;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r, namePrefix + threadCount.getAndIncrement(), 0);
        t.setDaemon(isDaemon);
        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        if (withUncaughtExceptionHandler) {
            t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                @Override
                public void uncaughtException(Thread t, Throwable e) {
                    System.err.printf("threadName: %s, exception message: %s\n", t.getName(), e.getMessage());
                    e.printStackTrace();
                }
            });
        }
        return t;
    }
}
