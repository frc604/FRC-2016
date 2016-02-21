package com._604robotics.robotnik.memory;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.ITable;

import java.util.HashMap;
import java.util.Map;

public class TableCache {
    private static final Map<ITable, IndexedTable> cache = new HashMap<ITable, IndexedTable>();

    protected static IndexedTable getTable (String key) {
        return getTable(NetworkTable.getTable(key));
    }

    protected static IndexedTable getSubTable(ITable parent, String key) {
        return getTable(parent.getSubTable(key));
    }

    private static IndexedTable getTable (ITable table) {
        if (cache.containsKey(table)) {
            return cache.get(table);
        } else {
            final IndexedTable result = new IndexedTable(table);
            cache.put(table, result);
            
            return result;
        }
    }
}
