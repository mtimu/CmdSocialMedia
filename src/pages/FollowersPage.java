package pages;

import account.Credentials;
import database.Repository;
import database.RepositoryFactory;
import model.User;
import ui.Menu;
import ui.Printer;

public class FollowersPage extends Page {
    public static String title = "Follower Menu";
    public static String[] menu = {
            "User Page",
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
        repository.getFollowerBy(credentials.getUserInSystem()).forEach(user -> System.out.println(user.inlinePrintForm()));
        Printer.printLine();
        Menu.printFollowerMenu();
        followersMenu();
    }

    private void followersMenu() {
        int choice = getInput().intIn();

        if (choice == EXIT) getPageManager().addToStack(MainPage.class);
        else showUserProfile(choice);
    }

    private void showUserProfile(int userId) {
        User user = repository.getUserById(credentials.getUserInSystem().getId(), userId);

        if (user == null) Printer.printERR("User Is Not your Follower");
        else {
            System.out.println(user);
            getInput().pressEnterToContinue();
        }

        onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        credentials = null;
        repository = null;
    }
}
