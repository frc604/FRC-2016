package com._604robotics.robotnik.prefabs.devices;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.RobotDrive;

public class TankDrivePIDOutput {
    private final RobotDrive drive;
    
    private double leftPower = 0D;
    private double rightPower = 0D;

    public final PIDOutput left = new PIDOutput() {
        @Override
        public void pidWrite (double output) {
            leftPower = output;
            update();
        }
    };

    public final PIDOutput right = new PIDOutput() {
        @Override
        public void pidWrite (double output) {
            rightPower = output;
            update();
        }
    };
    
    public TankDrivePIDOutput (RobotDrive drive) {
        this.drive = drive;
    }
    
    private synchronized void update () {
        drive.tankDrive(leftPower, rightPower);
    }
}
