package main.java.model;

import lombok.Getter;
import main.java.database.io.Entity;

@Getter
public class Post {
    private int id;
    private String title;
    private String caption;


    public Post(int id , String title , String caption) {
        this.id = id;
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
