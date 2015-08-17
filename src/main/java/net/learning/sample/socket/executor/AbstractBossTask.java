package net.learning.sample.socket.executor;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;

/**
 * Created by niuqinghua on 15/8/17.
 */
abstract public class AbstractBossTask implements Runnable {

    private final Selector selector;

    private final ExecutorService executorService;

    public AbstractBossTask(Selector selector, ExecutorService executorService) {
        this.selector = selector;
        this.executorService = executorService;
    }

    public void run() {
        try {
            ServerSocketChannel server = ServerSocketChannel.open();
            InetSocketAddress isa = new InetSocketAddress(10000);
            server.socket().bind(isa);
            server.socket().setReuseAddress(true);
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
                        System.out.println("----");
                        SocketChannel channel = (SocketChannel) sk.channel();
                        channel.register(selector, SelectionKey.OP_READ);
                        executorService.submit(createWorkerTask(channel));
                    }
                }
            }
        } catch (Exception e) {

        }
    }

    abstract protected Runnable createWorkerTask(SocketChannel socketChannel);

}
