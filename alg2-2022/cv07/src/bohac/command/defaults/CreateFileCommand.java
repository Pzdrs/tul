package bohac.command.defaults;

import bohac.CommandLine;
import bohac.command.Command;
import bohac.command.CommandArgument;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class CreateFileCommand extends Command {
    private final CommandLine commandLine;

    public CreateFileCommand(CommandLine commandLine) {
        super("touch");
        this.commandLine = commandLine;
    }

    @Override
    public String getDescription() {
        return "Creates a file";
    }

    @Override
    public List<String> getAliases() {
        return List.of("createFile");
    }

    @Override
    public List<CommandArgument> getArguments() {
        return List.of(
                new CommandArgument("name", CommandArgument.Type.STRING)
        );
    }

    @Override
    public boolean execute(String label, String[] args) {
        File file = Path.of(commandLine.getWorkingDirectory().toString(), args[0]).toFile();
        if (file.exists()) {
            System.out.println(getErrorMessage("file exists", args[0]));
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            System.out.println(getErrorMessage(e.getMessage(), args[0]));
            return false;
        }
    }
}
