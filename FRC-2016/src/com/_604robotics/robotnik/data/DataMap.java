package com._604robotics.robotnik.data;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DataMap implements Iterable<Map.Entry<String, Data>> {
    private final Map<String, Data> dataTable = new HashMap<String, Data>();

    protected void add (String name, Data data) {
        this.dataTable.put(name, data);
    }

    protected Data getData (String name) {
        return this.dataTable.get(name);
    }

    @Override
    public Iterator<Map.Entry<String, Data>> iterator () {
        return this.dataTable.entrySet().iterator();
    }
}
