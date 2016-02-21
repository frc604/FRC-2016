package com._604robotics.robot2016.constants;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public final class Calibration {
    private Calibration () {}
    
    public static final Value SHIFTER_LOW_GEAR = Value.kReverse;
    public static final Value SHIFTER_HIGH_GEAR = Value.kForward;
    
    public static final double SHOOTER_TARGET_SPEED = 100;
    public static final double SHOOTER_SPEED_THRESHOLD = 10;
    
    public static final double PICKUP_ZERO_ANGLE = 0;
    public static final double PICKUP_DOWN_ANGLE = 0;
    public static final double PICKUP_MID_ANGLE = 45;
    public static final double PICKUP_UP_ANGLE = 90;
}
