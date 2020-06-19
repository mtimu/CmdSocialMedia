package main.java.io;

import main.java.model.UserRelation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;


public class UserRelationRaf extends RAF<UserRelation> {
    private static final String ADDRESS = "";

    public static final int FOLLOWING = 0;
    public static final int FOLLOWER = 1;

    public UserRelationRaf() {
        super(ADDRESS);
    }

    @Override
    public void add(UserRelation obj) throws IOException {
        // id userId followerId
        // 4  4      4
        getRaf().seek(getEndOfRaf()); // TODO: 6/19/2020 set space for raf scheme
        // TODO: 6/19/2020 replace seek with skip

        getRaf().writeInt(obj.getId()); // TODO: 6/19/2020 use new id
        getRaf().writeInt(obj.getUserId());
        getRaf().writeInt(obj.getFollowerId());

    }

    public List<Integer> getUserFollowingsIds(int userId) throws IOException {
        return getUsersId((eachUserId , eachFollowerId) -> eachFollowerId == userId ? eachUserId : -1);
    }

    public List<Integer> getUserFollowersIds(int userId) throws IOException {
        return getUsersId((eachUserId , eachFollowerId) -> eachUserId == userId ? eachFollowerId : -1);
    }

    private List<Integer> getUsersId(BiFunction<Integer, Integer, Integer> condition) throws IOException {
        List<Integer> list = new ArrayList<>();
        getRaf().seek(0);
        while (getRaf().getFilePointer() < getRaf().length()) {
            getRaf().skipBytes(4); // skip relation id
            int eachUserId = getRaf().readInt();
            int eachFollowerId = getRaf().readInt();
            int result = condition.apply(eachUserId , eachFollowerId);
            if (result != -1)
                list.add(result);
        }

        return list;
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean haveThisRelation(int userId , int secondUserId , int relation) throws IOException {
        getRaf().seek(0);

        while (getRaf().getFilePointer() < getRaf().length()) {
            getRaf().skipBytes(4); // skip relation id
            int eachUserId = getRaf().readInt();
            int eachFollowerId = getRaf().readInt();
            if (relation == FOLLOWER) if (secondUserId == eachFollowerId && userId == eachUserId)
                return true;

            if (relation == FOLLOWING) {
                if (eachUserId == secondUserId && eachFollowerId == userId)
                    return true;
            }

        }

        return false;
    }

    public int userFollowersCount(int userId) throws IOException {
        int sum = 0;
        getRaf().seek(0);

        while (getRaf().getFilePointer() < getRaf().length()) {
            getRaf().skipBytes(4); // skip relation id
            int eachUserId = getRaf().readInt();
            getRaf().skipBytes(4); // skip followerId
            if (eachUserId == userId)
                sum++;
        }

        return sum;
    }

    public int userFollowingsCount(int userId) throws IOException {
        int sum = 0;
        getRaf().seek(0);

        while (getRaf().getFilePointer() < getRaf().length()) {
            getRaf().skipBytes(4); // skip relation id
            getRaf().skipBytes(4); // skip userId
            int eachFollowerId = getRaf().readInt();
            if (eachFollowerId == userId)
                sum++;
        }

        return sum;
    }

}
