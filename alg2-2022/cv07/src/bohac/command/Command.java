package bohac.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public abstract class Command implements Comparable<Command>{
    public static final UnaryOperator<String> NOT_FOUND_MESSAGE = s -> s.split(" ")[0] + ": command not found";
    private final String name;

    public Command(String name) {
        this.name = name;
    }

    public final String getName() {
        return name;
    }

    public final String getUsage() {
        StringBuilder builder = new StringBuilder();
        builder.append(getName());
        if (getArguments().size() > 0) {
            for (CommandArgument argument : getArguments()) {
                builder.append(" ");
                if (argument.isOptional()) {
                    builder.append("[").append(argument.getName()).append(argument.getType() != null ? ": " + argument.getType() : "").append("]");
                } else {
                    builder.append("<").append(argument.getName()).append(argument.getType() != null ? ": " + argument.getType() : "").append(">");
                }
            }
        }
        return builder.toString();
    }

    public final String getErrorMessage(String message) {
        return getErrorMessage(message, null);
    }

    public final String getErrorMessage(String message, String argument) {
        return new StringBuilder(getName())
                .append(": ")
                .append(argument != null ? argument + ": " : "")
                .append(message)
                .toString();
    }

    public List<String> getAliases() {
        return new ArrayList<>(0);
    }

    public List<CommandArgument> getArguments() {
        return new ArrayList<>(0);
    }

    public abstract String getDescription();

    public abstract boolean execute(String label, String[] args);

    @Override
    public int compareTo(Command o) {
        return getName().compareToIgnoreCase(o.getName());
    }
}
