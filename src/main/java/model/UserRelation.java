package main.java.model;

import lombok.Data;

@Data
public class UserRelation {
    private int id;
    private int userId;
    private int followerId;

    public UserRelation(int id , int userId , int followerId) {
        this.id = id;
        this.userId = userId;
        this.followerId = followerId;
    }

}
