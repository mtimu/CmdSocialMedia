package lifecycle;

import pages.Page;

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

    private void loopBackStack() {

        while (!manager.isStackEmpty()) {
            Page current = manager.getTopOfStack();
            current.onCreate();
            current.onStart();
            current.onDestroy();
        }

    }
}
