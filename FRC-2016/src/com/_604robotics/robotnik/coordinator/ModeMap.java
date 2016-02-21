package com._604robotics.robotnik.coordinator;

import com._604robotics.robotnik.GameMode;
import com._604robotics.robotnik.module.ModuleManager;

public class ModeMap {
    private Coordinator autonomousMode = new Coordinator();
    private Coordinator teleopMode = new Coordinator();
    
    public void attach (ModuleManager modules) {
        this.autonomousMode.attach(modules);
        this.teleopMode.attach(modules);
    }

    protected void setAutonomousMode (Coordinator autonomousMode) {
        this.autonomousMode = autonomousMode;
    }

    protected void setTeleopMode (Coordinator teleopMode) {
        this.teleopMode = teleopMode;
    }

    public Coordinator getAutonomousMode () {
        return this.autonomousMode;
    }

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
            break;
        case TELEOP:
            getTeleopMode().update();
            break;
        default:
        }
    }
    
    public void stop (GameMode gameMode) {
        switch (gameMode) {
        case AUTONOMOUS:
            getAutonomousMode().stop();
            break;
        case TELEOP:
            getTeleopMode().stop();
            break;
        default:
        }
    }
}
