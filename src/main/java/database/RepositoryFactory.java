package main.java.database;

public final class RepositoryFactory {

    public static Repository getInstance() {
        return FileRepository.getInstance();
    }

}
