package com._604robotics.robotnik.prefabs.trigger;

import com._604robotics.robotnik.trigger.TriggerAccess;

public class TriggerManual implements TriggerAccess {
    private boolean triggered;

    public TriggerManual (boolean defaultValue) {
        this.triggered = defaultValue;
    }
    
    /* (non-Javadoc)
     * @see com._604robotics.robotnik.trigger.TriggerAccess#get()
     */
    public boolean get () {
        return this.triggered;
    }

    public void set (boolean triggered) {
        this.triggered = triggered;
    }
}