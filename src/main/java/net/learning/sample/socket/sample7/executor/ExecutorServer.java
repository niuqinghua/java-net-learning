package net.learning.sample.socket.sample7.executor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServer {

    public void init() throws IOException {
        Selector selector = Selector.open();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.submit(new BossTask(selector, executorService));
    }

    public static void main(String[] args) throws IOException {
        new ExecutorServer().init();
    }
}