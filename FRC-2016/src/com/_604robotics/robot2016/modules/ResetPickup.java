package com._604robotics.robot2016.modules;

import com._604robotics.robot2016.constants.Ports;
import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.ActionData;
import com._604robotics.robotnik.action.controllers.ElasticController;
import com._604robotics.robotnik.module.Module;
import com._604robotics.robotnik.prefabs.devices.MA3A10;

import edu.wpi.first.wpilibj.Timer;

public class ResetPickup extends Module
{
    private final MA3A10 encoder = new MA3A10(Ports.PICKUP_ENCODER);
    
    private Timer countTimer=new Timer();
    
    public ResetPickup()
    {
        set(new ElasticController()
        {{
            add("ResetEncoder", new Action() {
                public void begin(ActionData data) {
                    countTimer.reset();
                    countTimer.start();
                }
                public void run(ActionData data) {
                    if (countTimer.get()>1.5) {
                        encoder.setZeroAngle(encoder.getAngle());
                    }
                }
                public void end(ActionData data) {
                    countTimer.reset();
                    countTimer.stop();
                }
            });
        }});
    }
}
