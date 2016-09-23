package com._604robotics.robot2016.modules;

import com._604robotics.robot2016.constants.Calibration;
import com._604robotics.robot2016.constants.Ports;
import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.ActionData;
import com._604robotics.robotnik.action.controllers.ElasticController;
import com._604robotics.robotnik.module.Module;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class DoubleClamp extends Module {
    private final DoubleSolenoid solenoid = new DoubleSolenoid(Ports.CLAMP_FORWARD_MAYBE, Ports.CLAMP_REVERSE_MAYBE);

    public DoubleClamp() {
        this.set(new ElasticController() {{
            addDefault("Close", new Action() {
                @Override
                public void begin (ActionData data) {
                    solenoid.set(Calibration.DOUBLE_CLAMP_CLOSE);
                }
            });

            add("Open", new Action() {
                @Override
                public void begin (ActionData data) {
                    solenoid.set(Calibration.DOUBLE_CLAMP_OPEN);
                }
            });
        }});
    }
}
