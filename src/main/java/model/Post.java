package main.java.model;

public class Post {
    private int id;
    private String title;
    private String caption;


    public Post(int id , String title , String caption) {
        this.id = id;
        this.title = title;
        this.caption = caption;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
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
