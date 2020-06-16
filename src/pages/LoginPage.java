package pages;

import account.Credentials;
import model.User;
import ui.Printer;

public class LoginPage extends Page {


    @Override
    public void onStart() {
        Printer.println("Welcome, Sharing is caring.");
        Printer.println("Enter your Credentials:");
        auth();
    }

    private void auth() {
        String username = getInput().lineIn("Username").trim();
        String password = getInput().lineIn("Password").trim();

        User user = Credentials.getInstance().authenticate(username , password);

        if (user != null) {
            getPageManager().addToStack(MainPage.class);
            return;
        }


        Printer.printERR("Wrong Login Information");
        Printer.printLine();
        auth();
    }

}
