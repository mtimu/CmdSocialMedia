package lifecycle;

import pages.Page;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public final class PageManager {
    private static PageManager manager;

    private Map<String, Page> pool;

    private Stack<Page> backStack;


    private PageManager() {
        pool = new HashMap<>();
        backStack = new Stack<>();
    }

    public static PageManager getInstance() {
        if (manager == null)
            manager = new PageManager();

        return manager;
    }


    public void addToStack(Class<? extends Page> pageClass) {
        if (pageClass == null)
            return;

        Page page;
        try {
            page = pageForClass(pageClass);
            backStack.push(page);
        } catch (Exception e) {
            System.err.println("Could'nt Load Page To Stack!");
            e.printStackTrace();
        }

    }

    public boolean isStackEmpty() {
        return backStack.isEmpty();
    }

    Page getTopOfStack() {
        return backStack.pop();
    }

    Stack<? extends Page> getBackStack() {
        return backStack;
    }

    private  <T extends Page> Page pageForClass(Class<T> destination) throws Exception {
        String name = destination.getName();
        Page page = pool.putIfAbsent(name , destination.getDeclaredConstructor().newInstance());

        if (page == null)
            page = pool.get(name);

        return page;
    }

}
