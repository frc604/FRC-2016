package com._604robotics.robotnik.trigger;

import java.util.Hashtable;

import com._604robotics.robotnik.Safety;
import com._604robotics.robotnik.logging.InternalLogger;
import com._604robotics.robotnik.memory.IndexedTable;
import com._604robotics.robotnik.meta.Iterator;
import com._604robotics.robotnik.meta.Repackager;

public class TriggerManager {
    private final String moduleName;
    private final Hashtable triggerTable;
    
    public TriggerManager (String moduleName, TriggerMap triggerMap, final IndexedTable table, Safety safety) {
        this.moduleName = moduleName;
        
        this.triggerTable = Repackager.repackage(triggerMap.iterate(), new Repackager() {
           public Object wrap (Object key, Object value) {
               return new TriggerReference((Trigger) value, table.getRow((String) key), safety);
           }
        });
    }
    
    public TriggerReference getTrigger (String name) {
        final TriggerReference ref = (TriggerReference) this.triggerTable.get(name);
        if (ref == null) {
            InternalLogger.missing("TriggerReference", name);
        }
        return ref;
    }
    
    public void update () {
        final Iterator i = new Iterator(this.triggerTable);
        while (i.next()) {
            ((TriggerReference) i.value).update();
        }
    }
}
