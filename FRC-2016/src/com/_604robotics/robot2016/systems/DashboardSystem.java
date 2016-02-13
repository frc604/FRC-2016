package com._604robotics.robot2016.systems;

import com._604robotics.robot2016.Robot2016;
import com._604robotics.robotnik.coordinator.Coordinator;
import com._604robotics.robotnik.coordinator.connectors.DataWire;
import com._604robotics.robotnik.module.ModuleManager;
import com._604robotics.robotnik.prefabs.outputs.DashboardOutput;

public class DashboardSystem extends Coordinator<Robot2016> {
    protected void apply (Robot2016 robot) {
        /* Drive servo testing and macro */
        {
            fill(robot.drive.getAction("Servo Drive"),
                    "left clicks", robot.dashboard.getData("Left Drive Servo"));
            fill(robot.drive.getAction("Servo Drive"),
                    "right clicks", robot.dashboard.getData("Right Drive Servo"));
        }

        /* Drive encoder output for debugging */
        {
            fill(DashboardOutput.asDouble(), "left clicks",  robot.drive.getData("Left Drive Clicks"));
            fill(DashboardOutput.asDouble(), "right clicks", robot.drive.getData("Right Drive Clicks"));
            fill(DashboardOutput.asDouble(), "left rate",    robot.drive.getData("Left Drive Rate"));
            fill(DashboardOutput.asDouble(), "right rate",   robot.drive.getData("Right Drive Rate"));
        }
        
        /* Shifter and Gear state display */
        {
            fill(DashboardOutput.asBoolean(), "Shifter Gear",  robot.shifter.getAction("High Gear").active());
            fill(DashboardOutput.asDouble(),  "Software Gear", robot.gear.getData("Gear"));
        }
    }
}
