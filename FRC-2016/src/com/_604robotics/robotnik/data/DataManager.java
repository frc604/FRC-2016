package com._604robotics.robotnik.data;

import java.util.HashMap;
import java.util.Map;

import com._604robotics.robotnik.Safety;
import com._604robotics.robotnik.logging.InternalLogger;
import com._604robotics.robotnik.meta.Iterator;
import com._604robotics.robotnik.networking.Row;

import edu.wpi.first.wpilibj.tables.ITable;

public class DataManager {
    private final Map<String, DataReference> data = new HashMap<>();
    
    public DataManager (DataMap dataMap, final ITable table, Safety safety) {
        final Iterator i = dataMap.iterate();
        while (i.next()) {
           data.put((String) i.key, new DataReference((Data) i.value, new Row(table, (String) i.key), safety));
        }
        
        table.putStringArray("__data", data.keySet().toArray(new String[data.keySet().size()]));
    }

    public DataReference getData (String name) {
        if (!data.containsKey(name)) {
            InternalLogger.missing("DataReference", name);
            return null;
        } else {
            return data.get(name);
        }
    }
    
    public void update () {
        for (DataReference ref : data.values()) {
            ref.update();
        }
    }
}
