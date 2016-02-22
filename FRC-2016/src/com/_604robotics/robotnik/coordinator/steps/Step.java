package com._604robotics.robotnik.coordinator.steps;

import com._604robotics.robotnik.coordinator.Coordinator;

/**
 * A step to execute.
 */
public class Step {
    private final Measure measure;
    private final Coordinator coordinator;

    /**
     * Creates a step.
     * @param measure Measure indicating completion.
     * @param coordinator Coordinator driving the step.
     */
    public Step (Measure measure, Coordinator coordinator) {
        this.measure = measure;
        this.coordinator = coordinator;
    }
    
    /**
     * Initializes the step.
     */
    public void initialize () {
        if (measure != null) {
            measure.initialize();
        }
    }
    
    /**
     * Gets whether the step is complete.
     * @return Whether the step is complete.
     */
    public boolean complete () {
        if (measure != null && !measure.complete()) {
            return false;
        }
        
        if (!coordinator.complete()) {
            return false;
        }

        return true;
    }
    
    /**
     * Updates the step.
     */
    public void update () {
        coordinator.update();
    }
    
    /**
     * Stops the step.
     */
    public void stop () {
        coordinator.stop();
    }
}
