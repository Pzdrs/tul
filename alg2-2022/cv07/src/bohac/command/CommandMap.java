package bohac.command;

import java.util.List;
import java.util.Map;

public interface CommandMap {
    void clearCommands();

    Map.Entry<Boolean, String> dispatch(String commandLine);

    Command getCommand(String name);

    boolean register(String label, Command command);

    boolean registerAll(List<Command> commands);
}
