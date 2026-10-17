/**
 * MenuItem wrapper, implements Runnable thus overriding the run() method
 */
public class MenuItem implements Runnable {
    private String description;
    private Runnable action;

    public MenuItem(String description, Runnable action) {
        this.description = description;
        this.action = action;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Custom implementation of the Runnable run() method
     */
    @Override
    public void run() {
        if (action != null) action.run();
        // Visual padding
        System.out.println();
        // Display the menu after every iteration - instead of using an infinite loop in Main.main()
        Main.menu.get(Utils.printMenu(Main.menu) - 1).run();
    }
}
