package bohac.command.defaults;

import bohac.command.Command;
import bohac.command.CommandArgument;
import bohac.command.CommandMap;
import bohac.command.SimpleCommandMap;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HelpCommand extends Command {
    private final SimpleCommandMap commandMap;

    public HelpCommand(CommandMap commandMap) {
        super("help");
        this.commandMap = (SimpleCommandMap) commandMap;
    }

    @Override
    public List<CommandArgument> getArguments() {
        return List.of(
                new CommandArgument("command", CommandArgument.Type.STRING, true)
        );
    }

    @Override
    public String getDescription() {
        return "Displays all the available commands";
    }

    @Override
    public boolean execute(String label, String[] args) {
        int BORDER_LENGTH = 50;
        if (args.length == 0) {
            System.out.println("=".repeat(BORDER_LENGTH));
            commandMap.getKnownCommands().entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(entry -> System.out.println(getCommandHelp(entry.getValue())));
            System.out.println("=".repeat(BORDER_LENGTH));
        } else {
            String arg = args[0];
            Command command = commandMap.getCommand(arg);
            if (command == null) {
                System.out.println(Command.NOT_FOUND_MESSAGE.apply(arg));
                return false;
            }
            System.out.println("=".repeat(BORDER_LENGTH));
            System.out.println(getCommandHelp(command));
            System.out.println("=".repeat(BORDER_LENGTH));
        }
        return true;
    }

    private String getCommandHelp(Command command) {
        return new StringBuilder(command.getName())
                .append(getAliasesString(command.getAliases()))
                .append(": ")
                .append(command.getUsage())
                .append(System.lineSeparator())
                .append(" - ")
                .append(command.getDescription()).toString();
    }

    private String getAliasesString(List<String> aliases) {
        if (aliases.size() == 0) return "";
        StringBuilder builder = new StringBuilder(" [");
        aliases.forEach(alias -> builder.append(alias).append("/"));
        builder.append("]");
        return builder.deleteCharAt(builder.length() - 2).toString();
    }
}
