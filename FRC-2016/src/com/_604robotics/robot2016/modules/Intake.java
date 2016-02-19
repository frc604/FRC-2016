package com._604robotics.robot2016.modules;

import com._604robotics.robot2016.Ports;
import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.ActionData;
import com._604robotics.robotnik.action.controllers.ElasticController;
import com._604robotics.robotnik.module.Module;
import edu.wpi.first.wpilibj.Victor;
import com._604robotics.robotnik.action.field.FieldMap;

public class Intake extends Module{
	private final Victor motor = new Victor(Ports.INTAKE_MOTOR);//port unknown as of right now, prob need to change this

	public Intake (){
		this.set(new ElasticController(){{
			addDefault("Off", new Action(){
				public void run(ActionData data){
					motor.stopMotor();
				}
			});
			
			add("SuckIn", new Action(){
				public void run(ActionData data){
					motor.set(-1D);
				}
				public void end(ActionData data){
					motor.stopMotor();
				}
			});
			
			add("SpitOut", new Action(){
				public void run(ActionData data){
					motor.set(1D);
				}
				public void end(ActionData data){
					motor.stopMotor();
				}
			});
			
            add("Run", new Action(new FieldMap() {{
            	define("power", 0D);
            }}) {
            	public void run(ActionData data) {
                    motor.set(data.get("power"));
                }
                public void end(ActionData data){
                    motor.stopMotor();
                } 
            });
		}});
	}
}
