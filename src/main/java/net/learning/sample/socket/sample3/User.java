package net.learning.sample.socket.sample3;

import java.io.Serializable;

/**
 * Created by niuqinghua on 15/8/10.
 */
public class User implements Serializable {

    private String name;

    private String password;

    public User() {

    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
