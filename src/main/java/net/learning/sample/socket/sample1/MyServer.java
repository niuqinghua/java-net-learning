package net.learning.sample.socket.sample1;

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
        Socket socket = server.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream());

        while (true) {
            String msg = in.readLine();
            System.out.println(msg);
            out.println("Server received " + msg);
            out.flush();
            if (msg.equals("bye")) {
                break;
            }
        }
        socket.close();
    }

}
