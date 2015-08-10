package net.learning.sample.socket.sample2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by niuqinghua on 15/8/10.
 */
public class MyServer {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(10000);

        while (true) {
            Socket socket = server.accept();
            invoke(socket);
        }
    }

    private static void invoke(final Socket client) throws IOException {
        new Thread(new Runnable() {
            public void run() {
                BufferedReader in = null;
                PrintWriter out = null;
                try {
                    in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    out = new PrintWriter(client.getOutputStream());

                    while (true) {
                        String msg = in.readLine();
                        String threadName = Thread.currentThread().getName();
                        String response = threadName + ", Server received " + msg;
                        System.out.println(response);
                        out.println(response);
                        out.flush();
                        if (msg.equals("bye")) {
                            break;
                        }
                    }
                } catch(IOException ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        in.close();
                    } catch (Exception e) {}
                    try {
                        out.close();
                    } catch (Exception e) {}
                    try {
                        client.close();
                    } catch (Exception e) {}
                }
            }
        }).start();
    }

}
