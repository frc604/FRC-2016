package com._604robotics.robotnik.coordinator;

import com._604robotics.robotnik.GameMode;
import com._604robotics.robotnik.Robot;

public class ModeManager<T extends Robot<T>> {
    private Coordinator<T> autonomousMode = new Coordinator<T>();
    private Coordinator<T> teleopMode = new Coordinator<T>();
    
    public void attach (T robot) {
        this.autonomousMode.attach(robot);
        this.teleopMode.attach(robot);
    }
    
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
        switch (gameMode) {
        case AUTONOMOUS:
            getAutonomousMode().update();
        case TELEOP:
            getTeleopMode().update();
        default:
        }
    }
    
    public void stop (GameMode gameMode) {
        switch (gameMode) {
        case AUTONOMOUS:
            getAutonomousMode().stop();
        case TELEOP:
            getTeleopMode().stop();
        default:
        }
    }
}
