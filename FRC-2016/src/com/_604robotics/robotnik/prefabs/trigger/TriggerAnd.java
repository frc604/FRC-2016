package com._604robotics.robotnik.prefabs.trigger;

import com._604robotics.robotnik.trigger.TriggerAccess;

public class TriggerAnd implements TriggerAccess {
    private final TriggerAccess[] triggers;

    public TriggerAnd (TriggerAccess... triggers) {
        this.triggers = triggers;
    }
    
    /* (non-Javadoc)
     * @see com._604robotics.robotnik.trigger.TriggerAccess#get()
     */
    public boolean get () {
        boolean value = true;
        
        for (int i = 0; i < this.triggers.length; i++) {
            value = this.triggers[i].get() && value;
        }
        
        return value;
    }
}