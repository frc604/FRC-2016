package com._604robotics.robotnik.data;

import java.util.Hashtable;

import com._604robotics.robotnik.Safety;
import com._604robotics.robotnik.logging.InternalLogger;
import com._604robotics.robotnik.memory.IndexedTable;
import com._604robotics.robotnik.meta.Iterator;
import com._604robotics.robotnik.meta.Repackager;

public class DataManager {
    private final String moduleName;
    private final Hashtable dataTable;
    
    public DataManager (String moduleName, DataMap dataMap, final IndexedTable table, Safety safety) {
        this.moduleName = moduleName;
        
        this.dataTable = Repackager.repackage(dataMap.iterate(), new Repackager() {
           public Object wrap (Object key, Object value) {
               return new DataReference((Data) value, table.getRow((String) key), safety);
           }
        });
    }

    public DataReference getData (String name) {
        final DataReference ref = (DataReference) this.dataTable.get(name);
        if (ref == null) InternalLogger.missing("DataReference", name);
        return ref;
    }
    
    public void update () {
        final Iterator i = new Iterator(this.dataTable);
        while (i.next()) {
            ((DataReference) i.value).update();
        }
    }
}
