package net.learning.sample.socket.sample5.executor;

import net.learning.sample.socket.sample5.User;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by niuqinghua on 15/8/16.
 */
public class Task implements Runnable {

    private final static Logger logger = Logger.getLogger(Task.class.getName());

    private Socket socket;

    public Task(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        ObjectInputStream is = null;
        ObjectOutputStream os = null;
        try {
            is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
            os = new ObjectOutputStream(socket.getOutputStream());

            Object obj = is.readObject();
            User user = (User)obj;
            System.out.println("user: " + user.getName() + "/" + user.getPassword());

            user.setName(user.getName() + "_new");
            user.setPassword(user.getPassword() + "_new");

            os.writeObject(user);
            os.flush();
        } catch (IOException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch(ClassNotFoundException ex) {
            logger.log(Level.SEVERE, null, ex);
        } finally {
            try {
                is.close();
            } catch(Exception ex) {}
            try {
                os.close();
            } catch(Exception ex) {}
            try {
                socket.close();
            } catch(Exception ex) {}
        }
    }
}
