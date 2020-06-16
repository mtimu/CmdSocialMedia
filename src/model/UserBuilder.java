package model;

import java.util.ArrayList;

public class UserBuilder {
    private int id;
    private String name;
    private String username;
    private String password;
    private String bio;


    public UserBuilder(int id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        bio = String.format("Hello, my name is %s",name);
    }

    public UserBuilder bio(String bio) {
        this.bio = bio;
        return this;
    }

    public User create() {
        return new User(id , name , username , password , bio);
    }
}
