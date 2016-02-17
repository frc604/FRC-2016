package com._604robotics.robot2016.modules;

import com._604robotics.robot2016.Ports;
import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.ActionData;
import com._604robotics.robotnik.action.controllers.ElasticController;
import com._604robotics.robotnik.action.field.FieldMap;
import com._604robotics.robotnik.data.Data;
import com._604robotics.robotnik.data.DataMap;
import com._604robotics.robotnik.module.Module;
import com._604robotics.robotnik.trigger.Trigger;
import com._604robotics.robotnik.trigger.TriggerMap;

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive extends Module {
	//19.6 to 18.6 inches per 100 ticks
	//-490/490 is 360 degrees with both wheels driving, 115 is 90 degrees
	
	// Locking a side: put it 18 in the opposite direction
	// 430 is 180 degrees with one side locked
	
	// When decreasing angle it needs a little bit less than you'd think
	
    private final RobotDrive drive = new RobotDrive(Ports.DRIVE_FRONT_LEFT_MOTOR, Ports.DRIVE_REAR_LEFT_MOTOR, Ports.DRIVE_FRONT_RIGHT_MOTOR, Ports.DRIVE_REAR_RIGHT_MOTOR);
    private final Encoder encoderLeft = new Encoder(Ports.DRIVE_ENCODER_LEFT_A, Ports.DRIVE_ENCODER_LEFT_B, true, CounterBase.EncodingType.k4X);
    private final Encoder encoderRight = new Encoder(Ports.DRIVE_ENCODER_RIGHT_A, Ports.DRIVE_ENCODER_RIGHT_B, false, CounterBase.EncodingType.k4X);
    
    private double pidLeftOut = 0D;
    private double pidRightOut = 0D;
    
    private double pidPowerCap = 0.6;
    
    /** The pid left. */
    private final PIDController pidLeft = new PIDController(0.020, 0D, 0.005, encoderLeft, new PIDOutput () {
        public void pidWrite (double output) {
        	if (output > 0) pidLeftOut = (output > pidPowerCap) ? pidPowerCap : output;
        	else pidLeftOut = (output < -pidPowerCap) ? -pidPowerCap : output;
        }
    });
    
    /** The pid right. */
    private final PIDController pidRight = new PIDController(0.020, 0D, 0.005, encoderRight, new PIDOutput () {
        public void pidWrite (double output) {
        	if (output > 0) pidRightOut = (output > pidPowerCap) ? pidPowerCap : output;
        	else pidRightOut = (output < -pidPowerCap) ? -pidPowerCap : output;
        }
    });
    
    /**
     * Instantiates a new drive.
     */
    public Drive () {
    	// if code doesnt work delete this stuff
        encoderLeft.setPIDSourceType(PIDSourceType.kDisplacement);
        encoderRight.setPIDSourceType(PIDSourceType.kDisplacement);
        // up to here
        
        pidLeft.setAbsoluteTolerance(30);
        pidRight.setAbsoluteTolerance(30);
        
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
            {
            	
            }
        }});
        
        this.set(new TriggerMap() {{
            add("At Left Servo Target", new Trigger() {
                private final Timer timer = new Timer();
                private boolean timing = false;
                
                public boolean run () {
                    if (pidLeft.isEnabled() && pidLeft.onTarget()) {
                        if (!timing) {
                            timing = true;
                            timer.start();
                        }
                        
                        return timer.get() >= 0.5;
                    } else {
                        if (timing) {
                            timing = false;
                            
                            timer.stop();
                            timer.reset();
                        }
                        
                        return false;
                    }
                }
            });
            
            add("At Right Servo Target", new Trigger() {
                private final Timer timer = new Timer();
                private boolean timing = false;
                
                public boolean run () {
                    if (pidRight.isEnabled() && pidRight.onTarget()) {
                        if (!timing) {
                            timing = true;
                            timer.start();
                        }
                        
                        return timer.get() >= 0.5;
                    } else {
                        if (timing) {
                            timing = false;
                            
                            timer.stop();
                            timer.reset();
                        }
                        
                        return false;
                    }
                }
            });
        }});
        
        this.set(new ElasticController() {{
            addDefault("Off", new Action() {
            	public void begin (ActionData data){
            		encoderLeft.reset();
            		encoderRight.reset();
            	}
            	
                public void run (ActionData data) {
                    drive.tankDrive(0D, 0D);
                }
            });
            
            add("Geared Drive", new Action(new FieldMap () {{
                define("left", 0D);
                define("right", 0D);
                define("Left Low Gear", false);
                define("Left High Gear", false);
                define("Right Low Gear", false);
                define("Right High Gear", false);
                }}) {
             
                public void run (ActionData data) {
                	double Lgear = 1.0;
                	double Rgear = 1.0;
                	if( data.is("Left Low Gear") && data.is("Left High Gear") )
                	{
                		Lgear = 1.5;
                	}
                	else if( data.is("Left Low Gear") )
                	{
                		Lgear = 0.8;
                	}
                	else if( data.is("Left High Gear") )
                	{
                		Lgear = 1.5;
                	}
                	if( data.is("Right Low Gear") && data.is("Right High Gear") )
                	{
                		Rgear = 1.8;
                	}
                	else if( data.is("Right Low Gear") )
                	{
                		Rgear = 0.8;
                	}
                	else if( data.is("Right High Gear") )
                	{
                		Rgear = 1.2;
                	}
                    drive.tankDrive(data.get("left")*0.5*Lgear, data.get("right")*0.5*Rgear);
                }
                
                public void end (ActionData data) {
                    drive.stopMotor();
                }
            });
            
            add("Tank Drive", new Action(new FieldMap () {{
                define("left", 0D);
                define("right", 0D);
                define("throttle", 1D);
            }}) {
                public void run (ActionData data) {
                    drive.tankDrive(data.get("left") * data.get("throttle"), data.get("right") * data.get("throttle"));
                }
                
                public void end (ActionData data) {
                    drive.stopMotor();
                }
            });
            
            add("Servo Drive", new Action(new FieldMap() {{
                define("left clicks", 0D);
                define("right clicks", 0D);
                define("power cap", 0.6D);
            }}) {
            	double startLeftClicks;
            	double startRightClicks;
            	
                public void begin (ActionData data) {
                	pidPowerCap = data.get("power cap");
                	startLeftClicks = data.data("Left Drive Clicks");
                	startRightClicks = data.data("Right Drive Clicks");
                    pidLeft.setSetpoint(data.get("left clicks") + startLeftClicks);
                    pidRight.setSetpoint(data.get("right clicks") + startRightClicks);
                    pidLeft.enable();
                    pidRight.enable();
                }
                
                public void run (ActionData data){
                	if(pidLeft.getSetpoint() != data.get("left clicks") + startLeftClicks){
                		pidLeft.setSetpoint(data.get("left clicks") + startLeftClicks);
                	}
                	
                	if(pidRight.getSetpoint() != data.get("right clicks") + startRightClicks){
                		pidRight.setSetpoint(data.get("right clicks") + startRightClicks);
                	}
                	
                	drive.tankDrive(pidLeftOut, pidRightOut);
                }
                
                public void end (ActionData data) {
                    pidLeft.reset();
                    pidRight.reset();
                    pidLeft.disable();
                    pidRight.disable();
                }
            });
        }});
    }
}
