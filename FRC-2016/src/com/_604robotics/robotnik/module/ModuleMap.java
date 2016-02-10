package com._604robotics.robotnik.module;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class ModuleMap.
 */
public class ModuleMap {
    
    /** The module table. */
    private final Map<String, Module> moduleTable = new HashMap<String, Module>();
    
    /**
     * Adds the.
     *
     * @param name the name
     * @param module the module
     */
    protected void add (String name, Module module) {
        this.moduleTable.put(name, module);
    }
    
    /**
     * Gets the module.
     *
     * @param name the name
     * @return the module
     */
    protected Module getModule (String name) {
        return this.moduleTable.get(name);
    }
    
    /**
     * Iterate.
     *
     * @return the iterator
     */
    protected Iterator<Map.Entry<String, Module>> iterate () {
        return this.moduleTable.entrySet().iterator();
    }
}
