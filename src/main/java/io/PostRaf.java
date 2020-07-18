package main.java.io;

import lombok.SneakyThrows;
import main.java.model.Post;

import java.io.IOException;
import java.util.ArrayList;

public final class PostRaf extends RAF<Post> {
    public static final String ADDRESS = "posts.txt";

    public static final int USER_ID_LEN = 4;
    public static final int POST_ID_LEN = 4;
    public static final int POST_LIKES_LEN = 4;


    public PostRaf() {
        super(ADDRESS);
    }

    @Override
    public void add(Post obj) throws IOException {
        // userId recordLength id likes titleLength title captionLength caption
        // 4      4            4  4     2           n     2             m



        int newId = newId();
        seekEnd();
        writeInt(obj.getUserId());
        writeInt(getRecordLen(obj));
        writeInt(newId);
        writeInt(obj.getLikes());
        writeStr(obj.getTitle());
        writeStr(obj.getCaption());
    }

    @SneakyThrows
    @Override
    public void writeDefaults() {
       writeInt(500_000);
    }

    public ArrayList<Post> getPostsByUser(int userId) throws IOException {
        // userId recordLength id likes titleLength title captionLength caption
        // 4      4            4  4     2           n     2             m
        ArrayList<Post> posts = new ArrayList<>();
        seekStart();

        while (!isPointerAtEnd()) {
            int eachUserId = readInt();
            int recordLength = readInt();
            if (eachUserId != userId) {
                skip(recordLength);
                continue;
            }

            int id = readInt();
            int likes = readInt();
            String title = readStr();
            String caption = readStr();
            Post post = new Post(id , userId , likes , title , caption);
            posts.add(post);
        }

        return posts;
    }

    public Post getPostById(int postId) throws IOException {
        // userId recordLength id likes titleLength title captionLength caption
        // 4      4            4  4     2           n     2             m
        seekStart();

        while (!isPointerAtEnd()) {
            int eachUserId = readInt();
            int recordLength = readInt();
            int eachPostId = readInt();
            if (eachPostId != postId) {
                skip(recordLength - POST_ID_LEN); // read id, so subtract idLength from recordLength
                continue;
            }

            int likes = readInt();
            String title = readStr();
            String caption = readStr();
            return new Post(eachPostId , eachUserId , likes , title , caption);
        }

        return null;

    }

    public void likePostById(int postId) throws IOException {
        seekStart();

        while (!isPointerAtEnd()) {
            skip(USER_ID_LEN);
            int recordLength = readInt();
            int eachPostId = readInt();
            if (eachPostId != postId) {
                skip(recordLength - POST_ID_LEN); // read id, so subtract idLength from recordLength
                continue;
            }

            int likes = readInt();
            seek(getPointer() - POST_LIKES_LEN);
            writeInt(likes + 1);
            break;
        }

    }

    public int getUserPostsSize(int userId) throws IOException {
        int size = 0;
        seekStart();

        while (!isPointerAtEnd()) {
            int eachUserId = readInt();

            if (eachUserId == userId) size++;

            int recordLength = readInt();
            skip(recordLength);
        }

        return size;
    }


    private int getRecordLen(Post post) {
        // 4 + 2 + obj.getTitle().length() + 2 + obj.getCaption().length();
        int titleLen = post.getTitle().length();
        int captionLen = post.getCaption().length();
        return POST_ID_LEN + POST_LIKES_LEN + 2 + titleLen + 2 + captionLen; // 2 is string length -> use for writeUTF, readUTF
    }

}
