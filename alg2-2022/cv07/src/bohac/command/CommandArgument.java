package bohac.command;

public class CommandArgument {
    public enum Type {
        INTEGER("int"), FLOAT("float"), STRING("string");

        private final String displayName;

        Type(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public String toString() {
            return displayName;
        }
    }

    private final String name;
    private final Type type;
    private final boolean optional;

    public CommandArgument(String name, Type type, boolean optional) {
        this.name = name;
        this.type = type;
        this.optional = optional;
    }

    public CommandArgument(String name, Type type) {
        this(name, type, false);
    }

    public CommandArgument(String name) {
        this(name, null, false);
    }

    public CommandArgument(String name, boolean optional) {
        this(name, null, optional);
    }

    public String getName() {
        return name;
    }


    public boolean isOptional() {
        return optional;
    }

    public Type getType() {
        return type;
    }
}
