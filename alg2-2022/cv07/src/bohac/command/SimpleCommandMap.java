package bohac.command;

import java.util.*;

public class SimpleCommandMap implements CommandMap {
    protected final Map<String, Command> knownCommands = new CommandHashMap();

    @Override
    public void clearCommands() {
        knownCommands.clear();
    }

    @Override
    public Map.Entry<Boolean, String> dispatch(String commandLine) {
        String normalizedCommandLine = commandLine.trim();

        if (normalizedCommandLine.isEmpty()) return new AbstractMap.SimpleEntry<>(false, null);

        String[] args = normalizedCommandLine.split(" ");

        String commandLabel = args[0];
        Command target = getCommand(commandLabel);

        // unknown command
        if (target == null) return new AbstractMap.SimpleEntry<>(false, Command.NOT_FOUND_MESSAGE.apply(commandLabel));

        // argument amount check
        if (args.length - 1 > target.getArguments().size()) {
            return new AbstractMap.SimpleEntry<>(false, target.getErrorMessage("too many arguments"));
        } else if (args.length - 1 < target.getArguments().stream().filter(commandArgument -> !commandArgument.isOptional()).count()) {
            return new AbstractMap.SimpleEntry<>(false, target.getErrorMessage("missing operand"));
        }

        return new AbstractMap.SimpleEntry<>(target.execute(commandLabel, Arrays.copyOfRange(args, 1, args.length)), null);
    }

    @Override
    public Command getCommand(String name) {
        Command target = knownCommands.get(name);
        if (target != null) return target;
        return ((CommandHashMap) knownCommands).getByAlias(name);
    }

    @Override
    public boolean register(String label, Command command) {
        knownCommands.put(label, command);
        return true;
    }

    @Override
    public boolean registerAll(List<Command> commands) {
        if (commands == null) return false;
        for (Command command : commands) {
            register(command.getName(), command);
        }
        return true;
    }

    public Map<String, Command> getKnownCommands() {
        return knownCommands;
    }
}
