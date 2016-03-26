package com._604robotics.robot2016.modules;

import static com._604robotics.robot2016.constants.Calibration.PICKUP_PID_MAX;

import com._604robotics.robot2016.constants.Calibration;
import com._604robotics.robot2016.constants.Ports;
import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.ActionData;
import com._604robotics.robotnik.action.controllers.ElasticController;
import com._604robotics.robotnik.action.field.FieldMap;
import com._604robotics.robotnik.data.DataMap;
import com._604robotics.robotnik.module.Module;
import com._604robotics.robotnik.prefabs.devices.MultiOutput;
import com._604robotics.robotnik.prefabs.devices.ResettablePIDSource;
import com._604robotics.robotnik.prefabs.devices.TalonPWMEncoder;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Pickup extends Module {
    private final CANTalon talon = new CANTalon(Ports.PICKUP_TALON);
    private final Victor victor = new Victor(Ports.PICKUP_VICTOR);
    
    private final MultiOutput motors = new MultiOutput(talon, victor);    
    private final ResettablePIDSource encoder = new ResettablePIDSource(new TalonPWMEncoder(talon));
    
    private final PIDController pidStow = new PIDController(
            Calibration.PICKUP_STOW_PID_P,
            Calibration.PICKUP_STOW_PID_I,
            Calibration.PICKUP_STOW_PID_D,
            encoder, motors);
    private final PIDController pidDeploy = new PIDController(
            Calibration.PICKUP_DEPLOY_PID_P,
            Calibration.PICKUP_DEPLOY_PID_I,
            Calibration.PICKUP_DEPLOY_PID_D,
            encoder, motors);
    
    public Pickup () {
    	talon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
    	encoder.setZero(Calibration.PICKUP_ZERO_ANGLE);
    	
    	victor.setInverted(true);
    	
        pidStow.setOutputRange(Calibration.PICKUP_PID_MIN, PICKUP_PID_MAX);
        pidDeploy.setOutputRange(Calibration.PICKUP_PID_MIN, Calibration.PICKUP_PID_MAX);
        
        SmartDashboard.putData("Pickup Stow PID", pidStow);
        SmartDashboard.putData("Pickup Deploy PID", pidDeploy);
        
        set(new DataMap() {{
            add("Pickup Angle", encoder::get);
            add("Pickup Velocity", talon::getPulseWidthVelocity);
        }});

        set(new ElasticController() {{
            addDefault("Manual", new Action(new FieldMap() {{
                define("Power", 0D);
                define("Reset Encoder", false);
                define("Deploy Angle", Calibration.PICKUP_DEPLOY_ANGLE);
            }}) {
                private final Timer resetTimer = new Timer();
                
                @Override
                public void begin (ActionData data) {
                    resetTimer.start();
                }

                @Override
                public void run (ActionData data) {
                    if (!data.is("Reset Encoder")) {
                        resetTimer.reset();
                    }

                    if (resetTimer.get() > Calibration.PICKUP_RESET_TIME) {
                        encoder.setZeroRel(data.data("Deploy Angle"));
                    }

                    motors.set(data.get("Power")*Calibration.PICKUP_POWER_COEFF);
                }
                
                @Override
                public void end (ActionData data) {
                    resetTimer.stop();
                    resetTimer.reset();
                }
            });

            add("Stow", new AngleAction(pidStow, Calibration.PICKUP_STOW_ANGLE, Calibration.PICKUP_STOW_PID_TOLERANCE));
            add("Deploy", new AngleAction(pidDeploy, Calibration.PICKUP_DEPLOY_ANGLE, Calibration.PICKUP_DEPLOY_PID_TOLERANCE));
            
            add("Stow Alt", new Action(new FieldMap() {{
            	define("Power", Calibration.PICKUP_STOW_POWER);
            	define("Threshold", Calibration.PICKUP_STOW_THRESHOLD);
            }}) {
				@Override
				public void run (ActionData data) {
					final double angle = data.data("Pickup Angle");
					final double power = data.get("Power");
					final double threshold = data.get("Threshold");
					if( angle > threshold )
					{
						motors.set(0);
					} else
					{
						motors.set(power);
					}
				}

				@Override
				public void end(ActionData data) {
					motors.set(0);
				}
            });
            
            add("Deploy Alt", new Action(new FieldMap() {{
            	define("Upper Threshold", Calibration.PICKUP_DEPLOY_UPPERTHRESHOLD);
            	define("Lower Threshold", Calibration.PICKUP_DEPLOY_LOWERTHRESHOLD);
            	define("Upper Power", Calibration.PICKUP_UPPER_POWER);
            	define("Lower Power",Calibration.PICKUP_LOWER_POWER);
            }}) {
				@Override
				public void run (ActionData data) {
					final double angle = data.data("Pickup Angle");
					final double upperThreshold = data.get("Upper Threshold");
					final double lowerThreshold = data.get("Lower Threshold");
					final double upperPower = data.get("Upper Power");
					final double lowerPower = data.get("Lower Power");

					
					if( angle > upperThreshold )
					{
						motors.set(upperPower);
					} 
					else if(angle > lowerThreshold) 
					{
						motors.set(lowerPower);
					}
					else
					{
						motors.set(0);
					}
				}

				@Override
				public void end(ActionData data) {
					motors.set(0);
				}
            });
        }});
    }
        
    private class AngleAction extends Action {
        private final PIDController pid;
        
        public AngleAction (PIDController pid, double defaultAngle, double defaultTolerance) {
            super(new FieldMap() {{
                define("Setpoint", defaultAngle);
                define("Tolerance", defaultTolerance);
            }});
            this.pid = pid;
        }

        @Override
        public void begin (ActionData data) {
            pid.setSetpoint(data.get("Setpoint"));
            pid.enable();
        }

        @Override
        public void run (ActionData data) {
            final double angle = data.data("Pickup Angle");
            final double setpoint = data.get("Setpoint");
            final double tolerance = data.get("Tolerance");
            
            if (setpoint != pid.getSetpoint()) {
                pid.setSetpoint(setpoint);
            }
            
            if (Math.abs(angle - setpoint) < tolerance) {
                if (pid.isEnabled()) {
                    pid.disable();
                }
                motors.set(0);
            } else if(!pid.isEnabled()) {
                pid.enable();
            }
        }

        @Override
        public void end (ActionData data) {
            pid.disable();
        }
    }
}
