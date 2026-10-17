package bohac.command.defaults;

import bohac.CommandLine;
import bohac.command.Command;
import bohac.command.CommandArgument;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CopyFileCommand extends Command {
    private final CommandLine commandLine;

    public CopyFileCommand(CommandLine commandLine) {
        super("cp");
        this.commandLine = commandLine;
    }

    @Override
    public String getDescription() {
        return "Creates a copy of a file";
    }

    @Override
    public List<String> getAliases() {
        return List.of("copy");
    }

    @Override
    public List<CommandArgument> getArguments() {
        return List.of(
                new CommandArgument("fileToCopy", CommandArgument.Type.STRING),
                new CommandArgument("newFileName", CommandArgument.Type.STRING)
        );
    }

    @Override
    public boolean execute(String label, String[] args) {
        File newFile = Path.of(commandLine.getWorkingDirectory().toString(), args[1]).toFile();
        if (newFile.exists()) {
            System.out.println(getErrorMessage("file exists", args[1]));
            return false;
        }
        try {
            Files.copy(Path.of(commandLine.getWorkingDirectory().toString(), args[0]), newFile.toPath());
        } catch (IOException e) {
            System.out.println(getErrorMessage(e.getMessage()));
            return false;
        }
        return true;
    }
}
