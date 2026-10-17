package bohac.command.defaults;

import bohac.CommandLine;
import bohac.command.Command;
import bohac.command.CommandArgument;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

public class RenameFileCommand extends Command {
    private final CommandLine commandLine;

    public RenameFileCommand(CommandLine commandLine) {
        super("rename");
        this.commandLine = commandLine;
    }

    @Override
    public List<CommandArgument> getArguments() {
        return List.of(
                new CommandArgument("oldName", CommandArgument.Type.STRING),
                new CommandArgument("newName", CommandArgument.Type.STRING)
        );
    }

    @Override
    public List<String> getAliases() {
        return List.of("mv");
    }

    @Override
    public String getDescription() {
        return "Renames a file or a directory (relative paths only)";
    }

    @Override
    public boolean execute(String label, String[] args) {
        File oldFile = Path.of(commandLine.getWorkingDirectory().toString(), args[0]).toFile();
        File newFile = Path.of(commandLine.getWorkingDirectory().toString(), args[1]).toFile();

        if (newFile.exists()) {
            System.out.println(getErrorMessage("file exists", args[1]));
            return false;
        }
        return oldFile.renameTo(newFile);
    }
}
