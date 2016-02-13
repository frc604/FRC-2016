package com._604robotics.robot2016.modules;

import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.ActionData;
import com._604robotics.robotnik.action.controllers.ElasticController;
import com._604robotics.robotnik.module.Module;
import edu.wpi.first.wpilibj.Victor;

public class Intake extends Module{
	private final Victor motor = new Victor(5);//port unknown as of right now, prob need to change this

	public Intake (){
		this.set(new ElasticController(){{
			addDefault("Off", new Action(){
                @Override
				public void run(ActionData data){
					motor.stopMotor();
				}
			});
			
			add("SuckIn", new Action(){
                @Override
				public void run(ActionData data){
					motor.set(-1D);
				}
                @Override
				public void end(ActionData data){
					motor.stopMotor();
				}
			});
			
			add("SpitOut", new Action(){
                @Override
				public void run(ActionData data){
					motor.set(1D);
				}
                @Override
				public void end(ActionData data){
					motor.stopMotor();
				}
			});
			
		}});
	}
}
