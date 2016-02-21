package com._604robotics.robotnik.module;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ModuleMap implements Iterable<Map.Entry<String, Module>> {
    private final Map<String, Module> moduleTable = new HashMap<String, Module>();

    protected void add (String name, Module module) {
        this.moduleTable.put(name, module);
    }

    protected Module getModule (String name) {
        return this.moduleTable.get(name);
    }

    public Iterator<Map.Entry<String, Module>> iterator () {
        return this.moduleTable.entrySet().iterator();
    }
}
