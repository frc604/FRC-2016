package com._604robotics.robot2016.constants;

public final class Ports {
    private Ports () {}

    /* Mantis */
    public static final int CLAMP = 3; 
    
    /* PP */
    public static final int PP_EXTEND = 6;
    public static final int PP_RETRACT = 7;
    
    /* Controller */
    public static final int DRIVER = 0;
    public static final int MANIPULATOR = 1;
    
    /* Analog Input */
    public static final int HORIZONTAL_GYRO = 0;
    
    /* Digital Input */
    public static final int DRIVE_ENCODER_RIGHT_A = 0;
    public static final int DRIVE_ENCODER_RIGHT_B = 1;
    public static final int DRIVE_ENCODER_LEFT_A = 2;
    public static final int DRIVE_ENCODER_LEFT_B = 3;
    
    /* Digital Output */
    public static final int COMPRESSOR = 0;
    public static final int INTAKE_MOTOR = 1;
    // public static final int PICKUP_TALON = 2;
    // public static final int PICKUP_VICTOR = 3;
    public static final int DRIVE_FRONT_RIGHT_MOTOR = 6;
    public static final int DRIVE_REAR_RIGHT_MOTOR = 7;
    public static final int DRIVE_FRONT_LEFT_MOTOR = 8;
    public static final int DRIVE_REAR_LEFT_MOTOR = 9;
    
    /* Solenoids */
    public static final int SHIFTER_SOLENOID_FORWARD = 4;
    public static final int SHIFTER_SOLENOID_REVERSE = 5;
}
