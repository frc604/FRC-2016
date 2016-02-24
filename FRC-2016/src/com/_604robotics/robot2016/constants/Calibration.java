package com._604robotics.robot2016.constants;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public final class Calibration {
    private Calibration () {}

    /* Shifter Solenoid Constants */
    public static final Value SHIFTER_LOW_GEAR = Value.kReverse;
    public static final Value SHIFTER_HIGH_GEAR = Value.kForward;

    /* Shooter Speed Constants */
    public static final double SHOOTER_TARGET_SPEED = 50000;
    public static final double SHOOTER_SPEED_THRESHOLD = 2000;

    /* Left Drive PID Constants */
    public static final double DRIVE_LEFT_PID_P = 0.02;
    public static final double DRIVE_LEFT_PID_I = 0;
    public static final double DRIVE_LEFT_PID_D = 0.005;
    public static final double DRIVE_LEFT_PID_MAX = 0.6;
    public static final double DRIVE_LEFT_PID_TOLERANCE = 20;

    /* Right Drive PID Constants */
    public static final double DRIVE_RIGHT_PID_P = 0.02;
    public static final double DRIVE_RIGHT_PID_I = 0;
    public static final double DRIVE_RIGHT_PID_D = 0.005;
    public static final double DRIVE_RIGHT_PID_MAX = 0.6;
    public static final double DRIVE_RIGHT_PID_TOLERANCE = 20;

    /* Pickup PID Constants */
    public static final double PICKUP_PID_P = -0.016;
    public static final double PICKUP_PID_I = 0;
    public static final double PICKUP_PID_D = -0.016;
    public static final double INTAKE_PID_MIN = -0.5;//not real
    public static final double INTAKE_PID_MAX = 0.5;//not real

    /* Pickup Angles */
    public static final double PICKUP_ZERO_ANGLE = 100;
    public static final double PICKUP_DOWN_ANGLE = 7;
    public static final double PICKUP_MID_ANGLE = 56 ;
    public static final double PICKUP_UP_ANGLE = 121;
    
    /* Teleop Xbox Controller Constants */
    public static final double TELEOP_DEADBAND = 0.3;
    public static final double TELEOP_FACTOR = -1;
}
