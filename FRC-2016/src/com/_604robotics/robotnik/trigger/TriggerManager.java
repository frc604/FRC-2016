package com._604robotics.robotnik.trigger;

import java.util.HashMap;
import java.util.Map;

import com._604robotics.robotnik.Safety;
import com._604robotics.robotnik.logging.Logger;
import com._604robotics.robotnik.memory.IndexedTable;

/**
 * Manages triggers.
 */
public class TriggerManager {
    private final String moduleName;
    private final Map<String, TriggerReference> triggerTable;
    
    /**
     * Creates a trigger manager.
     * @param moduleName Name of the module that this belongs to.
     * @param triggerMap Map of triggers to manage.
     * @param table Table to store trigger data in.
     * @param safety Safety Safety mode to operate under.
     */
    public TriggerManager (String moduleName, TriggerMap triggerMap, final IndexedTable table, Safety safety) {
        this.moduleName = moduleName;
        
        triggerTable = new HashMap<String, TriggerReference>();
        for (Map.Entry<String, Trigger> entry : triggerMap) {
            this.triggerTable.put(entry.getKey(), new TriggerReference(entry.getValue(), table.getRow(entry.getKey()), safety));
        }
    }
    
    /**
     * Gets a reference to a trigger.
     * @param name Name of the trigger.
     * @return The retrieved trigger reference.
     */
    public TriggerReference getTrigger (String name) {
        final TriggerReference ref = this.triggerTable.get(name);
        if (ref == null) Logger.missing("TriggerReference", name);
        return ref;
    }
    
    /**
     * Updates all triggers.
     */
    public void update () {
        for (TriggerReference ref : this.triggerTable.values()) {
            ref.update();
        }
    }
}
