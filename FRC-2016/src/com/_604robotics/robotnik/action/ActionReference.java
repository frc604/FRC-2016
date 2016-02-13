package com._604robotics.robotnik.action;

import com._604robotics.robotnik.Safety;
import com._604robotics.robotnik.data.DataRecipient;
import com._604robotics.robotnik.memory.IndexedTable;
import com._604robotics.robotnik.memory.IndexedTable.Row;
import com._604robotics.robotnik.module.ModuleReference;
import com._604robotics.robotnik.prefabs.trigger.TriggerManual;
import com._604robotics.robotnik.trigger.TriggerAccess;
import com._604robotics.robotnik.trigger.TriggerRecipient;

public class ActionReference implements DataRecipient, TriggerRecipient {
    private final Action action;

    private final Row trigger;
    private final IndexedTable dataTable;

    private final ActionData actionData;

    private final TriggerManual activeTrigger = new TriggerManual(false);
    
    private final Safety safety;
    
    public ActionReference (ModuleReference module, Action action, Row triggered, IndexedTable dataTable, Safety safety) {
        this.action = action;
        
        this.trigger = triggered;
        this.dataTable = dataTable;        
        this.actionData = new ActionData(this.action.getFieldMap(), this.dataTable, module);
        
        this.safety = safety;
    }
    
    public void reset () {
        this.trigger.putNumber(0D);
        this.actionData.reset();
    }
    
    /* (non-Javadoc)
     * @see com._604robotics.robotnik.trigger.TriggerRecipient#sendTrigger(double)
     */
    public void sendTrigger (double precedence) {
        final double current = this.trigger.getNumber(0D);
        
        if (precedence > current) {
            this.trigger.putNumber(precedence);
        }
    }
    
    /* (non-Javadoc)
     * @see com._604robotics.robotnik.data.DataRecipient#sendData(java.lang.String, double)
     */
    public void sendData (String fieldName, double dataValue) {
        this.dataTable.putNumber(fieldName, dataValue);
    }
    
    public void begin () {
        safety.wrap("action begin phase", () -> action.begin(actionData));
        this.activeTrigger.set(true);
    }
    
    public void run () {
        safety.wrap("action run phase", () -> action.run(actionData));
    }
    
    public void end () {
        safety.wrap("action end phase", () -> action.end(actionData));
        this.activeTrigger.set(false);
    }
    
    public TriggerAccess active () {
        return (TriggerAccess) this.activeTrigger;
    }
}