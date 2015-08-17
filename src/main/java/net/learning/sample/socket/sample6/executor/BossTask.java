package net.learning.sample.socket.sample6.executor;

import net.learning.sample.socket.executor.AbstractBossTask;

import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;

/**
 * Created by niuqinghua on 15/8/17.
 */
public class BossTask extends AbstractBossTask {

    public BossTask(Selector selector, ExecutorService executorService) {
        super(selector, executorService);
    }

    @Override
    protected Runnable createWorkerTask(SocketChannel socketChannel) {
        return new WorkerTask(socketChannel);
    }

}
