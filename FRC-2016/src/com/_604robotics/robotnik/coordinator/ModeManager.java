package com._604robotics.robotnik.coordinator;

import com._604robotics.robotnik.GameMode;
import com._604robotics.robotnik.Robot;

public class ModeManager {
    private Coordinator autonomousMode = new Coordinator();
    private Coordinator teleopMode = new Coordinator();
    
    /**
     * Sets the autonomous mode.
     *
     * @param autonomousMode the new autonomous mode
     */
    public void setAutonomousMode (Coordinator autonomousMode) {
        this.autonomousMode = autonomousMode;
    }
    
    /**
     * Sets the teleop mode.
     *
     * @param teleopMode the new teleop mode
     */
    public void setTeleopMode (Coordinator teleopMode) {
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
    
    public Coordinator getGameMode (GameMode gameMode) {
        switch (gameMode) {
        case AUTONOMOUS:
            return getAutonomousMode();
        case TELEOP:
            return getTeleopMode();
        default:
            return null;
        }
    }
    
    public void update (GameMode gameMode) {
        final Coordinator coordinator = getGameMode(gameMode);
        if (coordinator != null) {
            coordinator.update();
        }
    }
    
    public void stop (GameMode gameMode) {
        final Coordinator coordinator = getGameMode(gameMode);
        if (coordinator != null) {
            coordinator.update();
        }
    }
}
