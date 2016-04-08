package com._604robotics.robot2016.modules;

import com._604robotics.robot2016.constants.Calibration;
import com._604robotics.robot2016.constants.Ports;
import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.ActionData;
import com._604robotics.robotnik.action.controllers.StateController;
import com._604robotics.robotnik.module.Module;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class PneumaticPickup extends Module {
	private final DoubleSolenoid right = new DoubleSolenoid(Ports.PNEUMATIC_PICKUP_RIGHT_A,
	        Ports.PNEUMATIC_PICKUP_RIGHT_B);
	private final DoubleSolenoid left = new DoubleSolenoid(Ports.PNEUMATIC_PICKUP_LEFT_A, 
	        Ports.PNEUMATIC_PICKUP_LEFT_B);

	public PneumaticPickup () {
		this.set(new StateController() {{
			addDefault("Stow", new Action() {
				public void begin(ActionData data) {
					right.set(Calibration.PICKUP_STOW);
					left.set(Calibration.PICKUP_STOW);
				}
			});
			add("Down", new Action(){
				public void begin (ActionData data){
					right.set(Calibration.PICKUP_DEPLOY);
					left.set(Calibration.PICKUP_DEPLOY);
				}
			});
		}});
	}

}
