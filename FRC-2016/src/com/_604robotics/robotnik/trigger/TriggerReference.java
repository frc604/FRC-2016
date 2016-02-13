package com._604robotics.robotnik.trigger;

import com._604robotics.robotnik.Safety;
import com._604robotics.robotnik.memory.IndexedTable.Row;

public class TriggerReference implements TriggerAccess {
    private final Trigger trigger;
    private final Row value;

    private TriggerAccess inverse = null;
    
    private final Safety safety;
    
    private class TriggerNot implements TriggerAccess {
        private final TriggerAccess source;
        
        public TriggerNot (TriggerAccess source) {
            this.source = source;
        }

        public boolean get () {
            return !source.get();
        }
    }
    
    public TriggerReference (Trigger trigger, Row value, Safety safety) {
        this.trigger = trigger;
        this.value = value;
        
        this.safety = safety;
    }
    
    public TriggerAccess not () {
        if (inverse == null) {
            inverse = new TriggerNot(this);
        }
        
        return inverse;
    }
    
    /* (non-Javadoc)
     * @see com._604robotics.robotnik.trigger.TriggerAccess#get()
     */
    public boolean get () {
        return value.getBoolean(false);
    }
    
    public void update () {
        safety.wrap("updating trigger value", () -> value.putBoolean(trigger.run()));
    }
}
