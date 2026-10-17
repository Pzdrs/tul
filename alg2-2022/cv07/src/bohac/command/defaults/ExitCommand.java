package bohac.command.defaults;

import bohac.CommandLine;
import bohac.command.Command;

public class ExitCommand extends Command {
    private final CommandLine commandLine;

    public ExitCommand(CommandLine commandLine) {
        super("exit");
        this.commandLine = commandLine;
    }

    @Override
    public String getDescription() {
        return "Exits from the terminal";
    }

    @Override
    public boolean execute(String label, String[] args) {
        commandLine.setRunning(false);
        return true;
    }
}
