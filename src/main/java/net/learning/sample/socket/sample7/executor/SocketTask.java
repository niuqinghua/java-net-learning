package net.learning.sample.socket.sample7.executor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * Created by niuqinghua on 15/8/16.
 */
public class SocketTask implements Runnable {

    private Charset charset = Charset.forName("UTF-8");

    private SocketChannel sc;

    public SocketTask(SocketChannel sc) {
        this.sc = sc;
    }

    public void run() {
        ByteBuffer buff = ByteBuffer.allocate(1024);
        String content = "";
        try {
            while (sc.read(buff) > 0) {
                buff.flip();
                content += charset.decode(buff);
            }
            System.out.println("==========" + content);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (content.length() > 0) {
            try {
                sc.write(charset.encode(content));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
