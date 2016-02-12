package com._604robotics.robotnik.trigger;

import com._604robotics.robotnik.memory.IndexedTable.Slice;
import com._604robotics.robotnik.prefabs.trigger.TriggerNot;

// TODO: Auto-generated Javadoc
/**
 * The Class TriggerReference.
 */
public class TriggerReference implements TriggerAccess {
    
    /** The trigger. */
    private final Trigger trigger;
    
    /** The value. */
    private final Slice value;
    
    /** The inverse. */
    private TriggerAccess inverse = null;
    
    /**
     * Instantiates a new trigger reference.
     *
     * @param trigger the trigger
     * @param value the value
     */
    public TriggerReference (Trigger trigger, Slice value) {
        this.trigger = trigger;
        this.value = value;
    }
    
    /**
     * Not.
     *
     * @return the trigger access
     */
    public TriggerAccess not () {
        if (this.inverse == null) {
            this.inverse = new TriggerNot(this);
        }
        
        return this.inverse;
    }
    
    /* (non-Javadoc)
     * @see com._604robotics.robotnik.trigger.TriggerAccess#get()
     */
    public boolean get () {
        return this.value.getBoolean(false);
    }
    
    /**
     * Update.
     */
    public void update () {
        this.value.putBoolean(this.trigger.run());
    }
}
