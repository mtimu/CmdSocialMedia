package main.java.database;

import main.java.model.Post;
import main.java.model.User;

import java.util.ArrayList;
import java.util.Optional;

public abstract class Repository {

    //region Post Methods
    public abstract ArrayList<Post> getPostsBy(int userId);

    public abstract Post getPostById(int id);

    public abstract boolean addPost(Post post);

    public abstract boolean likePostById(int postId);
    //endregion


    public abstract void addUser(User user);

    public abstract Optional<User> getUserByUserPass(String username , String password);

    public abstract Optional<User> getUserFollowing(int userId , int followingId);

    public abstract Optional<User> getUserFollower(int userId , int followerId);


    public abstract ArrayList<User> getAllUsers();

    public abstract ArrayList<User> getUserFollowings(User user);

    public abstract ArrayList<User> getUserFollowers(User user);

    public abstract boolean followUser(User currentUser , int followingId);

}
