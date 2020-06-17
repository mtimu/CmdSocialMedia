package main.java.pages;

import main.java.account.Credentials;
import main.java.ui.Menu;
import main.java.ui.Printer;

public class MainPage extends Page {
    public final static String title = "java.Main Menu";
    public final static String[] menu = {
            "Profile" ,
            "Posts" ,
            "Followings" ,
            "Followers" ,
            "Exit"
    };

    private static final int CHOICE_PROFILE = 1;
    private static final int CHOICE_POSTS = 2;
    private static final int CHOICE_FOLLOWINGS = 3;
    private static final int CHOICE_FOLLOWERS = 4;
    private static final int CHOICE_EXIT = 0;

    @Override
    public void onStart() {
        Menu.printMainMenu();
        chooseFromMenu();
    }

    private void chooseFromMenu() {
        int choice = getInput().intIn();
        Class<? extends Page> pageClass = null;
        switch (choice) {
            case CHOICE_PROFILE:
                pageClass = ProfilePage.class;
                break;
            case CHOICE_POSTS:
                pageClass = PostsPage.class;
                break;
            case CHOICE_FOLLOWINGS:
                pageClass = FollowingPage.class;
                break;
            case CHOICE_FOLLOWERS:
                pageClass = FollowersPage.class;
                break;
            case CHOICE_EXIT:
                Credentials.getInstance().logout();
                pageClass = LoginPage.class;
                break;
        }

        if (pageClass == null) {
            Printer.printERR("Invalid Option");
            Printer.printLine();
            chooseFromMenu();
        }else
            getPageManager().addToStack(pageClass);
    }

}
