package com._604robotics.robot2016.constants;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public final class Calibration {
    //Make class uninstantiable
    private Calibration () {}
    
    /* Shifter solenoid constants */
    public static final Value SHIFTER_LOW_GEAR = Value.kReverse;
    public static final Value SHIFTER_HIGH_GEAR = Value.kForward;
    
    /* Shooter speed constants */
    public static final double SHOOTER_TARGET_SPEED = 100;
    public static final double SHOOTER_SPEED_THRESHOLD = 10;
    
    /* Left drive PID constants */
    public static final double DRIVE_LEFT_PID_P = 0.02;
    public static final double DRIVE_LEFT_PID_I = 0;
    public static final double DRIVE_LEFT_PID_D = 0.005;
    public static final double DRIVE_LEFT_PID_MAX = 0.6;
    public static final double DRIVE_LEFT_PID_TOLERANCE = 20;
    
    /* Right drive PID constants */
    public static final double DRIVE_RIGHT_PID_P = 0.02;
    public static final double DRIVE_RIGHT_PID_I = 0;
    public static final double DRIVE_RIGHT_PID_D = 0.005;
    public static final double DRIVE_RIGHT_PID_MAX = 0.6;
    public static final double DRIVE_RIGHT_PID_TOLERANCE = 20;
    
    /* Pickup PID constants */
    public static final double PICKUP_PID_P = 0.02;
    public static final double PICKUP_PID_I = 0;
    public static final double PICKUP_PID_D = 0.02;
    
    /* Pickup angles */
    public static final double PICKUP_ZERO_ANGLE = 0;
    public static final double PICKUP_DOWN_ANGLE = 0;
    public static final double PICKUP_MID_ANGLE = 45;
    public static final double PICKUP_UP_ANGLE = 90;
    
    /* Teleop xbox constants */
    public static final double TELEOP_DEADBAND = 0.3;
    public static final double TELEOP_FACTOR = -1;
}
