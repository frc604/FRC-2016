package com._604robotics.robot2016.modules;

import com._604robotics.robot2016.constants.Calibration;
import com._604robotics.robot2016.constants.Ports;
import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.ActionData;
import com._604robotics.robotnik.action.controllers.ElasticController;
import com._604robotics.robotnik.module.Module;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class SafeToggle extends Module {

	public static double THROTTLE = 1.0;
    public SafeToggle() {
        this.set(new ElasticController() {{
            addDefault("Safe Mode Off", new Action() {
                @Override
                public void begin (ActionData data) {
                    THROTTLE = Calibration.SAFE_MODE_OFF;
                }
            });

            add("Safe Mode On", new Action() {
                @Override
                public void begin (ActionData data) {
                    THROTTLE = Calibration.SAFE_MODE_ON;
                }
            });
        }});
    }
}
