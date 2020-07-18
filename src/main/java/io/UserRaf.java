package main.java.io;

import lombok.SneakyThrows;
import main.java.model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class UserRaf extends RAF<User> {
    public static final String ADDRESS = "users.txt";

    public static final int POSTS_SIZE_LEN = 4,
            FOLLOWINGS_SIZE_LEN = 4,
            FOLLOWERS_SIZE_LEN = 4;


    public UserRaf() {
        super(ADDRESS);
    }

    @Override
    public void add(User obj) throws IOException {
        // id recordLen username password name  bio   postsSize followingsSize followersSize
        // 4  4         2+len    2+len    2+len 2+len 4         4              4
        int newId = newId(); // do this first to avoid any pointer problem
        seekEnd();
        writeInt(newId);
        writeInt(getRecordLen(obj));
        writeStr(obj.getUsername());
        writeStr(obj.getPassword());
        writeStr(obj.getName());
        writeStr(obj.getBio());
    }

    @SneakyThrows
    @Override
    public void writeDefaults() {
        seek(0);
        writeInt(1_000);
    }

    public ArrayList<User> getAllUsers(PostRaf postRaf , UserRelationRaf userRelationRaf) throws IOException {
        ArrayList<User> list = new ArrayList<>();
        seekStart();

        while (!isPointerAtEnd()) {
            int id = readInt();
            skip(4);// skip recordLen
            User user = getUserInfo(id , postRaf , userRelationRaf);
            list.add(user);
        }

        return list;
    }

    public ArrayList<User> getUserFromList(List<Integer> followingsOfUserIds , PostRaf postRaf , UserRelationRaf userRelationRaf) throws IOException {
        return getAllUsers(postRaf , userRelationRaf).stream()
                .filter(user -> followingsOfUserIds.contains(user.getId()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public Optional<User> getUserById(int userId , PostRaf postRaf , UserRelationRaf userRelationRaf) throws IOException {
        return getAllUsers(postRaf , userRelationRaf).stream().filter(user -> user.getId() == userId).findFirst();
    }

    private User getUserInfo(int id , PostRaf postRaf , UserRelationRaf userRelationRaf) throws IOException {
        String username = readStr();
        String password = readStr();
        String name = readStr();
        String bio = readStr();
        int postsSize = postRaf.getUserPostsSize(id);
        int followingSize = userRelationRaf.userFollowingsCount(id);
        int followerSize = userRelationRaf.userFollowersCount(id);
        User user = new User(id , name , username , password , bio);
        user.setExtraInformation(postsSize , followerSize , followingSize);
        return user;
    }

    private int getRecordLen(User obj) {
        // id recordLen username password name  bio   postsSize followingsSize followersSize
        // 4  4         2+len    2+len    2+len 2+len 4         4              4
        return 2 + obj.getUsername().length() +
                2 + obj.getPassword().length() +
                2 + obj.getName().length() +
                2 + obj.getBio().length() +
                POSTS_SIZE_LEN + FOLLOWINGS_SIZE_LEN + FOLLOWERS_SIZE_LEN;
    }
}
