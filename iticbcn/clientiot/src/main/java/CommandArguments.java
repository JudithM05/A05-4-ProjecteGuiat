import java.util.HashMap;
import java.util.Map;

public class CommandArguments {
    private final Map<String, String> argsMap = new HashMap<>();

    public CommandArguments(String[] args) {
        for (String arg : args) {
            String[] parts = arg.split("=", 2);
            if (parts.length == 2) {
                argsMap.put(parts[0], parts[1]);
            }
        }
    }

    public String get(String key, String defaultValue) {
        return argsMap.getOrDefault(key, defaultValue);
    }

    public String get(String key) {
        return argsMap.get(key);
    }
}
