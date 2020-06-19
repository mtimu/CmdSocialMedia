package main.java.database;

import main.java.model.Post;
import main.java.model.User;

import java.util.ArrayList;

public abstract class Repository {

    //region Post Methods
    public abstract ArrayList<Post> getPostsBy(int userId);

    public abstract Post getPostById(int id);

    public abstract boolean addPost(Post post);

    public abstract boolean likePostById(int postId);
    //endregion


    public abstract User getUserById(int currentUserId,int userId);

    public abstract ArrayList<User> getAllUsers();

    public abstract ArrayList<User> getFollowingsBy(User user);

    public abstract ArrayList<User> getFollowerBy(User user);

    public abstract boolean followUser(User currentUser , int followerId);

}
