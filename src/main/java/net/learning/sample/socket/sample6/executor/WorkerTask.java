package net.learning.sample.socket.sample6.executor;

import net.learning.sample.socket.sample6.MyRequestObject;
import net.learning.sample.socket.sample6.MyResponseObject;
import net.learning.sample.socket.sample6.SerializableUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by niuqinghua on 15/8/17.
 */
public class WorkerTask implements Runnable {

    private final static Logger logger = Logger.getLogger(WorkerTask.class.getName());

    private SocketChannel sc;

    public WorkerTask(SocketChannel sc) {
        this.sc = sc;
    }

    public void run() {
        try {
            execute(sc);
        } catch (Exception e) {

        }
    }

    private void execute(SocketChannel socketChannel) throws IOException {
        try {
            MyRequestObject myRequestObject = receiveData(socketChannel);
            logger.log(Level.INFO, myRequestObject.toString());

            MyResponseObject myResponseObject = new MyResponseObject(
                    "response for " + myRequestObject.getName(),
                    "response for " + myRequestObject.getValue());
            sendData(socketChannel, myResponseObject);
            logger.log(Level.INFO, myResponseObject.toString());
        } finally {
            try {
                socketChannel.close();
            } catch(Exception ex) {}
        }
    }

    private MyRequestObject receiveData(SocketChannel socketChannel) throws IOException {
        MyRequestObject myRequestObject = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        try {
            byte[] bytes;
            int size = 0;
            while ((size = socketChannel.read(buffer)) >= 0) {
                buffer.flip();
                bytes = new byte[size];
                buffer.get(bytes);
                baos.write(bytes);
                buffer.clear();
            }
            bytes = baos.toByteArray();
            Object obj = SerializableUtil.toObject(bytes);
            myRequestObject = (MyRequestObject)obj;
        } finally {
            try {
                baos.close();
            } catch(Exception ex) {}
        }
        return myRequestObject;
    }

    private void sendData(SocketChannel socketChannel, MyResponseObject myResponseObject) throws IOException {
        byte[] bytes = SerializableUtil.toBytes(myResponseObject);
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        socketChannel.write(buffer);
    }

}
