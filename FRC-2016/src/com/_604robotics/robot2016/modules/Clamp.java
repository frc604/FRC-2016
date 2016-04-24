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
	private final Solenoid center = new Solenoid(Ports.CLAMP);
	
	public Clamp () {	
		this.set(new StateController() {{
			final Timer stowTimer = new Timer();
			final Timer downTimer = new Timer();
			set(new TriggerMap() {{
				add("Clamp Stowed", () -> stowTimer.get() > Calibration.CLAMP_MOVE_TIME);
				add("Clamp Deployed", () -> downTimer.get() > Calibration.CLAMP_MOVE_TIME);
			}});
			addDefault("Stow", new Action() {
				public void begin(ActionData data) {
					stowTimer.start();
					center.set(Calibration.CLAMP_STOW);
				}
				public void end(ActionData data) {
					stowTimer.stop();
					stowTimer.reset();
				}
			});
			add("Down", new Action(){
				public void begin (ActionData data){
					downTimer.start();
					center.set(Calibration.CLAMP_DEPLOY);
				}
				public void end(ActionData data) {
					downTimer.stop();
					downTimer.reset();
				}
			});
		}});
	}

}
