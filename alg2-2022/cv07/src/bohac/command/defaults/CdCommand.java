package bohac.command.defaults;

import bohac.CommandLine;
import bohac.command.Command;
import bohac.command.CommandArgument;

import java.io.File;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.List;

public class CdCommand extends Command {
    private final CommandLine commandLine;

    public CdCommand(CommandLine commandLine) {
        super("cd");
        this.commandLine = commandLine;
    }

    @Override
    public List<CommandArgument> getArguments() {
        return List.of(
                new CommandArgument("directory", CommandArgument.Type.STRING, true)
        );
    }

    @Override
    public String getDescription() {
        return "Changes the current working directory according to the argument.\n" +
                "   If no argument is passed in, the user is taken to the project root";
    }

    @Override
    public boolean execute(String label, String[] args) {
        if (args.length == 0) {
            commandLine.setWorkingDirectory(new File(System.getProperty("user.dir")).toPath());
            return true;
        }
        String arg = args[0];
        if (arg.equals("..")) {
            commandLine.setWorkingDirectory(commandLine.getWorkingDirectory().getParent());
            return true;
        }
        try {
            File file = new File(arg);
            if (!file.isAbsolute()) file = Path.of(commandLine.getWorkingDirectory().toString(), arg).toFile();
            if (!file.exists()) {
                System.out.println(getErrorMessage("No such file or directory", arg));
                return false;
            }
            if (!file.isDirectory()) {
                System.out.println(getErrorMessage("Not a directory", arg));
                return false;
            }
            commandLine.setWorkingDirectory(file.toPath());
        } catch (InvalidPathException e) {
            System.out.println(getErrorMessage("Invalid path", arg));
            return false;
        } catch (Exception e) {
            System.out.println(getErrorMessage(e.getMessage(), arg));
            return false;
        }
        return true;
    }
}
