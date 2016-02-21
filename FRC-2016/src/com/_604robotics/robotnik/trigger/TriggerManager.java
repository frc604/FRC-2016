package com._604robotics.robotnik.trigger;

import java.util.HashMap;
import java.util.Map;

import com._604robotics.robotnik.Safety;
import com._604robotics.robotnik.logging.InternalLogger;
import com._604robotics.robotnik.memory.IndexedTable;

public class TriggerManager {
    private final String moduleName;
    private final Map<String, TriggerReference> triggerTable;
    
    public TriggerManager (String moduleName, TriggerMap triggerMap, final IndexedTable table) {
        this.moduleName = moduleName;
        
        this.triggerTable = new HashMap<String, TriggerReference>();
        for (Map.Entry<String, Trigger> entry : triggerMap) {
            this.triggerTable.put(entry.getKey(), new TriggerReference(entry.getValue(), table.getSlice(entry.getKey())));
        }
    }
    
    public TriggerReference getTrigger (String name) {
        final TriggerReference ref = this.triggerTable.get(name);
        if (ref == null) InternalLogger.missing("TriggerReference", name);
        return ref;
    }
    
    public void update (Safety safety) {
        for (TriggerReference ref : this.triggerTable.values()) {
            ref.update(safety);
        }
    }
}
