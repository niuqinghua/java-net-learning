package net.learning.sample.socket.sample5.executor;

import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by niuqinghua on 15/8/10.
 */
public class ExecutorServer {

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(10000);
            ExecutorService executorService = Executors.newFixedThreadPool(50);
            while (true) {
                executorService.execute(new Task(server.accept()));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
