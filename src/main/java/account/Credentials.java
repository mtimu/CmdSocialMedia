package main.java.account;

import main.java.model.User;
import main.java.model.UserBuilder;

public class Credentials {
    private static Credentials credentials;
    private User userInSystem;


    private Credentials() {

    }

    public static Credentials getInstance() {
        if (credentials == null) {
            credentials = new Credentials();
        }

        return credentials;
    }

    public User authenticate(String username , String password) {
        // TODO: 6/13/2020 check in system
        User user = new UserBuilder(12 , "Mehdi" , "Ali" , "123").bio("Shine Bright Like A Diamond").create();
        setUserInSystem(user);
        return user;
    }

    public User getUserInSystem() {
        return userInSystem;
    }

    private void setUserInSystem(User userInSystem) {
        this.userInSystem = userInSystem;
    }


    public void logout() {

    }

}
