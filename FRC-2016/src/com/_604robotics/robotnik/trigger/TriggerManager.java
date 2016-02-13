package com._604robotics.robotnik.trigger;

import java.util.HashMap;
import java.util.Map;

import com._604robotics.robotnik.Safety;
import com._604robotics.robotnik.logging.InternalLogger;
import com._604robotics.robotnik.meta.Iterator;
import com._604robotics.robotnik.networking.Row;

import edu.wpi.first.wpilibj.tables.ITable;

public class TriggerManager {
    private final Map<String, TriggerReference> triggers = new HashMap<>();
    
    public TriggerManager (TriggerMap triggerMap, final ITable table, Safety safety) {
        final Iterator i = triggerMap.iterate();
        while (i.next()) {
           triggers.put((String) i.key, new TriggerReference((Trigger) i.value, new Row(table, (String) i.key), safety));
        }
        
        table.putStringArray("__triggers", triggers.keySet().toArray(new String[triggers.keySet().size()]));
    }
    
    public TriggerReference getTrigger (String name) {
        if (!triggers.containsKey(name)) {
            InternalLogger.missing("TriggerReference", name);
            return null;
        } else {
            return triggers.get(name);
        }
    }
    
    public void update () {
        for (TriggerReference ref : triggers.values()) {
            ref.update();
        }
    }
}
