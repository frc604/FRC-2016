package com._604robotics.robotnik.coordinator.groups;

import com._604robotics.robotnik.coordinator.Coordinator;
import com._604robotics.robotnik.module.ModuleManager;
import com._604robotics.robotnik.trigger.TriggerAccess;

public class Group {
    private final TriggerAccess trigger;
    private final Coordinator coordinator;

    public Group (TriggerAccess trigger, Coordinator coordinator) {
        this.trigger = trigger;
        this.coordinator = coordinator;
    }
    
    public void attach (ModuleManager modules) {
        coordinator.attach(modules);
    }
    
    public void update () {
        if (active()) {
            coordinator.update();
        }
    }

    public void reset () {
    	coordinator.stop();
    }
    
    public boolean active () {
        return trigger.get();
    }
    
    public boolean complete () {
        return !active() || coordinator.complete();
    }
}
