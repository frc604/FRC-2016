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

public class Pickup extends Module {
    private final RobotDrive flip = new RobotDrive(Ports.FLIPPER_MOTOR_LEFT, Ports.FLIPPER_MOTOR_RIGHT);
	private final Encoder encoder = new Encoder (Ports.FLIPPER_ENCODER_RIGHT_A, Ports.FLIPPER_ENCODER_RIGHT_B,true, CounterBase.EncodingType.k4X);
    
	private final double upSetPoint = 115; /* TBD */
	private final double midSetPoint = 90; /* TBD */
	private final double downSetPoint = 0; /* TBD */
	private final double MSPLeeway = 0; /* TBD */
	private final double flipperPower = 0.2; /* TBD */
	
	private double pidPowerCap = 1; /* TBD */
	private double pidOutput = 0D;
    
	private final PIDController pid = new PIDController(0.02, 0D, 0.02, encoder, new PIDOutput () {
        public void pidWrite (double output) {
        	if (output > 0) pidOutput = (output > pidPowerCap) ? pidPowerCap : output;
        	else pidOutput = (output < -pidPowerCap) ? -pidPowerCap : output;
        }
    });
    public Pickup () {
    	// if code doesnt work delete this stuff
        encoder.setPIDSourceType(PIDSourceType.kDisplacement);
        
        pid.setAbsoluteTolerance(20);
        
        SmartDashboard.putData("Pickup PID", pid);
        
        this.set(new DataMap() {{
            add("Pickup Clicks", new Data() {
                public double run () {
                    return encoder.get();
                }
            });
            add("Pickup Rate", new Data() {
                public double run () {
                    return encoder.getRate();
                }
            });
        }});
        this.set(new TriggerMap() {{
            add("At Pickup Target", new Trigger() {
                private final Timer timer = new Timer();
                private boolean timing = false;
                
                public boolean run () {
                    if (pid.isEnabled() && pid.onTarget()) {
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
            
            add("Up Position", new Trigger() {
            	public boolean run () {
            		if(encoder.get() >= upSetPoint) return true;
            		else return false;
            	}
            });
            
            add("Down Position", new Trigger() {
            	public boolean run () {
            		if(encoder.get() <= downSetPoint) return true;
            		else return false;
            	}
            });
            
        }});
        
        this.set(new ElasticController() {{
            addDefault("Off", new Action() {
            	public void begin (ActionData data){
            		encoder.reset();
            	}
            	
                public void run (ActionData data) {
                    flip.tankDrive(0D, 0D);
                }
            });
            
            add("Flip Up", new Action(new FieldMap () {{
                define("On", false);
            }}) {
                public void run (ActionData data) {
                	int allowed = 0;
                	double power = 0.0;
                	if( data.trigger("Up Position") == false )
                	{
                		allowed = 1;
                	}
                	if( data.is("On") )
                	{
                		power = flipperPower;
                	}
                    flip.arcadeDrive(power*allowed, 0);
                }
                
                public void end (ActionData data) {
                    flip.stopMotor();
                }
            });
            
            add("Flip Down", new Action(new FieldMap () {{
                define("On", false);
            }}) {
                public void run (ActionData data) {
                	int allowed = 0;
                	double power = 0.0;
                	if( data.trigger("Down Position") == false )
                	{
                		allowed = 1;
                	}
                	if( data.is("On") )
                	{
                		power = -flipperPower;
                	}
                    flip.arcadeDrive(power*allowed, 0);
                }
                
                public void end (ActionData data) {
                    flip.stopMotor();
                }
            });
            
            add("Find Mid", new Action(new FieldMap () {{
                define("on", false);
            }}) {
                public void run (ActionData data) {
                	double power = 0.0;
                	int direction = 0;
                	if( encoder.get() > midSetPoint + MSPLeeway )
                	{
                		direction = -1;
                	}
                	if( encoder.get() < midSetPoint - MSPLeeway )
                	{
                		direction = 1;
                	}
                	if( data.is("On") )
                	{
                		power = flipperPower;
                	}
                    flip.arcadeDrive(power*direction, 0);
                }
                
                public void end (ActionData data) {
                    flip.stopMotor();
                }
            });
            
            add("Servo Flip", new Action(new FieldMap() {{
                define("clicks", 0D);
                define("power cap", 0.6D);
            }}) {
            	double startClicks;
            	
                public void begin (ActionData data) {
                	pidPowerCap = data.get("power cap");
                	startClicks = data.data("Pickup Clicks");
                    pid.setSetpoint(data.get("clicks") + startClicks);
                    pid.enable();
                }
                
                public void run (ActionData data){
                	if(pid.getSetpoint() != data.get("left clicks") + startClicks){
                		pid.setSetpoint(data.get("left clicks") + startClicks);
                	}
                	flip.tankDrive(pidOutput, pidOutput);
                }
                
                public void end (ActionData data) {
                    pid.reset();
                    pid.disable();
                }
            });
        }});
    }
}
