package com._604robotics.robot2016.modules;

import com._604robotics.robot2016.Ports;
import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.ActionData;
import com._604robotics.robotnik.action.controllers.ElasticController;
import com._604robotics.robotnik.data.Data;
import com._604robotics.robotnik.data.DataMap;
import com._604robotics.robotnik.module.Module;
import com._604robotics.robotnik.prefabs.devices.MA3A10;
import com._604robotics.robotnik.prefabs.devices.MultiOutput;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Pickup extends Module {
	private final MA3A10 encoder = new MA3A10(Ports.FLIPPER_ENCODER);

    private final Victor leftMotor = new Victor(Ports.FLIPPER_MOTOR_LEFT);
    private final Victor rightMotor = new Victor(Ports.FLIPPER_MOTOR_RIGHT);
    
    private final MultiOutput motors = new MultiOutput(new PIDOutput[] { leftMotor, rightMotor });
    
	private final PIDController pid = new PIDController(0.02, 0D, 0.02, encoder, motors);
	
	public Pickup () {
	    encoder.setZeroAngle(0);
        
        pid.setAbsoluteTolerance(20);
        SmartDashboard.putData("Pickup PID", pid);
        
        set(new DataMap() {{
            add("Pickup Angle", new Data() {
                public double run () {
                    return encoder.getAngle();
                }
            });
        }});
        
        set(new ElasticController() {{
            addDefault("Off", new Action() {
                public void run (ActionData data) {
                    motors.set(0);
                }
            });
        }});
    }
}
