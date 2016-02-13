package com._604robotics.robotnik.data.sources;

import com._604robotics.robotnik.data.DataAccess;
import com._604robotics.robotnik.trigger.TriggerAccess;

// TODO: Auto-generated Javadoc
/**
 * The Class DataTriggerAdaptor.
 */
public class DataTriggerAdapter implements DataAccess {
    
    /** The trigger. */
    private final TriggerAccess trigger;
    
    /**
     * Instantiates a new data trigger adaptor.
     *
     * @param trigger the trigger
     */
    public DataTriggerAdapter (TriggerAccess trigger) {
        this.trigger = trigger;
    }
    
    /* (non-Javadoc)
     * @see com._604robotics.robotnik.data.DataAccess#get()
     */
    public double get () {
        return this.trigger.get() ? 1D : 0D;
    }
}
