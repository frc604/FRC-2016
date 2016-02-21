package com._604robotics.robotnik.coordinator.steps;

import com._604robotics.robotnik.coordinator.Coordinator;
import com._604robotics.robotnik.module.ModuleManager;

public class Step {
    private final Measure measure;
    private final Coordinator coordinator;

    public Step (Measure measure) {
        this.measure = measure;
        this.coordinator = new Coordinator();
    }

    public Step (Coordinator coordinator) {
        this.measure = null;
        this.coordinator = coordinator;
    }

    public Step (Measure measure, Coordinator coordinator) {
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
    
    public void attach (ModuleManager modules) {
        coordinator.attach(modules);
    }
    
    public void update () {
        coordinator.update();
    }
    
    public void reset () {
        coordinator.stop();
    }
}
