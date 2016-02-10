package com._604robotics.robotnik.trigger;

import com._604robotics.robotnik.TriggerProxy;
import com._604robotics.robotnik.meta.Repackager;
import com._604robotics.robotnik.memory.IndexedTable;
import com._604robotics.robotnik.logging.InternalLogger;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class TriggerManager.
 */
public class TriggerManager {
    
    /** The module name. */
    private final String moduleName;
    
    /** The trigger table. */
    private final Map<String, TriggerReference> triggerTable;
    
    /**
     * Instantiates a new trigger manager.
     *
     * @param moduleName the module name
     * @param triggerMap the trigger map
     * @param table the table
     */
    public TriggerManager (String moduleName, TriggerMap triggerMap, final IndexedTable table) {
        this.moduleName = moduleName;
        
        this.triggerTable = Repackager.repackage(triggerMap.iterate(), new Repackager<TriggerReference, String, Trigger>() {
           public TriggerReference wrap (String key, Trigger value) {
               return new TriggerReference(value, table.getSlice(key));
           }
        });
    }
    
    /**
     * Gets the trigger.
     *
     * @param name the name
     * @return the trigger
     */
    public TriggerReference getTrigger (String name) {
        TriggerReference ref = (TriggerReference) this.triggerTable.get(name);
        if (ref == null) InternalLogger.missing("TriggerReference", name);
        return ref;
    }
    
    /**
     * Update.
     */
    public void update () {
    	for(Map.Entry<String, TriggerReference> entry : this.triggerTable.entrySet()) {
    		TriggerProxy.update(moduleName, entry.getKey(), entry.getValue());
    	}
    }
}
