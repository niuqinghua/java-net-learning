package net.learning.sample.socket.sample7.executor;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;

/**
 * Created by niuqinghua on 15/8/17.
 */
public class BossTask implements Runnable {

    private final Selector selector;

    private final ExecutorService executorService;

    public BossTask(Selector selector, ExecutorService executorService) {
        this.selector = selector;
        this.executorService = executorService;
    }

    public void run() {
        try {
            ServerSocketChannel server = ServerSocketChannel.open();
            InetSocketAddress isa = new InetSocketAddress("127.0.0.1", 30000);
            server.socket().bind(isa);
            server.configureBlocking(false);
            server.register(selector, SelectionKey.OP_ACCEPT);
            while (selector.select() > 0) {
                for (SelectionKey sk : selector.selectedKeys()) {
                    selector.selectedKeys().remove(sk);
                    if (sk.isAcceptable()) {
                        SocketChannel sc = server.accept();
                        sc.configureBlocking(false);
                        sc.register(selector, SelectionKey.OP_READ);
                        sk.interestOps(SelectionKey.OP_ACCEPT);
                    }
                    if (sk.isReadable()) {
                        executorService.submit(new SocketTask((SocketChannel) sk.channel()));
                    }
                }
            }
        } catch (Exception e) {

        }
    }

}
