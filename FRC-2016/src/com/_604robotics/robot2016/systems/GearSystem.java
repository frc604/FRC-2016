package com._604robotics.robot2016.systems;

import com._604robotics.robotnik.coordinator.Coordinator;
import com._604robotics.robotnik.coordinator.connectors.DataWire;
import com._604robotics.robotnik.module.ModuleManager;

public class GearSystem extends Coordinator {
    protected void apply (ModuleManager modules) {
    	this.fill(new DataWire(modules.getModule("Drive").getAction("Tank Drive"),
                "throttle", modules.getModule("Gear").getData("Current Multiplier")));
    }
}
