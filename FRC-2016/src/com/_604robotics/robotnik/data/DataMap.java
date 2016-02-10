package com._604robotics.robotnik.data;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class DataMap.
 */
public class DataMap {
    
    /** The data table. */
    private final Map<String, Data> dataTable = new HashMap<String, Data>();
    
    /**
     * Adds the.
     *
     * @param name the name
     * @param data the data
     */
    protected void add (String name, Data data) {
        this.dataTable.put(name, data);
    }
    
    /**
     * Gets the data.
     *
     * @param name the name
     * @return the data
     */
    protected Data getData (String name) {
        return this.dataTable.get(name);
    }
    
    /**
     * Iterate.
     *
     * @return the iterator
     */
    protected Iterator<Map.Entry<String, Data>> iterate () {
        return this.dataTable.entrySet().iterator();
    }
}
