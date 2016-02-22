package com._604robotics.robot2016.constants;

public final class Ports {
    private Ports () {}

    /* Controller */
    public static final int DRIVER = 0;
    public static final int MANIPULATOR = 1;
    
    /* Digital Input */
    public static final int SHOOTER_ENCODER_B = 4;
    public static final int SHOOTER_ENCODER_A = 5;
    public static final int DRIVE_ENCODER_RIGHT_A = 6;
    public static final int DRIVE_ENCODER_RIGHT_B = 7;
    public static final int DRIVE_ENCODER_LEFT_A = 8;
    public static final int DRIVE_ENCODER_LEFT_B = 9;
    
    /* Digital Output */
    public static final int COMPRESSOR = 0;
    public static final int INTAKE_MOTOR = 1;
    public static final int FLIPPER_MOTOR_RIGHT = 2;
    public static final int FLIPPER_MOTOR_LEFT = 3;
    public static final int SHOOTER_MOTOR_RIGHT = 4;
    public static final int SHOOTER_MOTOR_LEFT = 5;
    public static final int DRIVE_FRONT_RIGHT_MOTOR = 6;
    public static final int DRIVE_REAR_RIGHT_MOTOR = 7;
    public static final int DRIVE_FRONT_LEFT_MOTOR = 8;
    public static final int DRIVE_REAR_LEFT_MOTOR = 9;
    
    /* Analog Input */
    public static final int FLIPPER_ENCODER = 0;
    
    /* Solenoids */
    public static final int SHIFTER_SOLENOID_FORWARD = 4;
    public static final int SHIFTER_SOLENOID_REVERSE = 5;
}
