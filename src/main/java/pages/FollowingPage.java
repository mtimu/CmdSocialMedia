package main.java.pages;

import main.java.account.Credentials;
import main.java.database.Repository;
import main.java.database.RepositoryFactory;
import main.java.model.Post;
import main.java.model.User;
import main.java.ui.Menu;
import main.java.ui.Printer;

import java.util.ArrayList;

public class FollowingPage extends Page {
    public static String title = "Following Menu";
    public static String[] menu = {
            "Following Page" ,
            "Add Following" ,
            "Exit"
    };

    public static final int EXIT = 0;
    public static final int SHOW_POSTS = 1;
    public static final int ADD_FOLLOWING = 2;

    private Credentials credentials;
    private Repository repository;

    @Override
    public void onCreate() {
        super.onCreate();
        credentials = Credentials.getInstance();
        repository = RepositoryFactory.getInstance();
    }

    @Override
    public void onStart() {
        followingMenu();
    }

    private void followingMenu() {
        repository.getUserFollowings(credentials.getUserInSystem()).forEach(user -> System.out.println(user.inlinePrintForm()));
        Printer.printLine();
        Menu.printFollowingMenu();
        int choice = getInput().intIn();
        switch (choice) {
            case ADD_FOLLOWING:
                addFollowingMenu();
                break;
            case EXIT:
                getPageManager().addToStack(MainPage.class);
                break;
            default:
                followingProfileMenu(choice);
        }

        if (choice != EXIT) followingMenu();
    }

    private void addFollowingMenu() {
        repository.getAllUsers().stream()
                .filter(user -> user.getId() != credentials.getUserInSystem().getId())
                .forEach(user -> System.out.println(user.inlinePrintForm()));

        Printer.printLine();
        Menu.printAddFollowingMenu();
        Printer.printERR("**: Enter Zero to Exit");
        addFollowing();
    }

    private void addFollowing() {
        int choice = getInput().intIn();
        if (choice == EXIT) return;

        // follow a user
        boolean success = repository.followUser(credentials.getUserInSystem() , choice);

        if (success) Printer.println("User added to your followings successfully" , Printer.COLOR_GREEN);
        else Printer.printERR("Add User To followings failed!");
        addFollowing();
    }

    private void followingProfileMenu(int followingId) {
        User user = repository.getUserFollowing(credentials.getUserInSystem().getId() , followingId);

        if (user == null) Printer.printERR("User Is Not your Followings");
        else {
            System.out.println(user);
            Printer.printLine();
            followingProfile(user);
        }
    }

    private void followingProfile(User following) {
        Menu.printFollowingProfileMenu();
        int choice = getInput().intIn();

        if (choice == EXIT) return;

        if (choice == SHOW_POSTS)
            followingPostsMenu(following);

        followingProfileMenu(following.getId());
    }

    private void followingPostsMenu(User following) {
        ArrayList<Post> posts = repository.getPostsBy(following.getId());
        posts.forEach(System.out::println);
        Printer.printLine();
        Menu.printUserPostsMenu();
        int choice = getInput().intIn();

        if (choice == EXIT) return;

        followingSinglePostMenu(getPostById(posts,choice));

        followingPostsMenu(following);
    }

    private void followingSinglePostMenu(Post post) {
        Printer.print(post.getExpandedDetail() , Printer.COLOR_YELLOW);
        Printer.printLine();
        Menu.printPostDetailMenu();

        String choice = getInput().lineIn();

        if (!choice.toLowerCase().equals("l")) return;

        boolean success = repository.likePostById(post.getId());
        if (success) Printer.println("Post Liked Successfully" , Printer.COLOR_GREEN);
        else Printer.printERR("Failed to Like Post!");
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    private Post getPostById(ArrayList<Post> posts , int postId) {
        return posts.stream().filter(post -> post.getId() == postId).findFirst().get();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        credentials = null;
        repository = null;
    }
}
