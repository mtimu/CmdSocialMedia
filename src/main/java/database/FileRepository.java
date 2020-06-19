package main.java.database;

import main.java.io.PostRaf;
import main.java.model.Post;
import main.java.model.User;

import java.util.ArrayList;

public class FileRepository extends Repository {
    static Repository INSTANCE;
    private PostRaf postRaf;


    FileRepository() {
        postRaf = new PostRaf();
    }

    static Repository getInstance() {
        if (INSTANCE == null)
            INSTANCE = new FileRepository();

        return INSTANCE;
    }

    //region Post methods
    @Override
    public ArrayList<Post> getPostsBy(int userId) {
        return postRaf.getPostsByUser(userId);
    }

    @Override
    public boolean addPost( Post post) {
        try {
            postRaf.add(post);
            return true;
        } catch (Exception exception) {
            // TODO: 6/19/2020 log error
            return false;
        }
    }

    @Override
    public Post getPostById(int id) {
        return postRaf.getPostById(id);
    }

    @Override
    public boolean likePostById(int postId) {
        try {
            postRaf.likePostById(postId);
            return true;
        } catch (Exception exception) {
            // TODO: 6/19/2020 log error
            return false;
        }
    }
    //endregion

    //region User methods
    @Override
    public ArrayList<User> getAllUsers() {
        ArrayList<User> list = new ArrayList<>();
        list.add(new User(1000 , "Gholi" , "Gholi1364" , "Gholi12356" , "Motada nemigiran"));
        return list;
    }

    @Override
    public ArrayList<User> getFollowingsBy(User user) {
        ArrayList<User> list = new ArrayList<>();
        list.add(new User(1000 , "Gholi" , "Gholi1364" , "Gholi12356" , "Motada nemigiran"));
        return list;
    }


    @Override
    public ArrayList<User> getFollowerBy(User user) {
        ArrayList<User> list = new ArrayList<>();
        list.add(new User(1000 , "Gholi" , "Gholi1364" , "Gholi12356" , "Motada nemigiran"));
        return list;
    }

    @Override
    public User getUserById(int currentUserId, int userId) {
        return new User(1000,"Gholi","Gholi1364","Gholi12356","Motada nemigiran");
    }

    @Override
    public boolean followUser(User currentUser , int followerId) {
        return true;
    }
    //endregion

}
