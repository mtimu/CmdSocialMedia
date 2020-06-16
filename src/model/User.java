package model;


public class User {
    private int id;
    private String name;
    private String username;
    private String password;
    private String bio;
    private int postsSize;
    private int followingsSize;
    private int followersSize;


    public User(int id ,
                String name ,
                String username ,
                String password ,
                String bio) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.bio = bio;
    }

    public void setSize(int postsSize , int followersSize , int followingsSize) {
        this.postsSize = postsSize;
        this.followersSize = followersSize;
        this.followingsSize = followingsSize;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getBio() {
        return bio;
    }


    public String inlinePrintForm() {
        return String.format("UserId: %d\nName:%s Username:%s Bio: %s\n" , getId() , getName() , getUsername() , getBio());
    }

    @Override
    public String toString() {
        String raw = "%s Profile: \n" +
                "\tCredentials: \n" +
                "\t\tUsername: %s\n" +
                "\t\tPassword: %s\n" +
                "\t\t===============\n" +
                "\t\tPosts\tFollowers\tFollowings\n" +
                "\t\t %d \t\t   %d \t\t\t %d\n" +
                "\t\tBio:\n" +
                "\t\t%s";

        return String.format(raw,
                getName(),
                getUsername(),
                getPassword(),
                postsSize,
                followersSize,
                followingsSize,
                getBio());
    }
}
