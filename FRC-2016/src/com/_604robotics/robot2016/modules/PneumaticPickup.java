package com._604robotics.robot2016.modules;

import com._604robotics.robot2016.constants.Ports;
import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.ActionData;
import com._604robotics.robotnik.action.controllers.StateController;
import com._604robotics.robotnik.module.Module;
import com._604robotics.robotnik.trigger.Trigger;
import com._604robotics.robotnik.trigger.TriggerMap;

import edu.wpi.first.wpilibj.Solenoid;

public class PneumaticPickup extends Module {
	private final Solenoid right = new Solenoid(Ports.PNEUMATICPICKUP_RIGHT);
	private final Solenoid left = new Solenoid(Ports.PNEUMATICPICKUP_LEFT);

	public PneumaticPickup () {
		this.set(new StateController() {{
			addDefault("Stow", new Action() {
				public void begin(ActionData data) {
					right.set(false);
					left.set(false);
				}
			});
			add("Down", new Action(){
				public void begin (ActionData data){
					right.set(true);
					left.set(true);
				}
			});
		}});
	}

}
