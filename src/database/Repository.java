package database;

import model.Post;
import model.User;

import java.util.ArrayList;

public abstract class Repository {

    public abstract ArrayList<Post> getPostsBy(User user);

    public abstract ArrayList<User> getAllUsers();

    public abstract ArrayList<User> getFollowingsBy(User user);

    public abstract ArrayList<User> getFollowerBy(User user);

    public abstract boolean addPostFor(User user , Post post);

    public abstract Post getPostById(int id);

    public abstract User getUserById(int currentUserId,int userId);

    public abstract boolean followUser(User currentUser , int choice);

    public abstract Post getUserSinglePostById(User following , int postId);

    public abstract boolean likePostById(int postId);
}
