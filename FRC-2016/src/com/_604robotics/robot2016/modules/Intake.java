package com._604robotics.robot2016.modules;

import com._604robotics.robot2016.constants.Ports;
import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.ActionData;
import com._604robotics.robotnik.action.controllers.ElasticController;
import com._604robotics.robotnik.module.Module;
import edu.wpi.first.wpilibj.Victor;
import com._604robotics.robotnik.action.field.FieldMap;

public class Intake extends Module{
	private final Victor motor = new Victor(Ports.INTAKE_MOTOR);

	public Intake (){
		this.set(new ElasticController() {{
            addDefault("Run", new Action(new FieldMap() {{
            	define("Power", 0);
            }}) {
            	public void run (ActionData data) {
                    motor.set(data.get("Power"));
                }

                public void end (ActionData data){
                    motor.stopMotor();
                } 
            });
		}});
	}
}
