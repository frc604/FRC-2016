package com._604robotics.robotnik.data;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * A map containing data.
 */
public class DataMap implements Iterable<Map.Entry<String, Data>> {
    private final Map<String, Data> dataTable = new HashMap<String, Data>();

    /**
     * Adds data to the map.
     * @param name Name of the data.
     * @param data Data to add.
     */
    protected void add (String name, Data data) {
        this.dataTable.put(name, data);
    }

    /**
     * Gets data from the map.
     * @param name Name of the data.
     * @return The retrieved data.
     */
    protected Data getData (String name) {
        return this.dataTable.get(name);
    }

    @Override
    public Iterator<Map.Entry<String, Data>> iterator () {
        return this.dataTable.entrySet().iterator();
    }
}
