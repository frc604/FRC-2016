package com._604robotics.robotnik.data;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com._604robotics.robotnik.Safety;
import com._604robotics.robotnik.logging.InternalLogger;
import com._604robotics.robotnik.memory.IndexedTable;

public class DataManager {
    private final String moduleName;

    private final Map<String, DataReference> dataTable;

    public DataManager (String moduleName, DataMap dataMap, final IndexedTable table) {
        this.moduleName = moduleName;
        
        this.dataTable = new HashMap<String, DataReference>();
        for(Map.Entry<String, Data> entry : dataMap) {
        	this.dataTable.put(entry.getKey(), new DataReference(entry.getValue(), table.getSlice(entry.getKey())));
        }
    }

    public DataReference getData (String name) {
        final DataReference ref = this.dataTable.get(name);
        if (ref == null) InternalLogger.missing("DataReference", name);
        return ref;
    }

    public void update (Safety safety) {
        for (DataReference reference : this.dataTable.values()) {
            reference.update(safety);
        }
    }
}
