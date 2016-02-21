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
    	
    	/* Drive */
    	{
	    	this.fill(new DataWire(DashboardOutput.asDouble(), "Left Drive Clicks",
	    			modules.getModule("Drive").getData("Left Drive Clicks")));
	    	this.fill(new DataWire(DashboardOutput.asDouble(), "Right Drive Clicks",
	    			modules.getModule("Drive").getData("Right Drive Clicks")));
	    	this.fill(new DataWire(DashboardOutput.asDouble(), "Left Drive Rate",
	    			modules.getModule("Drive").getData("Left Drive Rate")));
	    	this.fill(new DataWire(DashboardOutput.asDouble(), "Right Drive Rate",
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
    	
    	/* Pickup */
    	{
    	    this.fill(new DataWire(DashboardOutput.asDouble(), "Pickup Angle", modules.getModule("Pickup").getData("Pickup Angle")));

    		this.fill(new DataWire(modules.getModule("Pickup").getAction("Down"), "Setpoint", modules.getModule("Dashboard").getData("Pickup Down Angle")));
    		this.fill(new DataWire(modules.getModule("Pickup").getAction("Mid"), "Setpoint", modules.getModule("Dashboard").getData("Pickup Mid Angle")));
    		this.fill(new DataWire(modules.getModule("Pickup").getAction("Up"), "Setpoint", modules.getModule("Dashboard").getData("Pickup Up Angle")));
    	}
    }
}
