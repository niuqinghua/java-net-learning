package net.learning.sample.socket.sample6.executor;

import java.nio.channels.Selector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by niuqinghua on 15/8/10.
 */
public class ExecutorServer {

    public static void main(String[] args) throws Exception{
        Selector selector = Selector.open();
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        executorService.submit(new BossTask(selector, executorService));
    }

}
