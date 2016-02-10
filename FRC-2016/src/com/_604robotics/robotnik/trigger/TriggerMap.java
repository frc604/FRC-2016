package com._604robotics.robotnik.trigger;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class TriggerMap.
 */
public class TriggerMap {
    
    /** The trigger table. */
    private final Map<String, Trigger> triggerTable = new HashMap<String, Trigger>();
    
    /**
     * Adds the.
     *
     * @param name the name
     * @param trigger the trigger
     */
    protected void add (String name, Trigger trigger) {
        this.triggerTable.put(name, trigger);
    }
    
    /**
     * Gets the trigger.
     *
     * @param name the name
     * @return the trigger
     */
    protected Trigger getTrigger (String name) {
        return this.triggerTable.get(name);
    }
    
    /**
     * Iterate.
     *
     * @return the iterator
     */
    protected Iterator<Map.Entry<String, Trigger>> iterate () {
        return this.triggerTable.entrySet().iterator();
    }
}
