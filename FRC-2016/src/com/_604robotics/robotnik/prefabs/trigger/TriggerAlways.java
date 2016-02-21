package com._604robotics.robotnik.prefabs.trigger;

import com._604robotics.robotnik.trigger.TriggerAccess;

public class TriggerAlways implements TriggerAccess {
    private TriggerAlways () {}

    private static final TriggerAccess instance = new TriggerAlways();

    public static TriggerAccess getInstance () {
        return instance;
    }
    
    /* (non-Javadoc)
     * @see com._604robotics.robotnik.trigger.TriggerAccess#get()
     */
    public boolean get () {
        return true;
    }
}