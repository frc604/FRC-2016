package com._604robotics.robot2016.modes;

import com._604robotics.robotnik.coordinator.Coordinator;
import com._604robotics.robotnik.coordinator.connectors.Binding;
import com._604robotics.robotnik.coordinator.connectors.DataWire;
import com._604robotics.robotnik.module.ModuleManager;
import com._604robotics.robotnik.prefabs.controller.xbox.XboxController;
import com._604robotics.robotnik.prefabs.trigger.TriggerToggle;

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
        
        manipulator.rightStick.Y.setDeadband(deadband);
    }
    
    protected void apply (ModuleManager modules) {
    	/* Driving */
    	{
    	    /* Tank Drive */
    		{	
	    		this.bind(new Binding(modules.getModule("Drive").getAction("Tank Drive"), modules.getModule("Dashboard").getTrigger("Tank Drive")));
	            this.fill(new DataWire(modules.getModule("Drive").getAction("Tank Drive"), "left", driver.leftStick.Y));
	            this.fill(new DataWire(modules.getModule("Drive").getAction("Tank Drive"), "right", driver.rightStick.Y));
    		}
    		
    		/* Geared Drive */
    		{
        		this.bind(new Binding(modules.getModule("Drive").getAction("Geared Drive"), modules.getModule("Dashboard").getTrigger("Geared Drive")));
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
        	
        	/* Intake */
        	{
        		this.fill(new DataWire(modules.getModule("Intake").getAction("Run"), "Power", manipulator.rightStick.Y));
        	}
        	
        	/* Pickup */
        	{
        		this.bind(new Binding(modules.getModule("Pickup").getAction("Down"), manipulator.buttons.A));
        		this.bind(new Binding(modules.getModule("Pickup").getAction("Mid"), manipulator.buttons.X));
        		this.bind(new Binding(modules.getModule("Pickup").getAction("Up"), manipulator.buttons.Y));
        	}
    	}
    }
}
