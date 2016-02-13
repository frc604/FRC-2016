package com._604robotics.robotnik.memory;

import java.util.HashSet;
import java.util.Set;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;

// TODO: Replace IndexedTable with a regular NetworkTable. Factor out Row.
//       Manually index Modules, Actions, Data, Triggers (requires
//       robotnik-inspector updates?).
@Deprecated
public class IndexedTable {
    public static IndexedTable getTable (String key) {
        return TableCache.getTable(key);
    }
    
    private final Set keys = new HashSet();
    private final ITable table;
    
    public boolean knowsAbout (String key) {
        return this.keys.contains(key);
    }
    
    public Row getRow (String key) {
        return new Row(this, key);
    }
    
    public IndexedTable getSubTable(String key) {
        this.addKey(key);
        return TableCache.getSubTable(this.table, key);
    }
    
    public String  getString  (String key, String  defaultValue) { return this.table.getString (key, defaultValue); }
    public double  getNumber  (String key, double  defaultValue) { return this.table.getNumber (key, defaultValue); }
    public boolean getBoolean (String key, boolean defaultValue) { return this.table.getBoolean(key, defaultValue); }
    public byte[]  getRaw     (String key, byte[]  defaultValue) { return this.table.getRaw    (key, defaultValue); }

    public void putString  (String key, String  value) { this.table.putString (key, value); this.addKey(key); }
    public void putNumber  (String key, double  value) { this.table.putNumber (key, value); this.addKey(key); }
    public void putBoolean (String key, boolean value) { this.table.putBoolean(key, value); this.addKey(key); }
    public void putRaw     (String key, byte[]  value) { this.table.putRaw    (key, value); this.addKey(key); }

    private void addKey (String key) {
        if (!this.keys.contains(key)) {
            this.keys.add(key);
            
            final String keyList = this.table.getString("__index", "");
            if (keyList.equals("")) {
                this.table.putString("__index", key);
            } else {
                this.table.putString("__index", keyList + ";" + key);
            }
        }
    }
    
    protected IndexedTable (ITable table) {
        this.table = table;
    }

    public class Row {
        private final IndexedTable source;
        private final String key;
        
        private Row (IndexedTable source, String key) {
            this.source = source;
            this.key = key;
        }
        
        public String getKey () {
            return key;
        }

        public String  getString  (String  defaultValue) { return this.source.getString (this.key, defaultValue); }
        public double  getNumber  (double  defaultValue) { return this.source.getNumber (this.key, defaultValue); }
        public boolean getBoolean (boolean defaultValue) { return this.source.getBoolean(this.key, defaultValue); }
        public byte[]  getRaw     (byte[]  defaultValue) { return this.source.getRaw    (this.key, defaultValue); }

        public void putString  (String  value) { this.source.putString (this.key, value); }
        public void putNumber  (double  value) { this.source.putNumber (this.key, value); }
        public void putBoolean (boolean value) { this.source.putBoolean(this.key, value); }
        public void putRaw     (byte[]  value) { this.source.putRaw    (this.key, value); }
    }
}
