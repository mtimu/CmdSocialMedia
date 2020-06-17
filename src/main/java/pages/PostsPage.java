package main.java.pages;

import main.java.database.Repository;
import main.java.account.Credentials;
import main.java.database.RepositoryFactory;
import main.java.model.Post;
import main.java.ui.Menu;
import main.java.ui.Printer;

public class PostsPage extends Page {
    public static String title = "Posts Menu";
    public static String[] menu = {
            "Show Post" ,
            "Add Post" ,
            "Exit"
    };

    public static final int ADD_POST = 2;
    public static final int EXIT = 0;

    private Credentials credentials;
    private Repository repository;

    @Override
    public void onCreate() {
        super.onCreate();
        repository = RepositoryFactory.getInstance();
        credentials = Credentials.getInstance();
    }

    @Override
    public void onStart() {
        repository.getPostsBy(credentials.getUserInSystem()).forEach(System.out::println);
        Printer.printLine();
        Menu.printPostsMenu();
        chooseFromMenu();
    }

    private void chooseFromMenu() {
        int choice = getInput().intIn();
        switch (choice) {
            case ADD_POST:
                addPost();
                break;
            case EXIT:
                getPageManager().addToStack(MainPage.class);
                break;
            default:
                showPostDetail(choice);
        }
    }

    private void addPost() {
        Printer.printLine();
        String title = getInput().lineIn("Post Title");
        String caption = getInput().lineIn("Post Caption");
        Post newPost = new Post(-1 , title , caption);

        boolean success = repository.addPostFor(credentials.getUserInSystem() , newPost);

        if (success) Printer.println("Post Added Successfully",Printer.COLOR_GREEN);
        else Printer.printERR("Failed To Add Post");
        Printer.printLine();

        onStart();
    }


    private void showPostDetail(int postId) {
        Post post = repository.getPostById(postId);

        if (post == null) Printer.printERR("No Post with This Id");
        else Printer.println(post.toString() , Printer.COLOR_YELLOW);

        onStart();
    }

}
