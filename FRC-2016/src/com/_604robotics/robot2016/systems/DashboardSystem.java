package com._604robotics.robot2016.systems;

import com._604robotics.robotnik.coordinator.Coordinator;
import com._604robotics.robotnik.coordinator.connectors.Binding;
import com._604robotics.robotnik.coordinator.connectors.DataWire;
import com._604robotics.robotnik.module.ModuleManager;
import com._604robotics.robotnik.prefabs.outputs.DashboardOutput;

public class DashboardSystem extends Coordinator {
    protected void apply (ModuleManager modules) {
        /* Disable Drive via Dashboard */
        {
    		this.bind(new Binding(modules.getModule("Drive").getAction("Off"), modules.getModule("Dashboard").getTrigger("Drive Off"), true));
        }
    		
      	/* Servo Drive Testing and Macros */
    	{
    		this.fill(new DataWire(modules.getModule("Drive").getAction("Servo Drive"),
    				"left clicks", modules.getModule("Dashboard").getData("Left Drive Servo")));
    		this.fill(new DataWire(modules.getModule("Drive").getAction("Servo Drive"),
    				"right clicks", modules.getModule("Dashboard").getData("Right Drive Servo")));
    	}
    	
    	/* Drive */
    	{
	    	this.fill(new DataWire(DashboardOutput.asDouble(), "left clicks",
	    			modules.getModule("Drive").getData("Left Drive Clicks")));
	    	this.fill(new DataWire(DashboardOutput.asDouble(), "right clicks",
	    			modules.getModule("Drive").getData("Right Drive Clicks")));
	    	this.fill(new DataWire(DashboardOutput.asDouble(), "left rate",
	    			modules.getModule("Drive").getData("Left Drive Rate")));
	    	this.fill(new DataWire(DashboardOutput.asDouble(), "right rate",
	    			modules.getModule("Drive").getData("Right Drive Rate")));
    	}
    	
    	/* Shifting */
    	{
        	this.fill(new DataWire(DashboardOutput.asBoolean(), "Shifter Gear",
        	        modules.getModule("Shifter").getAction("High Gear").active()));
        	this.fill(new DataWire(DashboardOutput.asDouble(), "Software Gear",
        			modules.getModule("Gear").getData("Gear")));
    	}
    	
    	/* Shooter */
    	{
    		this.fill(new DataWire(DashboardOutput.asDouble(), "Current Shooter Speed", modules.getModule("Shooter").getData("Current Speed")));
    		this.fill(new DataWire(DashboardOutput.asBoolean(), "Shooter Charged", modules.getModule("Shooter").getTrigger("Charged")));

    		this.fill(new DataWire(modules.getModule("Shooter").getAction("Shoot"), "Target Speed", modules.getModule("Dashboard").getData("Shooter Target Speed")));
    		this.fill(new DataWire(modules.getModule("Shooter").getAction("Shoot"), "Threshold", modules.getModule("Dashboard").getData("Shooter Threshold")));
    	}
    	
    	/* Intake */
    	{
    	    this.fill(new DataWire(DashboardOutput.asDouble(), "Pickup Clicks",
    	            modules.getModule("Pickup").getData("Pickup Clicks")));
    	}
    }
}
