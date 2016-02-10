package com._604robotics.robotnik.data;

import com._604robotics.robotnik.DataProxy;
import com._604robotics.robotnik.meta.Repackager;
import com._604robotics.robotnik.memory.IndexedTable;
import com._604robotics.robotnik.logging.InternalLogger;
import java.util.Iterator;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class DataManager.
 */
public class DataManager {
    
    /** The module name. */
    private final String moduleName;
    
    /** The data table. */
    private final Map<String, DataReference> dataTable;
    
    /**
     * Instantiates a new data manager.
     *
     * @param moduleName the module name
     * @param dataMap the data map
     * @param table the table
     */
    public DataManager (String moduleName, DataMap dataMap, final IndexedTable table) {
        this.moduleName = moduleName;
        
        this.dataTable = Repackager.repackage(dataMap.iterate(), new Repackager<DataReference, String, Data>() {
           public DataReference wrap (String key, Data value) {
               return new DataReference(value, table.getSlice(key));
           }
        });
    }
    
    /**
     * Gets the data.
     *
     * @param name the name
     * @return the data
     */
    public DataReference getData (String name) {
        final DataReference ref = this.dataTable.get(name);
        if (ref == null) InternalLogger.missing("DataReference", name);
        return ref;
    }
    
    /**
     * Update.
     */
    public void update () {
        final Iterator<Map.Entry<String, DataReference>> i = this.dataTable.entrySet().iterator();
        while (i.hasNext()) {
        	Map.Entry<String, DataReference> entry = i.next();
        	DataProxy.update(moduleName, entry.getKey(), entry.getValue());
        }
    }
}
