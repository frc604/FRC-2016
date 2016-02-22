package com._604robotics.robotnik.trigger;

import com._604robotics.robotnik.Safety;
import com._604robotics.robotnik.memory.IndexedTable.Row;
import com._604robotics.robotnik.prefabs.trigger.TriggerNot;

/**
 * A reference to a trigger.
 */
public class TriggerReference implements TriggerAccess {
    private final Trigger trigger;
    private final Row value;

    private TriggerAccess inverse = null;
    
    private final Safety safety;
    
    /**
     * Creates a trigger reference.
     * @param trigger Trigger to refer to.
     * @param value Slice to store the trigger value in.
     * @param safety Safety mode to operate under.
     */
    public TriggerReference (Trigger trigger, Row value, Safety safety) {
        this.trigger = trigger;
        this.value = value;
        
        this.safety = safety;
    }
    
    /**
     * Gets the inverse of the trigger.
     * @return The inverse of the trigger.
     */
    public TriggerAccess not () {
        if (inverse == null) {
            inverse = new TriggerNot(this);
        }
        
        return inverse;
    }
    
    @Override
    public boolean get () {
        return value.getBoolean(false);
    }
    
    /**
     * Updates the trigger.
     */
    public void update () {
        safety.wrap("updating trigger value", () -> value.putBoolean(trigger.run()));
    }
}
