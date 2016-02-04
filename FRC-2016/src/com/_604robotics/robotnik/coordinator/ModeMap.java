package com._604robotics.robotnik.coordinator;

import com._604robotics.robotnik.module.ModuleManager;

// TODO: Auto-generated Javadoc
/**
 * The Class ModeMap.
 */
public class ModeMap {
    
    /** The autonomous mode. */
    private Coordinator autonomousMode = new Coordinator();
    
    /** The teleop mode. */
    private Coordinator teleopMode = new Coordinator();
    
    /**
     * Attach.
     *
     * @param modules the modules
     */
    public void attach (ModuleManager modules) {
        this.autonomousMode.attach(modules);
        this.teleopMode.attach(modules);
    }
    
    /**
     * Sets the autonomous mode.
     *
     * @param autonomousMode the new autonomous mode
     */
    protected void setAutonomousMode (Coordinator autonomousMode) {
        this.autonomousMode = autonomousMode;
    }
    
    /**
     * Sets the teleop mode.
     *
     * @param teleopMode the new teleop mode
     */
    protected void setTeleopMode (Coordinator teleopMode) {
        this.teleopMode = teleopMode;
    }

    /**
     * Gets the autonomous mode.
     *
     * @return the autonomous mode
     */
    public Coordinator getAutonomousMode () {
        return this.autonomousMode;
    }

    /**
     * Gets the teleop mode.
     *
     * @return the teleop mode
     */
    public Coordinator getTeleopMode () {
        return this.teleopMode;
    }
}
