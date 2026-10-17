package bohac;

/**
 * Class that represents a response of a command
 */
public class CommandResponse {
    /**
     * Doesn't represent whether the command executed successfully, it simply keeping the program alive until exit command is executed
     */
    private boolean success;
    /**
     * Message sent back to the user
     */
    private String message;
    /**
     * If a message doesn't suffice, a Runnable is passed to the user instead
     */
    private Runnable runnable;

    private CommandResponse(boolean success) {
        this.success = success;
        Utils.clearScreen();
    }

    public CommandResponse(boolean success, String message) {
        this(success);
        this.message = message;
    }

    public CommandResponse(boolean success, Runnable runnable) {
        this(success);
        this.runnable = runnable;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public boolean hasMessage() {
        return message != null;
    }

    public boolean hasRunnable() {
        return runnable != null;
    }

    public Runnable getRunnable() {
        return runnable;
    }
}
