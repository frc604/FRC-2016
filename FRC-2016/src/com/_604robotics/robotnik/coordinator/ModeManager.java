package com._604robotics.robotnik.coordinator;

import com._604robotics.robotnik.GameMode;

/**
 * Manager for robot control modes.
 */
public class ModeManager {
    private Coordinator autonomousMode = new Coordinator();
    private Coordinator teleopMode = new Coordinator();
    
    /**
     * Sets the manager's autonomous mode.
     * @param autonomousMode Autonomous mode coordinator to set.
     */
    public void setAutonomousMode (Coordinator autonomousMode) {
        this.autonomousMode = autonomousMode;
    }

    /**
     * Sets the manager's teleop mode.
     * @param autonomousMode Teleop mode coordinator to set.
     */
    public void setTeleopMode (Coordinator teleopMode) {
        this.teleopMode = teleopMode;
    }

    /**
     * Gets the manager's autonomous mode.
     * @return The map's autonomous mode coordinator.
     */
    public Coordinator getAutonomousMode () {
        return this.autonomousMode;
    }

    /**
     * Gets the manager's teleop mode.
     * @return The map's teleop mode coordinator.
     */
    public Coordinator getTeleopMode () {
        return this.teleopMode;
    }
    
    /**
     * Gets the coordinator for a game mode.
     * @param gameMode Game mode to get the coordinator for.
     * @return The game mode's coordinator.
     */
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
    
    /**
     * Updates the coordinator for a game mode.
     * @param gameMode Game mode to update the coordinator for.
     */
    public void update (GameMode gameMode) {
        final Coordinator coordinator = getGameMode(gameMode);
        if (coordinator != null) {
            coordinator.update();
        }
    }
    
    /**
     * Stops the coordinator for a game mode.
     * @param gameMode Game mode to stop the coordinator for.
     */
    public void stop (GameMode gameMode) {
        final Coordinator coordinator = getGameMode(gameMode);
        if (coordinator != null) {
            coordinator.update();
        }
    }
}
