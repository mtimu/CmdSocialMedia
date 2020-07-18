package main.java.pages;

import main.java.database.Repository;
import main.java.database.RepositoryFactory;
import main.java.model.User;
import main.java.ui.Printer;

public class AddUserPage extends Page {

    private Repository repository;


    @Override
    public void onCreate() {
        super.onCreate();
        repository = RepositoryFactory.getInstance();
    }

    @Override
    public void onStart() {
        Printer.println("Add User" , Printer.COLOR_BLUE);
        String name = getInput().lineIn("Name");
        String username = getInput().lineIn("Username");
        String password = getInput().lineIn("Password");
        String bio = getInput().lineIn("Bio");
        User user = new User(-1 , name , username , password , bio);
        repository.addUser(user);
        printResult();
        getPageManager().addToStack(LoginPage.class);
    }

    private void printResult() {
        String message = "User added successfully";
        Printer.println(message , Printer.COLOR_GREEN);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        repository = null;
    }
}
