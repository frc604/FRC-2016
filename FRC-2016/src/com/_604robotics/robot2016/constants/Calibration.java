package com._604robotics.robot2016.constants;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public final class Calibration {
    private Calibration () {}
    
    /* Autonomous Mode Distances */
    public static final double AUTON_FORWARD_CLICKS = 2700;
    public static final double AUTON_BACKWARD_CLICKS = -2700; // This value is here because there are parts in the code which will require the robot to do a 180.

    /* Shifter Solenoid Constants */
    public static final Value SHIFTER_LOW_GEAR = Value.kReverse;
    public static final Value SHIFTER_HIGH_GEAR = Value.kForward;
    
    /* Pickup Solenoid Constants */
    public static final Value PICKUP_STOW = Value.kReverse;
    public static final Value PICKUP_DEPLOY = Value.kForward;

    /* Clamp Solenoid Constants */
    public static final int CLAMP_MOVE_TIME = 0; //not real
    public static final boolean CLAMP_STOW = false;
    public static final boolean CLAMP_DEPLOY = true;
    
    /* Pickup Timer Constants */
    public static final double PICKUP_MOVE_TIME = 1;
    
    /* Shooter Speed Constants */
    public static final double SHOOTER_TARGET_SPEED = 50000;
    public static final double SHOOTER_SPEED_THRESHOLD = 3000;
    public static final double SHOOTER_MINIMUM_CHARGE_TIME = 0.5;
   
    /*Intake constants*/
    public static final double INTAKE_SHOOT_POWER = 0.7;

    /* Drive Movement Constants */
    public static final double DRIVE_MOVE_PID_P = 0.02;
    public static final double DRIVE_MOVE_PID_I = 0;
    public static final double DRIVE_MOVE_PID_D = 0.005;
    public static final double DRIVE_MOVE_PID_MAX = 1.0;
    public static final double DRIVE_MOVE_PID_TOLERANCE = 20;

    /* Drive Rotation Constants */
    // NONE OF THESE ARE REAL
    public static final double DRIVE_ROTATE_PID_P = 0.02;
    public static final double DRIVE_ROTATE_PID_I = 0;
    public static final double DRIVE_ROTATE_PID_D = 0.005;
    public static final double DRIVE_ROTATE_PID_MAX = 1.0;
    public static final double DRIVE_ROTATE_PID_TOLERANCE = 20;
    
    /* Teleop Xbox Controller Constants */
    public static final double TELEOP_DEADBAND = 0.3;
    public static final double TELEOP_FACTOR = -1;
}
