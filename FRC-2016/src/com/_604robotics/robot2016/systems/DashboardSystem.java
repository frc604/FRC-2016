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
    		this.bind(new Binding(robot.drive.getAction("Off"), robot.dashboard.getTrigger("Drive Off"), true));
        }
    	
    	/* Drive */
    	{
	    	this.fill(new DataWire(DashboardOutput.asDouble(), "Left Drive Clicks",
	    			robot.drive.getData("Left Drive Clicks")));
	    	this.fill(new DataWire(DashboardOutput.asDouble(), "Right Drive Clicks",
	    			robot.drive.getData("Right Drive Clicks")));
	    	this.fill(new DataWire(DashboardOutput.asDouble(), "Left Drive Rate",
	    			robot.drive.getData("Left Drive Rate")));
	    	this.fill(new DataWire(DashboardOutput.asDouble(), "Right Drive Rate",
	    			robot.drive.getData("Right Drive Rate")));
    	}
    	
    	/* Shifting */
    	{
        	this.fill(new DataWire(DashboardOutput.asBoolean(), "Shifter Gear",
        	        robot.shifter.getAction("High Gear").active()));
        	this.fill(new DataWire(DashboardOutput.asDouble(), "Software Gear",
        			robot.gear.getData("Gear")));
    	}
    	
    	/* Shooter */
    	{
    		this.fill(new DataWire(DashboardOutput.asDouble(), "Current Shooter Speed", robot.shooter.getData("Current Speed")));
    		this.fill(new DataWire(DashboardOutput.asBoolean(), "Shooter Charged", robot.shooter.getTrigger("Charged")));

    		this.fill(new DataWire(robot.shooter.getAction("Shoot"), "Target Speed", robot.dashboard.getData("Shooter Target Speed")));
    		this.fill(new DataWire(robot.shooter.getAction("Shoot"), "Threshold", robot.dashboard.getData("Shooter Threshold")));
    	}
    	
    	/* Pickup */
    	{
    	    this.fill(new DataWire(DashboardOutput.asDouble(), "Pickup Angle", robot.pickup.getData("Pickup Angle")));

    		this.fill(new DataWire(robot.pickup.getAction("Down"), "Setpoint", robot.dashboard.getData("Pickup Down Angle")));
    		this.fill(new DataWire(robot.pickup.getAction("Mid"), "Setpoint", robot.dashboard.getData("Pickup Mid Angle")));
    		this.fill(new DataWire(robot.pickup.getAction("Up"), "Setpoint", robot.dashboard.getData("Pickup Up Angle")));
    	}
    }
}
