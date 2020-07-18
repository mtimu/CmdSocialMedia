package main.java.io;

import lombok.SneakyThrows;
import main.java.model.UserRelation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;


public class UserRelationRaf extends RAF<UserRelation> {
    private static final String ADDRESS = "usersRelations.txt";

    public static final int FOLLOWING = 0;
    public static final int FOLLOWER = 1;

    public UserRelationRaf() {
        super(ADDRESS);
    }

    @Override
    public void add(UserRelation obj) throws IOException {
        // id userId followerId
        // 4  4      4
        int newId = newId();
        seekEnd();
        writeInt(newId);
        writeInt(obj.getUserId());
        writeInt(obj.getFollowerId());
    }

    @SneakyThrows
    @Override
    public void writeDefaults() {
        writeInt(1_000_000);
    }

    public List<Integer> getUserFollowingsIds(int userId) throws IOException {
        return getUsersId((eachUserId , eachFollowerId) -> eachFollowerId == userId ? eachUserId : -1);
    }

    public List<Integer> getUserFollowersIds(int userId) throws IOException {
        return getUsersId((eachUserId , eachFollowerId) -> eachUserId == userId ? eachFollowerId : -1);
    }

    private List<Integer> getUsersId(BiFunction<Integer, Integer, Integer> condition) throws IOException {
        List<Integer> list = new ArrayList<>();
        seekStart();
        while (!isPointerAtEnd()) {
            skip(4); // skip relation id
            int eachUserId = readInt();
            int eachFollowerId = readInt();
            int result = condition.apply(eachUserId , eachFollowerId);
            if (result != -1)
                list.add(result);
        }

        return list;
    }

    /**
     * if Relation == FOLLOWING, means *userId* follow *secondUserId*
     * else if Relation == FOLLOWER, means *secondUserId* follow *userId*
     *
     * @param userId
     * @param secondUserId
     * @param relation     could be either FOLLOWING or FOLLOWER
     * @return return true if two users have specified relation
     * @throws IOException
     */
    public boolean haveThisRelation(int userId , int secondUserId , int relation) throws IOException {
        seekStart();

        while (!isPointerAtEnd()) {
            skip(4); // skip relation id
            int eachUserId = readInt();
            int eachFollowerId = readInt();

            if (relation == FOLLOWER)
                if (userId == eachUserId && secondUserId == eachFollowerId)
                    return true;

            if (relation == FOLLOWING)
                if (eachUserId == secondUserId && eachFollowerId == userId)
                    return true;

        }

        return false;
    }

    public int userFollowersCount(int userId) throws IOException {
        int sum = 0;
        seekStart();

        while (!isPointerAtEnd()) {
            skip(4); // skip relation id
            int eachUserId = readInt();
            skip(4); // skip followerId
            if (eachUserId == userId)
                sum++;
        }

        return sum;
    }

    public int userFollowingsCount(int userId) throws IOException {
        int sum = 0;
        seekStart();

        while (!isPointerAtEnd()) {
            skip(4); // skip relation id
            skip(4); // skip userId
            int eachFollowerId = readInt();
            if (eachFollowerId == userId)
                sum++;
        }

        return sum;
    }

}
