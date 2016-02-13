package com._604robotics.robot2016.modules;

import com._604robotics.robot2016.Ports;
import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.ActionData;
import com._604robotics.robotnik.action.controllers.ElasticController;
import com._604robotics.robotnik.action.field.FieldMap;
import com._604robotics.robotnik.module.Module;
import com._604robotics.robotnik.prefabs.devices.MultiOutput;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Victor;

public class Shooter extends Module {
	private final MultiOutput motors = new MultiOutput(new PIDOutput[] { new Victor(Ports.SHOOTER_MOTOR_LEFT), new Victor(Ports.SHOOTER_MOTOR_RIGHT) });
	private final Encoder encoder = new Encoder(Ports.SHOOTER_ENCODER_A, Ports.SHOOTER_ENCODER_B);
	
	public Shooter () {
		this.set(new ElasticController() {{
			addDefault("Off", new Action() {
				public void run(ActionData data){
					motors.stopMotor();
				}
			});
			
            add("Shoot", new Action(new FieldMap () {{
                define("Target Speed", 0);
            }}) {
				public void run(ActionData data) {
					double targetSpeed = data.get("Target Speed");
			        if (encoder.getRate() >= targetSpeed) {
			            motors.stopMotor();
			        } else {
			            motors.set(-1);
			        }
				}
				
				public void end(ActionData data) {
					motors.stopMotor();
				}
            });
		}});
	}
}
