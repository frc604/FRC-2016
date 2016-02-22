package com._604robotics.robotnik.coordinator.groups;

import com._604robotics.robotnik.Robot;
import com._604robotics.robotnik.coordinator.Coordinator;
import com._604robotics.robotnik.module.ModuleManager;
import com._604robotics.robotnik.trigger.TriggerAccess;

/**
 * A group of steps to execute.
 */
public class Group {
    private final TriggerAccess trigger;
    private final Coordinator coordinator;

    /**
     * Creates a group.
     * @param trigger Trigger activating the group.
     * @param coordinator Coordinator driving the group.
     */
    public Group (TriggerAccess trigger, Coordinator coordinator) {
        this.trigger = trigger;
        this.coordinator = coordinator;
    }
    
    /**
     * Updates the group.
     */
    public void update () {
        if (active()) {
            coordinator.update();
        }
    }

    /**
     * Stops the group.
     */
    public void stop () {
    	coordinator.stop();
    }
    
    /**
     * Gets whether the group is active.
     * @return Whether the group is active.
     */
    public boolean active () {
        return trigger.get();
    }
    
    /**
     * Gets whether the group is complete.
     * @return Whether the group is complete.
     */
    public boolean complete () {
        return !active() || coordinator.complete();
    }
}
