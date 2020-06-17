package main.java.ui;

import main.java.pages.FollowersPage;
import main.java.pages.FollowingPage;
import main.java.pages.MainPage;
import main.java.pages.PostsPage;

/**
 * simple class to build program menus
 */
// TODO: 6/13/2020 create a pool pattern for menus too
public class Menu {
    public static final int INVALID_OPTION_CHOOSE_AGAIN = 1;
    public static final int INVALID_OPTION_MAIN_MENU = 0;


    public static void printInvalidOptionMenu() {
        String title = "Invalid Choice Menu";
        String[] menu = {
                "Choose Again",
                "java.Main Menu"
        };

        String[] indices = createIndicesFrom(menu);
        System.out.println(createMenuFrom(title , menu , indices));;
    }

    public static void printMainMenu() {
        String[] indices = createIndicesFrom(MainPage.menu);
        System.out.println(createMenuFrom(MainPage.title , MainPage.menu , indices));
    }

    public static void printPostsMenu() {
        String[] indices = createIndicesFrom(PostsPage.menu);
        indices[0] = StrHelper.center("Id" , PostsPage.menu[0].length());
        System.out.println(createMenuFrom(PostsPage.title , PostsPage.menu , indices));
    }

    public static void printFollowerMenu() {
        String[] indices = createIndicesFrom(FollowersPage.menu);
        indices[0] = StrHelper.center("Id" , FollowersPage.menu[0].length());
        System.out.println(createMenuFrom(FollowersPage.title , FollowersPage.menu , indices));
    }

    public static void printFollowingMenu() {
        String[] indices = createIndicesFrom(FollowingPage.menu);
        indices[0] = StrHelper.center("Id" , FollowingPage.menu[0].length());
        System.out.println(createMenuFrom(FollowingPage.title , FollowingPage.menu , indices));
    }

    public static void printAddFollowingMenu() {
        String title = "Add Following Menu";
        String[] menu = {
                "Follow" ,
                "Exit"
        };

        String[] indices = createIndicesFrom(menu);
        indices[0] = StrHelper.center("Id" , menu[0].length());

        System.out.println(createMenuFrom(title , menu , indices));
    }

    public static void printFollowingProfileMenu() {
        String title = "Following Profile Menu";
        String[] menu = {
                "Show Posts" ,
                "Exit"
        };

        String[] indices = createIndicesFrom(menu);

        System.out.println(createMenuFrom(title , menu , indices));
    }

    public static void printUserPostsMenu() {
        String title = "User Posts Menu";
        String[] menu = {
                "Post Detail" ,
                "Exit"
        };

        String[] indices = createIndicesFrom(menu);
        indices[0] = StrHelper.center("Id" , menu[0].length());

        System.out.println(createMenuFrom(title , menu , indices));
    }

    public static void printPostDetailMenu() {
        String title = "Post Detail Menu";
        String[] menu = {
                "Like" ,
                "Exit"
        };

        String[] indices = createIndicesFrom(menu);
        indices[0] = StrHelper.center("L" , menu[0].length());

        System.out.println(createMenuFrom(title , menu , indices));
    }

    /**
     * @param rawTitle title of menu
     * @param raw menu items
     * @param indices menu indices
     * @return formatted menu from title, menu items and indices
     */
    private static String createMenuFrom(String rawTitle , String[] raw , String[] indices) {

        String menu = "\t" + StrHelper.join(raw , "\t|\t");
        String strIndices = "\t" + StrHelper.join(indices , "\t \t");
        String title = "\t\t" + StrHelper.center(rawTitle , menu.length());

        return title.concat("\n").concat(menu).concat("\n").concat(strIndices);
    }


    /**
     * @param raw menu items
     * @return indices for menu items
     */
    private static String[] createIndicesFrom(String[] raw) {
        String[] indices = new String[raw.length];
        for (int i = 0; i < raw.length; i++) {
            Object index = (i + 1) == raw.length ? 0 : i + 1;
            indices[i] = StrHelper.center(index , raw[i].length());
        }
        return indices;
    }


}
