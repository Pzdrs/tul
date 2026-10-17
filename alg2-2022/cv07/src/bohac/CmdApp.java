package bohac;

public class CmdApp {
    public static void main(String[] args) {
        CommandLine commandLine = CommandLine.getInstance();
        do {
            commandLine.prompt();
        } while (commandLine.isRunning());
    }
}
