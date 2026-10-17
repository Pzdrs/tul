package bohac.hra;

import bohac.Utils;

import java.util.AbstractMap;

public record Menu(MenuItem... menuItems) {
    static final Runnable EXIT = () -> System.exit(0);
    static final Runnable CLEAR = () -> System.out.println(System.lineSeparator().repeat(30));

    record MenuItem(String description, Runnable runnable) implements Runnable {
        /**
         * Custom implementation of the Runnable run() method
         */
        @Override
        public void run() {
            if (runnable != null) runnable.run();
            // Visual padding
            System.out.println();
        }
    }

    public void prompt() {
        System.out.println("Choose an option: ");
        for (int i = 0; i < menuItems.length; i++) {
            System.out.printf("[%d] - %s\n", i + 1, menuItems[i].description());
        }
        menuItems[Utils.promptNumericInt("> ", new AbstractMap.SimpleEntry<>(1, menuItems.length)) - 1].run();
        // Recursive call - the only way this loop stops is choosing the exit option
        prompt();
    }
}
