package main.java.io;

import main.java.model.Post;


import java.io.IOException;
import java.util.ArrayList;

public class PostRaf extends RAF<Post> {
    public static final String ADDRESS = "";

    public static final int USER_ID_LEN = 4;
    public static final int RECORD_LEN_LEN = 4;
    public static final int POST_ID_LEN = 4;
    public static final int POST_LIKES_LEN = 4;


    public PostRaf() {
        super(ADDRESS);
    }

    @Override
    public void add(Post obj) throws IOException {
        // userId recordLength id likes titleLength title captionLength caption
        // 4      4            4  4     2           n     2             m


        int recordLength = getRecordLen(obj);
        getRaf().seek(getEndOfRaf());
        getRaf().write(obj.getUserId());
        getRaf().write(recordLength);
        getRaf().write(obj.getId());
        getRaf().write(obj.getLikes());
        getRaf().writeUTF(obj.getTitle());
        getRaf().writeUTF(obj.getCaption());
    }

    public ArrayList<Post> getPostsByUser(int userId) throws IOException {
        // userId recordLength id likes titleLength title captionLength caption
        // 4      4            4  4     2           n     2             m
        ArrayList<Post> posts = new ArrayList<>();
        getRaf().seek(0);

        while (getRaf().getFilePointer() < getEndOfRaf()) {
            int eachUserId = getRaf().readInt();
            int recordLength = getRaf().readInt();
            if (eachUserId != userId) {
                getRaf().skipBytes(recordLength);
                continue;
            }

            int id = getRaf().readInt();
            int likes = getRaf().readInt();
            String title = getRaf().readUTF();
            String caption = getRaf().readUTF();
            Post post = new Post(id , userId , likes , title , caption);
            posts.add(post);
        }

        return posts;
    }

    public Post getPostById(int postId) throws IOException {
        // userId recordLength id likes titleLength title captionLength caption
        // 4      4            4  4     2           n     2             m
        getRaf().seek(0);

        while (getRaf().getFilePointer() < getEndOfRaf()) {
            int eachUserId = getRaf().readInt();
            int recordLength = getRaf().readInt();
            int eachPostId = getRaf().readInt();
            if (eachPostId != postId) {
                getRaf().skipBytes(recordLength - POST_ID_LEN); // read id, so subtract idLength from recordLength
                continue;
            }

            int likes = getRaf().readInt();
            String title = getRaf().readUTF();
            String caption = getRaf().readUTF();
            return new Post(eachPostId , eachUserId , likes , title , caption);
        }

        return null;

    }

    public void likePostById(int postId) throws IOException {
        getRaf().seek(0);

        while (getRaf().getFilePointer() < getEndOfRaf()) {
            getRaf().skipBytes(USER_ID_LEN);
            int recordLength = getRaf().readInt();
            int eachPostId = getRaf().readInt();
            if (eachPostId != postId) {
                getRaf().skipBytes(recordLength - POST_ID_LEN); // read id, so subtract idLength from recordLength
                continue;
            }

            int likes = getRaf().readInt();
            getRaf().seek(getRaf().getFilePointer() - POST_LIKES_LEN);
            getRaf().write(likes+1);
            break;
        }

    }


    private int getRecordLen(Post post) {
        // 4 + 2 + obj.getTitle().length() + 2 + obj.getCaption().length();
        int titleLen = post.getTitle().length();
        int captionLen = post.getCaption().length();
        return POST_ID_LEN + POST_LIKES_LEN + 2 + titleLen + 2 + captionLen; // 2 is string length -> use for writeUTF, readUTF
    }

}
