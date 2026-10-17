package bohac.command.defaults;

import bohac.CommandLine;
import bohac.command.Command;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PwdCommand extends Command {
    private CommandLine commandLine;

    public PwdCommand(CommandLine commandLine) {
        super("pwd");
        this.commandLine = commandLine;
    }

    @Override
    public List<String> getAliases() {
        return List.of("wd", "dir");
    }

    @Override
    public String getDescription() {
        return "Prints out the current working directory";
    }

    @Override
    public boolean execute(String label, String[] args) {
        System.out.println(commandLine.getWorkingDirectory());
        return true;
    }
}
