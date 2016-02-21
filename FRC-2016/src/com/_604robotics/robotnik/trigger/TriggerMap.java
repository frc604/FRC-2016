package com._604robotics.robotnik.trigger;

import java.util.Hashtable;

import com._604robotics.robotnik.meta.Iterator;

public class TriggerMap {
    private final Hashtable triggerTable = new Hashtable();
    
    protected void add (String name, Trigger trigger) {
        this.triggerTable.put(name, trigger);
    }
    
    protected Trigger getTrigger (String name) {
        return (Trigger) this.triggerTable.get(name);
    }
    
    protected Iterator iterate () {
        return new Iterator(this.triggerTable);
    }
}
