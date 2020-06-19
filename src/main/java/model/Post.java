package main.java.model;

import lombok.Getter;

@Getter
public class Post {
    private int id;
    private int userId;
    private String title;
    private String caption;


    public Post(int id , int userId , String title , String caption) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.caption = caption;
    }


    public String getExpandedDetail() {
        // TODO: 6/17/2020 complete this part
        return "";
    }

    @Override
    public String toString() {
        String raw = "Post{%d}:\n" +
                "\t Title: %s\n" +
                "\t Caption: %s";
        return String.format(raw, getId(), getTitle(), getCaption());
    }

}
