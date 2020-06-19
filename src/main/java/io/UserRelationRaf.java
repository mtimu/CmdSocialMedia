package main.java.io;

import main.java.model.UserRelation;

import java.io.IOException;
import java.util.List;

public class UserRelationRaf extends RAF<UserRelation> {
    private static final String ADDRESS = "";

    public static final int FOLLOWING = 0;
    public static final int FOLLOWER = 1;

    public UserRelationRaf() {
        super(ADDRESS);
    }

    @Override
    public void add(UserRelation obj) throws IOException {

    }

    public List<Integer> getUserFollowingsIds(int userId) {
        return null;
    }

    public List<Integer> getUserFollowersIds(int userId) {
        return null;
    }

    public boolean haveRelation(int userId , int secondUserId, int relation) {
        return false;
    }

}
