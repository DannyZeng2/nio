package bio.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SocketServerPoolHandler {

    private final ExecutorService executorService;


    /**
     * 初始化线程池对象
     * public ThreadPoolExecutor(int corePoolSize,
     * int maximumPoolSize,
     * long keepAliveTime,
     * TimeUnit unit,
     * BlockingQueue<Runnable> workQueue)
     */
    public SocketServerPoolHandler(int maximumPoolSize, long keepAliveTime, int queueSize) {
        executorService = new ThreadPoolExecutor(4, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(queueSize));
    }

    public void execute(Runnable target) {
        executorService.execute(target);
    }
}
