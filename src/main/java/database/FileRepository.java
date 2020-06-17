package main.java.database;

import main.java.model.Post;
import main.java.model.User;

import java.util.ArrayList;

public class FileRepository extends Repository {
    static Repository INSTANCE;


    FileRepository() {
    }

    static Repository getInstance() {
        if (INSTANCE == null)
            INSTANCE = new FileRepository();

        return INSTANCE;
    }

    @Override
    public ArrayList<Post> getPostsBy(User user) {
        ArrayList<Post> list = new ArrayList<>();
        list.add(new Post(100 , "Hello" , "This is caption"));
        return list;
    }

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
    public boolean addPostFor(User user , Post post) {
        return false;
    }

    @Override
    public Post getPostById(int id) {
        return new Post(100,"Hello","This is caption");
    }

    @Override
    public User getUserById(int currentUserId, int userId) {
        return new User(1000,"Gholi","Gholi1364","Gholi12356","Motada nemigiran");
    }

    @Override
    public boolean followUser(User currentUser , int choice) {
        return true;
    }


    @Override
    public boolean likePostById(int postId) {
        return true;
    }
}
