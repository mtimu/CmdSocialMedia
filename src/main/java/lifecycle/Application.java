package main.java.lifecycle;

import main.java.pages.Page;

/**
 * java.Main class of whole project to manage java.pages
 */
public final class Application {
    private PageManager manager;


    private Application(Class<? extends Page> currentPage) {
        manager = PageManager.getInstance();
        manager.addToStack(currentPage);
        loopBackStack();
    }

    public static void start(Class<? extends Page> startPage) {
        new Application(startPage);
    }

    /**
     * simple method, loop through stack java.pages while it's not empty
     * calling {@link Page} methods for each class
     */
    private void loopBackStack() {

        while (!manager.isStackEmpty()) {
            Page current = manager.getTopOfStack();
            current.onCreate();
            current.onStart();
            current.onDestroy();
        }

    }
}
