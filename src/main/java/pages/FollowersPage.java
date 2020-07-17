package main.java.pages;

import main.java.account.Credentials;
import main.java.database.Repository;
import main.java.database.RepositoryFactory;
import main.java.model.User;
import main.java.ui.Menu;
import main.java.ui.Printer;

import java.util.Optional;

public class FollowersPage extends Page {
    public static String title = "Follower Menu";
    public static String[] menu = {
            "User Page" ,
            "Exit"
    };

    public static final int EXIT = 0;

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
        repository.getUserFollowers(credentials.getUserInSystem()).forEach(user -> System.out.println(user.inlinePrintForm()));
        Printer.printLine();
        Menu.printFollowerMenu();
        followersMenu();
    }

    private void followersMenu() {
        int choice = getInput().intIn();

        if (choice == EXIT) getPageManager().addToStack(MainPage.class);
        else showUserProfile(choice);
    }

    private void showUserProfile(int followerId) {
        Optional<User> user = repository.getUserFollower(credentials.getUserInSystem().getId() , followerId);


        if (user.isPresent()) {
            System.out.println(user);
            getInput().pressEnterToContinue();
        } else Printer.printERR("User Is Not your Follower");

        onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        credentials = null;
        repository = null;
    }
}
