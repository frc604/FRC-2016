package com._604robotics.robot2016.constants;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public final class Calibration {
    private Calibration () {}
    /* Shifter solenoid constants */
    public static final Value SHIFTER_LOW_GEAR = Value.kReverse;
    public static final Value SHIFTER_HIGH_GEAR = Value.kForward;

    /* Shooter Speed Constants */
    public static final double SHOOTER_TARGET_SPEED = 30000;
    public static final double SHOOTER_SPEED_THRESHOLD = 2000;
    public static final double SHOOTER_MINIMUM_CHARGE_TIME = 0.5;

    /* Left Drive PID Constants */
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

    /* Pickup Constants */
    public static final double PICKUP_RESET_TIME = 2;
    public static final double PICKUP_PID_MIN = -0.5;//not real
    public static final double PICKUP_PID_MAX = 0.5;//not real
    
    /* Pickup Stow Constants */
    public static final double PICKUP_STOW_TOLERANCE = 5;//not yet calibrated
    public static final double PICKUP_STOW_PID_P = -0.016;
    public static final double PICKUP_STOW_PID_I = 0;
    public static final double PICKUP_STOW_PID_D = -0.016;

    /* Pickup Deploy Constants */
    public static final double PICKUP_DEPLOY_TOLERANCE = 5;//not yet calibrated
    public static final double PICKUP_DEPLOY_PID_P = -0.016;
    public static final double PICKUP_DEPLOY_PID_I = 0;
    public static final double PICKUP_DEPLOY_PID_D = -0.016;

    /* Pickup Angles */
    public static final double PICKUP_ZERO_ANGLE = 0;
    public static final double PICKUP_STOW_ANGLE = 7;
    public static final double PICKUP_DEPLOY_ANGLE = 121;
    
    /* Teleop Xbox Controller Constants */
    public static final double TELEOP_DEADBAND = 0.3;
    public static final double TELEOP_FACTOR = -1;
    
    /* Vision constants */
    public static final double VISION_DIST = 22;
    public static final double VISION_BOTTOM = 68;
    public static final double VISION_LEFTMID = 160-8;
    public static final double VISION_RIGHTMID = 160+8;
    public static final double VISION_TIMER = 1;
    
}
