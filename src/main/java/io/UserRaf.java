package main.java.io;

import main.java.model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserRaf extends RAF<User> {
    public static final String ADDRESS = "";


    public UserRaf() {
        super(ADDRESS);
    }

    @Override
    public void add(User obj) throws IOException {

    }

    public ArrayList<User> getAllUsers() {
        return null;
    }

    public ArrayList<User> getUserFollowings(List<Integer> followingsOfUserIds) {
        return null;
    }

    public ArrayList<User> getUserFollowers(List<Integer> userFollowersIds) {
        return null;
    }

    public User getUserById(int userId) {
        return null;
    }
}
