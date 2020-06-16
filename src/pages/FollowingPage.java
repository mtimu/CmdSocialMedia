package pages;

import account.Credentials;
import database.Repository;
import database.RepositoryFactory;
import model.Post;
import model.User;
import ui.Menu;
import ui.Printer;

public class FollowingPage extends Page {
    public static String title = "Following Menu";
    public static String[] menu = {
            "Following Page" ,
            "Add Following" ,
            "Exit"
    };

    public static final int EXIT = 0;
    public static final int ADD_FOLLOWING = 2;
    public static final int SHOW_POSTS = 1;

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
        repository.getFollowingsBy(credentials.getUserInSystem()).forEach(user -> System.out.println(user.inlinePrintForm()));
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

        followingMenu();
    }

    private void addFollowingMenu() {
        repository.getAllUsers().forEach(user -> System.out.println(user.inlinePrintForm()));
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

        if (success) Printer.println("Add User to your followings successfully" , Printer.COLOR_GREEN);
        else Printer.printERR("Add User To followings failed!");
        addFollowing();
    }

    private void followingProfileMenu(int userId) {
        User user = repository.getUserById(credentials.getUserInSystem().getId() , userId);

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
        repository.getPostsBy(following).forEach(System.out::println);
        Printer.printLine();
        Menu.printUserPostsMenu();
        int choice = getInput().intIn();

        if (choice == EXIT) return;

        followingSinglePostMenu(following, choice);

        followingPostsMenu(following);
    }

    private void followingSinglePostMenu(User following, int postId) {
        Post post = repository.getUserSinglePostById(following , postId);

        Printer.print(post.getExpandedDetail() , Printer.COLOR_YELLOW);
        Printer.printLine();
        Menu.printPostDetailMenu();

        String choice = getInput().lineIn();

        if (!choice.toLowerCase().equals("l")) return;

        boolean success = repository.likePostById(postId);
        if (success) Printer.println("Post Liked Successfully" , Printer.COLOR_GREEN);
        else Printer.printERR("Failed to Like Post!");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        credentials = null;
        repository = null;
    }
}
