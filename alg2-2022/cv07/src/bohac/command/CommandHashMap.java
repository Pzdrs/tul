package bohac.command;

import java.util.HashMap;

public class CommandHashMap extends HashMap<String, Command> {
    @Override
    public Command get(Object key) {
        return super.get(key.toString().toLowerCase());
    }

    public Command getByAlias(Object key) {
        for (Entry<String, Command> entry : entrySet()) {
            for (String alias : entry.getValue().getAliases()) {
                if (String.valueOf(key).toLowerCase().equals(alias)) return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public Command put(String key, Command value) {
        return super.put(key.toLowerCase(), value);
    }
}
