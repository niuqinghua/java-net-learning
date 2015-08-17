package net.learning.sample.socket.sample7.executor;

import java.nio.channels.SocketChannel;

/**
 * Created by niuqinghua on 15/8/16.
 */
public class SocketTask implements Runnable {

    private SocketChannel sc;

    public SocketTask(SocketChannel sc) {
        this.sc = sc;
    }

    public void run() {

    }
}
