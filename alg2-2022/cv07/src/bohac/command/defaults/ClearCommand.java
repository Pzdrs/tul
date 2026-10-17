package bohac.command.defaults;

import bohac.command.Command;

public class ClearCommand extends Command {
    public ClearCommand() {
        super("clear");
    }

    @Override
    public String getDescription() {
        return "Clears the terminal";
    }

    @Override
    public boolean execute(String label, String[] args) {
        System.out.println(System.lineSeparator().repeat(50));
        return true;
    }
}
