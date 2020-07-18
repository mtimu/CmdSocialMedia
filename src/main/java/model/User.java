package main.java.model;


import lombok.Getter;

@Getter
public class User{
    private int id;
    private String username;
    private String password;
    private String name;
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

    public void setExtraInformation(int postsSize , int followersSize , int followingsSize) {
        this.postsSize = postsSize;
        this.followersSize = followersSize;
        this.followingsSize = followingsSize;
    }

    public String inlinePrintForm() {
        return String.format("UserId: %d\nName:%s Username:%s Bio: %s\n" , getId() , getName() , getUsername() , getBio());
    }

    public String profileForOtherUsers() {
        String raw = "%s Profile: \n" +
                "\t\t===============\n" +
                "\t\tPosts\tFollowers\tFollowings\n" +
                "\t\t %d \t\t   %d \t\t\t %d\n" +
                "\t\tBio: %s";

        return String.format(raw ,
                getName() ,
                postsSize ,
                followersSize ,
                followingsSize ,
                getBio());
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
                "\t\tBio: %s";

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
