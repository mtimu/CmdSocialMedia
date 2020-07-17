package main.java.pages;

import main.java.account.Credentials;
import main.java.ui.Menu;
import main.java.ui.Printer;

public class LoginPage extends Page {
    public static final String TITLE = "Login Menu";
    public static final String[] MENU = {"Add User" , "Login" , "EXIT"};
    public static final int ADD_USER = 1;
    public static final int LOGIN = 2;


    @Override
    public void onStart() {
        Printer.println("Welcome, Sharing is caring.");
        Menu.printLoginMenu();
        int choice = getInput().intIn();
        switch (choice) {
            case ADD_USER:
                getPageManager().addToStack(AddUserPage.class);
                break;

            case LOGIN:
                auth();
                break;

            default:
                // EXIT
        }
    }

    private void auth() {
        Printer.println("Enter your Credentials:");
        String username = getInput().lineIn("Username").trim();
        String password = getInput().lineIn("Password").trim();

        boolean authenticate = Credentials.getInstance().authenticate(username , password);

        if (authenticate) {
            getPageManager().addToStack(MainPage.class);
            return;
        }


        Printer.printERR("Wrong Login Information");
        Printer.printLine();
        onStart();
    }

}
