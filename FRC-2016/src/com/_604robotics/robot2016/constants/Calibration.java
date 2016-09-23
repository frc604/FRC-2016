package com._604robotics.robot2016.constants;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public final class Calibration {
    private Calibration () {}
    
    /* Safe Mode Constants */
    public static final double SAFE_MODE_OFF = 1.0;
    public static final double SAFE_MODE_ON = 0.5;
    
    /* Autonomous Mode Distances */
    public static final double AUTON_FORWARD_CLICKS = 2700;
    public static final double AUTON_BACKWARD_CLICKS = -2700; // This value is here because there are parts in the code which will require the robot to do a 180.

    /* Shifter Solenoid Constants */
    public static final Value SHIFTER_LOW_GEAR = Value.kReverse;
    public static final Value SHIFTER_HIGH_GEAR = Value.kForward;
    
    /* Pickup Solenoid Constants */
    public static final Value PICKUP_STOW = Value.kReverse;
    public static final Value PICKUP_DEPLOY = Value.kForward;

    /* Double Clamp Solenoid Constants */
    public static final Value DOUBLE_CLAMP_CLOSE = Value.kReverse;
    public static final Value DOUBLE_CLAMP_OPEN = Value.kForward;
    
    /* CLAMP Solenoid Constants */
    public static final int CLAMP_MOVE_TIME = 1;
    public static final boolean CLAMP_CLOSE = false;
    public static final boolean CLAMP_OPEN = true;
    
    /* Pickup Timer Constants */
    public static final double PICKUP_MOVE_TIME = 1;
   
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
