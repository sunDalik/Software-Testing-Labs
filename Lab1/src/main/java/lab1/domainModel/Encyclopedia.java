package lab1.domainModel;

import java.util.HashMap;
import java.util.Map;

public class Encyclopedia {
    public final Map<String, String> entries = new HashMap<>();

    public void addEntry(String key, String definition) {
        entries.put(key.toLowerCase(), definition);
    }

    public String getDefinition(String key) {
        return entries.get(key.toLowerCase());
    }
}
