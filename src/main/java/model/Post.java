package main.java.model;

import lombok.Getter;

@Getter
public class Post {
    private int id;
    private int userId;
    private int likes;
    private String title;
    private String caption;


    public Post(int id , int userId , int likes , String title , String caption) {
        this.id = id;
        this.userId = userId;
        this.likes = likes;
        this.title = title;
        this.caption = caption;
    }


    public String getExpandedDetail() {
        StringBuilder builder = new StringBuilder();
        builder.append("Post{").append(getId()).append("}: ").append("\n")
                .append("Title: ").append(getTitle()).append("\tLikes: ").append(getLikes()).append("\n")
                .append("Caption: ").append(getCaption());
        return builder.toString();
    }

    @Override
    public String toString() {
        String raw = "Post{%d}:\n" +
                "\t Title: %s\n" +
                "\t Caption: %s";
        return String.format(raw, getId(), getTitle(), getCaption());
    }

}
