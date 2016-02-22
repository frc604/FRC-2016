package com._604robotics.robot2016.systems;

import com._604robotics.robot2016.Robot2016;
import com._604robotics.robotnik.coordinator.Coordinator;

public class GearSystem extends Coordinator {
    public GearSystem (Robot2016 robot) {
    	fill(robot.drive.getAction("Tank Drive"), "Throttle", robot.gear.getData("Current Multiplier"));
    }
}
