package BioServer;

import java.util.concurrent.*;

/**
 * @author lwj32
 */
public class TimeServerHandlerExecutorPool implements Executor {
    private ExecutorService executorService;
    public TimeServerHandlerExecutorPool(int queueSize, int maxSize) {
        this.executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), maxSize, 1000L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(queueSize));
    }

    public TimeServerHandlerExecutorPool(int coreSize, int queueSize, int maxSize) {
        this.executorService = new ThreadPoolExecutor(coreSize, maxSize, 1000L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(queueSize));
    }

    @Override
    public void execute(Runnable command) {
        executorService.execute(command);
    }
}
