package com._604robotics.robot2016.modules;

import com._604robotics.robot2016.constants.Calibration;
import com._604robotics.robot2016.constants.Ports;
import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.ActionData;
import com._604robotics.robotnik.action.controllers.StateController;
import com._604robotics.robotnik.module.Module;
import com._604robotics.robotnik.trigger.TriggerMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Clamp extends Module {
	private final Solenoid solenoid = new Solenoid(Ports.CLAMP);
	
	public Clamp () {	
		this.set(new StateController() {{
			final Timer stowTimer = new Timer();
			final Timer downTimer = new Timer();
			set(new TriggerMap() {{
				add("Clamp Closed", () -> stowTimer.get() > Calibration.CLAMP_MOVE_TIME);
				add("Clamp Opened", () -> downTimer.get() > Calibration.CLAMP_MOVE_TIME);
			}});
			addDefault("Close", new Action() {
				public void begin(ActionData data) {
					stowTimer.start();
					solenoid.set(Calibration.CLAMP_STOW);
				}
				public void end(ActionData data) {
					stowTimer.stop();
					stowTimer.reset();
				}
			});
			add("Open", new Action(){
				public void begin (ActionData data){
					downTimer.start();
					solenoid.set(Calibration.CLAMP_DEPLOY);
				}
				public void end(ActionData data) {
					downTimer.stop();
					downTimer.reset();
				}
			});
		}});
	}

}
