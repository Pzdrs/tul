package bohac;

import bohac.command.CommandMap;
import bohac.command.SimpleCommandMap;
import bohac.command.defaults.*;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Singleton enforced class representing the command line
 */
public class CommandLine {
    private static final CommandLine INSTANCE = new CommandLine();
    private static final Scanner scanner = new Scanner(System.in);
    private final CommandMap commandMap = new SimpleCommandMap();
    private boolean running;
    private Path workingDirectory;

    private CommandLine() {
        running = true;
        setWorkingDirectory(new File(System.getProperty("user.dir")).toPath());
        registerCommands();
    }

    public boolean prompt() {
        System.out.print(workingDirectory + ">");
        Map.Entry<Boolean, String> result = commandMap.dispatch(scanner.nextLine());
        if (!result.getKey() && result.getValue() != null) {
            System.out.println(result.getValue());
        }
        return running;
    }

    private void registerCommands() {
        commandMap.registerAll(List.of(
                new PwdCommand(this),
                new RenameFileCommand(this),
                new HelpCommand(commandMap),
                new CdCommand(this),
                new LsCommand(this),
                new ClearCommand(),
                new ExitCommand(this),
                new CopyFileCommand(this),
                new CreateDirectoryCommand(this),
                new CreateFileCommand(this),
                new DeleteFileCommand(this)
        ));
    }

    public void setWorkingDirectory(Path workingDirectory) {
        this.workingDirectory = workingDirectory.normalize();
    }

    public Path getWorkingDirectory() {
        return workingDirectory;
    }

    public static CommandLine getInstance() {
        return INSTANCE;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isRunning() {
        return running;
    }
}
