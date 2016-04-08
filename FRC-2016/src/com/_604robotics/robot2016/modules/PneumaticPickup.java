package com._604robotics.robot2016.modules;

import com._604robotics.robot2016.constants.Calibration;
import com._604robotics.robot2016.constants.Ports;
import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.ActionData;
import com._604robotics.robotnik.action.controllers.StateController;
import com._604robotics.robotnik.module.Module;
import com._604robotics.robotnik.trigger.TriggerMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PneumaticPickup extends Module {
	private final DoubleSolenoid right = new DoubleSolenoid(Ports.PNEUMATIC_PICKUP_RIGHT_A,
	        Ports.PNEUMATIC_PICKUP_RIGHT_B);
	private final DoubleSolenoid left = new DoubleSolenoid(Ports.PNEUMATIC_PICKUP_LEFT_A, 
	        Ports.PNEUMATIC_PICKUP_LEFT_B);
	
	public PneumaticPickup () {	
		this.set(new StateController() {{
			final Timer stowTimer = new Timer();
			final Timer downTimer = new Timer();
			set(new TriggerMap() {{
				add("Is Stowed", () -> stowTimer.get() > Calibration.PICKUP_MOVE_TIME);
				add("Is Deployed", () -> downTimer.get() > Calibration.PICKUP_MOVE_TIME);
			}});
			addDefault("Stow", new Action() {
				public void begin(ActionData data) {
					stowTimer.start();
					right.set(Calibration.PICKUP_STOW);
					left.set(Calibration.PICKUP_STOW);
				}
				public void end(ActionData data) {
					stowTimer.stop();
					stowTimer.reset();
				}
			});
			add("Down", new Action(){
				public void begin (ActionData data){
					downTimer.start();
					right.set(Calibration.PICKUP_DEPLOY);
					left.set(Calibration.PICKUP_DEPLOY);
				}
				public void end(ActionData data) {
					downTimer.stop();
					downTimer.reset();
				}
			});
		}});
	}

}
