package com._604robotics.robot2016.systems;

import com._604robotics.robot2016.Robot2016;
import com._604robotics.robotnik.coordinator.Coordinator;
import com._604robotics.robotnik.coordinator.connectors.Binding;
import com._604robotics.robotnik.coordinator.connectors.DataWire;
import com._604robotics.robotnik.prefabs.outputs.DashboardOutput;

public class DashboardSystem extends Coordinator<Robot2016> {
    protected void apply (Robot2016 robot) {
        /* Disable Drive via Dashboard */
        {
    		bind(robot.drive.getAction("Off"), robot.dashboard.getTrigger("Drive Off"), true);
        }
    	
    	/* Drive */
    	{
	    	fill(DashboardOutput.asDouble(), "Left Drive Clicks", robot.drive.getData("Left Drive Clicks"));
	    	fill(DashboardOutput.asDouble(), "Right Drive Clicks", robot.drive.getData("Right Drive Clicks"));
	    	fill(DashboardOutput.asDouble(), "Left Drive Rate", robot.drive.getData("Left Drive Rate"));
	    	fill(DashboardOutput.asDouble(), "Right Drive Rate", robot.drive.getData("Right Drive Rate"));
    	}
    	
    	/* Shifting */
    	{
        	fill(DashboardOutput.asBoolean(), "Shifter Gear", robot.shifter.getAction("High Gear").active());
        	fill(DashboardOutput.asDouble(), "Software Gear", robot.gear.getData("Gear"));
    	}
    	
    	/* Shooter */
    	{
    		fill(DashboardOutput.asDouble(), "Current Shooter Speed", robot.shooter.getData("Current Speed"));
    		fill(DashboardOutput.asBoolean(), "Shooter Charged", robot.shooter.getTrigger("Charged"));

    		fill(robot.shooter.getAction("Shoot"), "Target Speed", robot.dashboard.getData("Shooter Target Speed"));
    		fill(robot.shooter.getAction("Shoot"), "Threshold", robot.dashboard.getData("Shooter Threshold"));
    	}
    	
    	/* Pickup */
    	{
    	    fill(DashboardOutput.asDouble(), "Pickup Angle", robot.pickup.getData("Pickup Angle"));

    		fill(robot.pickup.getAction("Down"), "Setpoint", robot.dashboard.getData("Pickup Down Angle"));
    		fill(robot.pickup.getAction("Mid"), "Setpoint", robot.dashboard.getData("Pickup Mid Angle"));
    		fill(robot.pickup.getAction("Up"), "Setpoint", robot.dashboard.getData("Pickup Up Angle"));
    	}
    }
}
