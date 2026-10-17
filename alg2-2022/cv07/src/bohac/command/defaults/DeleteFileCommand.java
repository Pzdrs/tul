package bohac.command.defaults;

import bohac.CommandLine;
import bohac.command.Command;
import bohac.command.CommandArgument;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class DeleteFileCommand extends Command {
    private final CommandLine commandLine;

    public DeleteFileCommand(CommandLine commandLine) {
        super("rm");
        this.commandLine = commandLine;
    }

    @Override
    public List<String> getAliases() {
        return List.of("remove", "delete", "del");
    }

    @Override
    public List<CommandArgument> getArguments() {
        return List.of(
                new CommandArgument("file", CommandArgument.Type.STRING),
                new CommandArgument("recursive (-r)", CommandArgument.Type.STRING, true)
        );
    }

    @Override
    public String getDescription() {
        return "Deletes a file or a directory";
    }

    @Override
    public boolean execute(String label, String[] args) {
        File file = Path.of(commandLine.getWorkingDirectory().toString(), args[0]).toFile();
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null || files.length == 0) {
                return file.delete();
            } else {
                if (Arrays.stream(args).toList().contains("-r")) {
                    try {
                        Files.walk(file.toPath())
                                .map(Path::toFile)
                                .sorted(Comparator.reverseOrder())
                                .forEach(File::delete);
                        return true;
                    } catch (IOException e) {
                        System.out.println(getErrorMessage(e.getMessage(), args[0]));
                        return false;
                    }
                } else {
                    System.out.println(getErrorMessage("directory not empty, remove all files in the directory or use the -r switch", args[0]));
                    return false;
                }
            }
        } else return file.delete();
    }
}
