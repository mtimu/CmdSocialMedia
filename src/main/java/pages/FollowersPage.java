package main.java.pages;

import main.java.account.Credentials;
import main.java.database.Repository;
import main.java.database.RepositoryFactory;
import main.java.model.User;
import main.java.ui.Menu;
import main.java.ui.Printer;

import java.util.ArrayList;
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
        ArrayList<User> userFollowers = repository.getUserFollowers(credentials.getUserInSystem());
        userFollowers.forEach(user -> System.out.println(user.inlinePrintForm()));

        if (userFollowers.isEmpty()) {
            Printer.printERR(String.format("%s has no follower" , credentials.getUserInSystem().getName()));
            getInput().pressEnterToContinue();
            return;
        }

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
            Printer.println(user.get().profileForOtherUsers() , Printer.COLOR_YELLOW);
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
