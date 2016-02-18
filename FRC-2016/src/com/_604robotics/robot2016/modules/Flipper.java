package com._604robotics.robot2016.modules;

import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.ActionData;
import com._604robotics.robotnik.action.controllers.ElasticController;
import com._604robotics.robotnik.action.field.FieldMap;
import com._604robotics.robotnik.data.DataMap;
import com._604robotics.robotnik.data.Data;
import com._604robotics.robotnik.module.Module;
import com._604robotics.robot2016.Ports;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
//note "9000" used for not yet known values

public class Flipper extends Module {
	private final Victor rightmotor = new Victor(Ports.FLIPPER_MOTOR_RIGHT);//encoder on right//in rev
	private final Victor  leftmotor = new Victor(Ports.FLIPPER_MOTOR_LEFT);
	private final Encoder encoder = new Encoder (Ports.FLIPPER_ENCODER_RIGHT_A, Ports.FLIPPER_ENCODER_RIGHT_B,true, CounterBase.EncodingType.k4X);
	
	private double pidPowerCap = 9000;//determine later
	private double pidOutput = 0D;
	
	private final PIDController pid = new PIDController(9000, 0D, 9000, encoder, new PIDOutput () {
        public void pidWrite (double output) {
        	if (output > 0) pidOutput = (output > pidPowerCap) ? pidPowerCap : output;
        	else pidOutput = (output < -pidPowerCap) ? -pidPowerCap : output;
        }
    });
	
	public Flipper(){
		 encoder.setPIDSourceType(PIDSourceType.kRate);//not sure which type
		 pid.setAbsoluteTolerance(9000);//determine later
		 
		 this.set(new DataMap(){{
			 //idk yet
		 	}
		 });
		 
		this.set(new ElasticController(){{
			addDefault("Stow", new Action(){
				public void run(ActionData data){
					
				}
				
			});
			
			add("Intaking", new Action(){
				
			});
			
			add("Firing", new Action(){
				
			});
			
		}});
		
	}
	
}
