package bohac.command.defaults;

import bohac.CommandLine;
import bohac.command.Command;
import bohac.command.CommandArgument;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

public class CreateDirectoryCommand extends Command {
    private final CommandLine commandLine;

    public CreateDirectoryCommand(CommandLine commandLine) {
        super("mkdir");
        this.commandLine = commandLine;
    }

    @Override
    public String getDescription() {
        return "Creates a directory";
    }

    @Override
    public List<String> getAliases() {
        return List.of("createDirectory");
    }

    @Override
    public List<CommandArgument> getArguments() {
        return List.of(
                new CommandArgument("name", CommandArgument.Type.STRING)
        );
    }

    @Override
    public boolean execute(String label, String[] args) {
        File directory = Path.of(commandLine.getWorkingDirectory().toString(), args[0]).toFile();
        if (directory.exists()) {
            System.out.println(getErrorMessage("file exists", args[0]));
            return false;
        }
        return directory.mkdir();
    }
}
