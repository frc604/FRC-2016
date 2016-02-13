package com._604robotics.robotnik.coordinator.steps;

import com._604robotics.robotnik.Robot;
import com._604robotics.robotnik.coordinator.Coordinator;
import com._604robotics.robotnik.module.ModuleManager;

public class Step<T extends Robot<T>> {
    private final Measure measure;
    private final Coordinator<T> coordinator;

    public Step (Measure measure, Coordinator<T> coordinator) {
        this.measure = measure;
        this.coordinator = coordinator;
    }
    
    public void initialize () {
        if (measure != null) {
            measure.initialize();
        }
    }
    
    public boolean complete () {
        if (measure != null && !measure.complete()) {
            return false;
        }
        
        if (!coordinator.complete()) {
            return false;
        }

        return true;
    }
    
    public void attach (T robot) {
        coordinator.attach(robot);
    }
    
    public void update () {
        coordinator.update();
    }
    
    public void stop () {
        coordinator.stop();
    }
}
