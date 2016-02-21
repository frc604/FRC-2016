package com._604robotics.robotnik.trigger;

import com._604robotics.robotnik.Safety;
import com._604robotics.robotnik.memory.IndexedTable.Slice;
import com._604robotics.robotnik.prefabs.trigger.TriggerNot;

public class TriggerReference implements TriggerAccess {
    private final Trigger trigger;
    private final Slice value;

    private TriggerAccess inverse = null;

    public TriggerReference (Trigger trigger, Slice value) {
        this.trigger = trigger;
        this.value = value;
    }
    
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
    
    public void update (Safety safety) {
        safety.wrap("updating trigger value", () -> value.putBoolean(trigger.run()));
    }
}
