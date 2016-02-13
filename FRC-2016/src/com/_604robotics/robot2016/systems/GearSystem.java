package com._604robotics.robot2016.systems;

import com._604robotics.robot2016.Robot2016;
import com._604robotics.robotnik.coordinator.Coordinator;
import com._604robotics.robotnik.coordinator.connectors.DataWire;

public class GearSystem extends Coordinator<Robot2016> {
    protected void apply (Robot2016 robot) {
    	this.fill(new DataWire(robot.drive.getAction("Tank Drive"), "Throttle", robot.gear.getData("Current Multiplier")));
    }
}
