package com._604robotics.robot2016.modules;

import com._604robotics.robot2016.Ports;
import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.ActionData;
import com._604robotics.robotnik.action.controllers.ElasticController;
import com._604robotics.robotnik.module.Module;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Shifter extends Module {
    private final DoubleSolenoid solenoid = new DoubleSolenoid(Ports.SHIFTER_SOLENOID_FORWARD, Ports.SHIFTER_SOLENOID_REVERSE);

    public Shifter() {
        this.set(new ElasticController() {{
            addDefault("Low Gear", new Action() {
                public void begin (ActionData data) {
                    solenoid.set(Value.kReverse);
                }
            });
            
            add("High Gear", new Action() {
                public void begin (ActionData data) {
                    solenoid.set(Value.kForward);
                }
            });
        }});
    }
}
