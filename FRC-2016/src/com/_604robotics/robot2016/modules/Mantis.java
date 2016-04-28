package com._604robotics.robot2016.modules;

import com._604robotics.robot2016.constants.Calibration;
import com._604robotics.robot2016.constants.Ports;
import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.ActionData;
import com._604robotics.robotnik.action.controllers.StateController;
import com._604robotics.robotnik.module.Module;
import com._604robotics.robotnik.trigger.TriggerMap;


import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;

public class Mantis extends Module {
	private final Solenoid solenoid = new Solenoid(Ports.MANTIS);
	
	public Mantis () {	
		this.set(new StateController() {{
			final Timer closeTimer = new Timer();
			final Timer openTimer = new Timer();
			set(new TriggerMap() {{
				add("Clamp Closed", () -> closeTimer.get() > Calibration.MANTIS_MOVE_TIME);
				add("Clamp Opened", () -> openTimer.get() > Calibration.MANTIS_MOVE_TIME);
			}});
			addDefault("Close", new Action() {
				public void begin(ActionData data) {
					closeTimer.start();
					solenoid.set(Calibration.MANTIS_CLOSE);
				}
				public void end(ActionData data) {
					closeTimer.stop();
					closeTimer.reset();
				}
			});
			add("Open", new Action(){
				public void begin (ActionData data){
					openTimer.start();
					solenoid.set(Calibration.MANTIS_OPEN);
				}
				public void end(ActionData data) {
					openTimer.stop();
					openTimer.reset();
				}
			});
		}});
	}

}
