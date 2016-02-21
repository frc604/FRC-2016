package com._604robotics.robot2016.modules;

import com._604robotics.robot2016.constants.Calibration;
import com._604robotics.robot2016.constants.Ports;
import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.ActionData;
import com._604robotics.robotnik.action.controllers.ElasticController;
import com._604robotics.robotnik.action.field.FieldMap;
import com._604robotics.robotnik.data.Data;
import com._604robotics.robotnik.data.DataMap;
import com._604robotics.robotnik.module.Module;
import com._604robotics.robotnik.prefabs.devices.TankDrivePIDOutput;
import com._604robotics.robotnik.trigger.Trigger;
import com._604robotics.robotnik.trigger.TriggerMap;

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive extends Module {
	// 19.6 to 18.6 inches per 100 ticks
	// -490/490 is 360 degrees with both wheels driving, 115 is 90 degrees
	
	// Locking a side: put it 18 in the opposite direction
	// 430 is 180 degrees with one side locked
	
	// When decreasing angle it needs a little bit less than you'd think
	
    private final RobotDrive drive = new RobotDrive(
            Ports.DRIVE_FRONT_LEFT_MOTOR,
            Ports.DRIVE_REAR_LEFT_MOTOR,
            Ports.DRIVE_FRONT_RIGHT_MOTOR,
            Ports.DRIVE_REAR_RIGHT_MOTOR);

    private final Encoder encoderLeft = new Encoder(
            Ports.DRIVE_ENCODER_LEFT_A,
            Ports.DRIVE_ENCODER_LEFT_B,
            true, CounterBase.EncodingType.k4X);
    private final Encoder encoderRight = new Encoder(
            Ports.DRIVE_ENCODER_RIGHT_A,
            Ports.DRIVE_ENCODER_RIGHT_B,
            false, CounterBase.EncodingType.k4X);
    
    private final TankDrivePIDOutput pidOutput = new TankDrivePIDOutput(drive);
    
    private final PIDController pidLeft = new PIDController(
            Calibration.DRIVE_LEFT_PID_P,
            Calibration.DRIVE_LEFT_PID_I,
            Calibration.DRIVE_LEFT_PID_D,
            encoderLeft,
            pidOutput.left);
    private final PIDController pidRight = new PIDController(
            Calibration.DRIVE_RIGHT_PID_P,
            Calibration.DRIVE_RIGHT_PID_I,
            Calibration.DRIVE_RIGHT_PID_D,
            encoderRight,
            pidOutput.right);
    
    public Drive () {
        encoderLeft.setPIDSourceType(PIDSourceType.kDisplacement);
        encoderRight.setPIDSourceType(PIDSourceType.kDisplacement);

        pidLeft.setOutputRange(-Calibration.DRIVE_LEFT_PID_MAX, Calibration.DRIVE_LEFT_PID_MAX);
        pidRight.setOutputRange(-Calibration.DRIVE_RIGHT_PID_MAX, Calibration.DRIVE_RIGHT_PID_MAX);
        
        pidLeft.setAbsoluteTolerance(Calibration.DRIVE_LEFT_PID_TOLERANCE);
        pidRight.setAbsoluteTolerance(Calibration.DRIVE_RIGHT_PID_TOLERANCE);
        
        SmartDashboard.putData("Left Drive PID", pidLeft);
        SmartDashboard.putData("Right Drive PID", pidRight);
        
        this.set(new DataMap() {{
            add("Left Drive Clicks", new Data() {
                public double run () {
                    return encoderLeft.get();
                }
            });
            
            add("Right Drive Clicks", new Data() {
                public double run () {
                    return encoderRight.get();
                }
            });
            
            add("Left Drive Rate", new Data() {
                public double run () {
                    return encoderLeft.getRate();
                }
            });
            
            add("Right Drive Rate", new Data() {
                public double run () {
                    return encoderRight.getRate();
                }
            });

            add("Left PID Error", new Data() {
            	public double run() {
            		return pidLeft.getAvgError();
            	}
            });

            add("Right PID Error", new Data() {
            	public double run() {
            		return pidRight.getAvgError();
            	}
            });
        }});
        
        this.set(new TriggerMap() {{
            add("At Left Servo Target", new Trigger() {
                public boolean run () {
                    return pidLeft.isEnabled() && pidLeft.onTarget();
                }
            });
            
            add("At Right Servo Target", new Trigger() {
                public boolean run () {
                    return pidRight.isEnabled() && pidRight.onTarget();
                }
            });
        }});
        
        this.set(new ElasticController() {{
            addDefault("Off", new Action() {
                public void run (ActionData data) {
                    drive.tankDrive(0, 0);
                }
            });
            
            add("Tank Drive", new Action(new FieldMap () {{
                define("Left Power", 0D);
                define("Right Power", 0D);
                define("Throttle", 1D);
            }}) {
                public void run (ActionData data) {
                    drive.tankDrive(data.get("Left Power") * data.get("Throttle") * 0.5,
                                    data.get("Right Power") * data.get("Throttle") * 0.5);
                }
                
                public void end (ActionData data) {
                    drive.stopMotor();
                }
            });
            
            add("Geared Drive", new Action(new FieldMap () {{
                define("Left Power", 0);
                define("Right Power", 0);
                define("Left Low Gear", false);
                define("Left High Gear", false);
                define("Right Low Gear", false);
                define("Right High Gear", false);
            }}) {
                public void run (ActionData data) {
                	double leftGear = 0.75;
                	double rightGear = 0.75;

                	if (data.is("Left Low Gear") && data.is("Left High Gear")) {
                		leftGear = 0.5;
                	} else if (data.is("Left Low Gear")) {
                		leftGear = 0.5;
                	} else if (data.is("Left High Gear")) {
                		leftGear = 1.0;
                	}

                	if (data.is("Right Low Gear") && data.is("Right High Gear")) {
                		rightGear = 0.5;
                	} else if (data.is("Right Low Gear")) {
                		rightGear = 0.5;
                	} else if(data.is("Right High Gear")) {
                		rightGear = 1.0;
                	}

                    drive.tankDrive(data.get("Left Power") * leftGear,
                                    data.get("Right Power") * rightGear);
                }
                
                public void end (ActionData data) {
                    drive.stopMotor();
                }
            });
            
            add("Servo Drive", new Action(new FieldMap() {{
                define("Left Clicks", 0D);
                define("Right Clicks", 0D);
            }}) {
                public void begin (ActionData data) {
            		encoderLeft.reset();
            		encoderRight.reset();

                    pidLeft.setSetpoint(data.get("Left Clicks"));
                    pidRight.setSetpoint(data.get("Right Clicks"));

                    pidLeft.enable();
                    pidRight.enable();
                }
                
                public void run (ActionData data){
                	if (pidLeft.getSetpoint() != data.get("Left Clicks")) {
                	    pidLeft.reset();
                	    encoderLeft.reset();

                		pidLeft.setSetpoint(data.get("Left Clicks"));
                		pidLeft.enable();
                	}
                	
                	if (pidRight.getSetpoint() != data.get("Right Clicks")) {
                	    pidRight.reset();
                	    encoderRight.reset();

                		pidRight.setSetpoint(data.get("Right Clicks"));
                		pidRight.enable();
                	}
                }
                
                public void end (ActionData data) {
                    pidLeft.reset();
                    pidRight.reset();
                }
            });
        }});
    }
}
