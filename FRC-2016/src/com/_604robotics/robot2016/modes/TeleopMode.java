package com._604robotics.robot2016.modes;

import com._604robotics.robotnik.coordinator.Coordinator;
import com._604robotics.robotnik.coordinator.connectors.Binding;
import com._604robotics.robotnik.coordinator.connectors.DataWire;
import com._604robotics.robotnik.module.ModuleManager;
import com._604robotics.robotnik.prefabs.controller.xbox.XboxController;
import com._604robotics.robotnik.prefabs.trigger.TriggerAnd;
import com._604robotics.robotnik.prefabs.trigger.TriggerNot;
import com._604robotics.robotnik.prefabs.trigger.TriggerOr;
import com._604robotics.robotnik.prefabs.trigger.TriggerToggle;
import com._604robotics.robotnik.trigger.TriggerAccess;

public class TeleopMode extends Coordinator {
    private final XboxController driver = new XboxController(0);
    private final XboxController manipulator = new XboxController(1);
    
    public TeleopMode () {
        double deadband = 0.2;
        double factor = -1;
        
        driver.leftStick.X.setDeadband(deadband);
        driver.leftStick.Y.setDeadband(deadband);
        
        driver.leftStick.X.setFactor(factor);
        driver.leftStick.Y.setFactor(factor);
        
        driver.rightStick.X.setDeadband(deadband);
        driver.rightStick.Y.setDeadband(deadband);
        
        driver.rightStick.X.setFactor(factor);
        driver.rightStick.Y.setFactor(factor);
    }
    
    protected void apply (ModuleManager modules) {
    	/* Driving */
    	{
    		this.bind(new Binding(modules.getModule("Drive").getAction("Off"), new TriggerAnd(new TriggerAccess[] {
            			modules.getModule("Dashboard").getTrigger("Drive Off")})));
    		
    	    /* Tank Drive */
    		{	
	    		this.bind(new Binding(modules.getModule("Drive").getAction("Tank Drive"), new TriggerAnd(new TriggerAccess[] {
	            		modules.getModule("Dashboard").getTrigger("Drive On"),
	            		modules.getModule("Dashboard").getTrigger("Tank Drive")})));
	            this.fill(new DataWire(modules.getModule("Drive").getAction("Tank Drive"), "left", driver.leftStick.Y));
	            this.fill(new DataWire(modules.getModule("Drive").getAction("Tank Drive"), "right", driver.rightStick.Y));
    		}
    		
    		/* Geared Drive */
    		{
        		this.bind(new Binding(modules.getModule("Drive").getAction("Geared Drive"), new TriggerAnd(new TriggerAccess[] {
                		modules.getModule("Dashboard").getTrigger("Drive On"),
                		modules.getModule("Dashboard").getTrigger("Geared Drive")})));
                this.fill(new DataWire(modules.getModule("Drive").getAction("Geared Drive"), "left", driver.leftStick.Y));
                this.fill(new DataWire(modules.getModule("Drive").getAction("Geared Drive"), "right", driver.rightStick.Y));

                this.fill(new DataWire(modules.getModule("Drive").getAction("Geared Drive"), "Left Low Gear", driver.buttons.LT));
                this.fill(new DataWire(modules.getModule("Drive").getAction("Geared Drive"), "Left High Gear", driver.buttons.LB));
                this.fill(new DataWire(modules.getModule("Drive").getAction("Geared Drive"), "Right Low Gear", driver.buttons.LT));
                this.fill(new DataWire(modules.getModule("Drive").getAction("Geared Drive"), "Right High Gear", driver.buttons.LB));
        	}
        	
            /* Shifter */
    		{
                final TriggerToggle shift = new TriggerToggle(driver.buttons.RB, false);
                this.bind(new Binding(modules.getModule("Shifter").getAction("Low Gear"), shift.off));
                this.bind(new Binding(modules.getModule("Shifter").getAction("High Gear"), shift.on));
    		}
    	}
    	
    	/* Manipulating */
    	{
        	/* Shooter */
        	{
        		this.bind(new Binding(modules.getModule("Shooter").getAction("Shoot"), manipulator.buttons.RT));
        	}
        	// Intake
        	{
        		this.bind(new Binding(modules.getModule("Intake").getAction("Run")));
        		this.fill(new DataWire(modules.getModule("Intake").getAction("Run"), "power", manipulator.rightStick.Y));
        	}
        	// Flipper
        	{
        		this.bind(new Binding(modules.getModule("Flipper").getAction("Flip Up")));
        		this.fill(new DataWire(modules.getModule("Flipper").getAction("Flip Up"), "on", manipulator.buttons.Y));
        		this.bind(new Binding(modules.getModule("Flipper").getAction("Flip Down")));
        		this.fill(new DataWire(modules.getModule("Flipper").getAction("Flip Down"), "on", manipulator.buttons.X));
        		this.bind(new Binding(modules.getModule("Flipper").getAction("Find Mid")));
        		this.fill(new DataWire(modules.getModule("Flipper").getAction("Find Mid"), "on", manipulator.buttons.B));
        	}
    	}
    }
}
