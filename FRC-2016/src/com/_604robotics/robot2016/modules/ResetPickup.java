package com._604robotics.robot2016.modules;

import com._604robotics.robot2016.constants.Ports;
import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.ActionData;
import com._604robotics.robotnik.action.controllers.ElasticController;
import com._604robotics.robotnik.module.Module;
import com._604robotics.robotnik.prefabs.devices.MA3A10;

public class ResetPickup extends Module
{
    private final MA3A10 encoder = new MA3A10(Ports.PICKUP_ENCODER);
    
    public ResetPickup()
    {
        set(new ElasticController()
        {{
            add("ResetEncoder", new Action() {
                public void run(ActionData data) {
                    encoder.setZeroAngle(encoder.getAngle());
                }
            });
        }});
    }
}
