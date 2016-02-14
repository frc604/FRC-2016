package com._604robotics.robotnik;

import edu.wpi.first.wpilibj.DriverStation;

public enum GameMode {
    DISABLED("Disabled") {
        @Override
        public boolean active () {
            return !DriverStation.getInstance().isEnabled();
        }
    },
    AUTONOMOUS("Autonomous") {
        @Override
        public boolean active () {
            return DriverStation.getInstance().isEnabled() && DriverStation.getInstance().isAutonomous();
        }
    },
    TELEOP("Teleop") {
        @Override
        public boolean active () {
            return DriverStation.getInstance().isEnabled() && DriverStation.getInstance().isOperatorControl();
        }
    };

    private final String prettyName;

    private GameMode (String prettyName) {
        this.prettyName = prettyName;
    }

    public abstract boolean active ();

    public String prettyName () {
        return prettyName;
    }
}
