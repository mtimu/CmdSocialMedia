package main.java.database;

import main.java.io.PostRaf;
import main.java.io.UserRaf;
import main.java.io.UserRelationRaf;
import main.java.model.Post;
import main.java.model.User;
import main.java.model.UserRelation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileRepository extends Repository {
    static Repository INSTANCE;
    private final PostRaf postRaf;
    private final UserRaf userRaf;
    private final UserRelationRaf userRelationRaf;


    FileRepository() {
        postRaf = new PostRaf();
        userRaf = new UserRaf();
        userRelationRaf = new UserRelationRaf();
    }

    static Repository getInstance() {
        if (INSTANCE == null)
            INSTANCE = new FileRepository();

        return INSTANCE;
    }

    //region Post methods
    @Override
    public ArrayList<Post> getPostsBy(int userId) {
        try {
            return postRaf.getPostsByUser(userId);
        } catch (IOException e) {
            // TODO: 6/19/2020 log error
            return new ArrayList<>();
        }
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
        try {
            return postRaf.getPostById(id);
        } catch (IOException e) {
            // TODO: 6/19/2020 log error
            return null;
        }
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
        try {
            return userRaf.getAllUsers(postRaf,userRelationRaf);
        } catch (IOException e) {
            // TODO: 6/19/2020 log error
            return new ArrayList<>();
        }
    }

    @Override
    public ArrayList<User> getUserFollowings(User user) {
        try {
            List<Integer> userFollowingsIds = userRelationRaf.getUserFollowingsIds(user.getId());
            return userRaf.getUserFromList(userFollowingsIds, postRaf, userRelationRaf);
        } catch (IOException e) {
            // TODO: 6/19/2020 log error
            return new ArrayList<>();
        }
    }


    @Override
    public ArrayList<User> getUserFollowers(User user) {
        try {
            List<Integer> userFollowersIds = userRelationRaf.getUserFollowersIds(user.getId());
            return userRaf.getUserFromList(userFollowersIds,postRaf, userRelationRaf);
        } catch (IOException e) {
            // TODO: 6/19/2020 log error
            return new ArrayList<>();
        }
    }

    @Override
    public User getUserFollowing(int userId, int followingId) {
        try {
            // return null if doesnt have a relation
            if (!userRelationRaf.haveThisRelation(userId , followingId, UserRelationRaf.FOLLOWING))
                return null;

            return userRaf.getUserById(followingId, postRaf, userRelationRaf);
        } catch (IOException e) {
            // TODO: 6/19/2020 log error
            return null;
        }
    }

    @Override
    public User getUserFollower(int userId , int followerId) {

        try {
            // return null if doesnt have a relation
            if (!userRelationRaf.haveThisRelation(userId , followerId,UserRelationRaf.FOLLOWER))
                return null;

            return userRaf.getUserById(followerId, postRaf, userRelationRaf);
        } catch (IOException e) {
            // TODO: 6/19/2020 log error
            return null;
        }
    }

    @Override
    public boolean followUser(User currentUser , int followerId) {
        try {
            userRelationRaf.add(new UserRelation(-1,currentUser.getId(),followerId));
            return true;
        } catch (IOException e) {
            // TODO: 6/19/2020 log error
            return false;
        }
    }
    //endregion

}
