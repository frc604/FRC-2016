package com._604robotics.robotnik.coordinator.groups;

import com._604robotics.robotnik.Robot;
import com._604robotics.robotnik.coordinator.Coordinator;
import com._604robotics.robotnik.module.ModuleManager;
import com._604robotics.robotnik.trigger.TriggerAccess;

public class Group<T extends Robot<T>> {
    private final TriggerAccess trigger;
    private final Coordinator<T> coordinator;

    public Group (TriggerAccess trigger, Coordinator<T> coordinator) {
        this.trigger = trigger;
        this.coordinator = coordinator;
    }
    
    public void attach (T robot) {
        coordinator.attach(robot);
    }
    
    public void update () {
        if (trigger.get())
            coordinator.update();
    }

    public void stop () {
    	coordinator.stop();
    }
}
