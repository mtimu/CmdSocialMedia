package main.java.account;

import main.java.database.Repository;
import main.java.database.RepositoryFactory;
import main.java.model.User;

import java.util.Optional;

public class Credentials {
    private static Credentials credentials;
    private final Repository repository;
    private User userInSystem;


    private Credentials() {
        repository = RepositoryFactory.getInstance();
    }

    public static Credentials getInstance() {
        if (credentials == null) {
            credentials = new Credentials();
        }

        return credentials;
    }

    public boolean authenticate(String username , String password) {
        Optional<User> user = repository.getUserByUserPass(username , password);
        user.ifPresent(this::setUserInSystem);
        return user.isPresent();
    }

    public User getUserInSystem() {
        return userInSystem;
    }

    private void setUserInSystem(User userInSystem) {
        this.userInSystem = userInSystem;
    }


    public void logout() {
        // TODO: 7/16/2020 complete this shit!
    }

}
