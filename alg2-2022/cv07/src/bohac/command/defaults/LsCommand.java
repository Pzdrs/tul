package bohac.command.defaults;

import bohac.CommandLine;
import bohac.command.Command;

import java.io.File;

public class LsCommand extends Command {
    private CommandLine commandLine;

    public LsCommand(CommandLine commandLine) {
        super("ls");
        this.commandLine = commandLine;
    }

    @Override
    public String getDescription() {
        return "Lists all the files and directories";
    }

    @Override
    public boolean execute(String label, String[] args) {
        File[] files = commandLine.getWorkingDirectory().toFile().listFiles();
        if (files == null) return false;
        System.out.printf("total %d\n", files.length);
        for (File file : files) {
            if (file.isFile()) {
                System.out.printf("%s (%d bytes)\n", file.getName(), file.length());
            } else {
                File[] children = file.getAbsoluteFile().listFiles();
                System.out.printf("%s (%d children)\n", file.getName(), children == null ? 0 : children.length);
            }
        }
        return true;
    }
}
