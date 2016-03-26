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
   
    /*Intake constants*/
    public static final double INTAKE_SHOOT_POWER = 0.7;

    /* Left Drive PID Constants */
    public static final double DRIVE_MOVE_PID_P = 0.02;
    public static final double DRIVE_MOVE_PID_I = 0;
    public static final double DRIVE_MOVE_PID_D = 0.005;
    public static final double DRIVE_MOVE_PID_MAX = 1.0;
    public static final double DRIVE_MOVE_PID_TOLERANCE = 20;

    /* Right Drive PID Constants */
    public static final double DRIVE_ROTATE_PID_P = 0.02;
    public static final double DRIVE_ROTATE_PID_I = 0;
    public static final double DRIVE_ROTATE_PID_D = 0.005;
    public static final double DRIVE_ROTATE_PID_MAX = 1.0;
    public static final double DRIVE_ROTATE_PID_TOLERANCE = 20;

    /* Pickup Constants */
    public static final double PICKUP_RESET_TIME = 2;
    public static final double PICKUP_PID_MIN = -0.5;//not real
    public static final double PICKUP_PID_MAX = 0.5;//not real
    public static final double PICKUP_POWER_COEFF = 0.6;
    
    /* Pickup Stow Constants */
    public static final double PICKUP_STOW_PID_P = 0.001;
    public static final double PICKUP_STOW_PID_I = 0;
    public static final double PICKUP_STOW_PID_D = 0.002;
    public static final double PICKUP_STOW_PID_MAX = 1.0;
    public static final double PICKUP_STOW_PID_TOLERANCE = 0;
    public static final double PICKUP_STOW_THRESHOLD = -480;
    public static final double PICKUP_STOW_POWER = 0.7;
    
    /* Pickup Deploy Constants */
    public static final double PICKUP_DEPLOY_PID_P = 0.016;
    public static final double PICKUP_DEPLOY_PID_I = 0;
    public static final double PICKUP_DEPLOY_PID_D = 0.016;
    public static final double PICKUP_DEPLOY_PID_MAX = 1.0;
    public static final double PICKUP_DEPLOY_PID_TOLERANCE = 0;
    public static final double PICKUP_DEPLOY_UPPERTHRESHOLD = -400;
    public static final double PICKUP_DEPLOY_LOWERTHRESHOLD = -945;//calibrate

    public static final double PICKUP_UPPER_POWER = -0.3;
    public static final double PICKUP_LOWER_POWER = -0.1;


    /* Pickup Angles */
    public static final double PICKUP_ZERO_ANGLE = 3365; // this is the angle before match starts
    public static final double PICKUP_STOW_ANGLE = -328;
    public static final double PICKUP_DEPLOY_ANGLE = -1300;
    
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
