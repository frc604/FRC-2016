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

public class TeleopMode extends Coordinator
{
    private final XboxController      driver = new XboxController(0);
    private final XboxController manipulator = new XboxController(2);
    
    public TeleopMode()
    {
        System.out.println("Initialize XBOX in Teleop");//DEBUGPRINT
        
        double deadband=0.2;
        double factor=-1D;
        
        driver.leftStick.X.setDeadband(deadband);
        driver.leftStick.Y.setDeadband(deadband);
        
        driver.leftStick.X.setFactor(factor);
        driver.leftStick.Y.setFactor(factor);
        
        driver.rightStick.X.setDeadband(deadband);
        driver.rightStick.Y.setDeadband(deadband);
        
        driver.rightStick.X.setFactor(factor);
        driver.rightStick.Y.setFactor(factor);
    }
    protected void apply (ModuleManager modules)
    {
    	{
    		System.out.println("Dashboard in Teleop");//DEBUGPRINT
    	    this.bind(new Binding(modules.getModule("Drive").getAction("Off"), new TriggerAnd(new TriggerAccess[] {
            		modules.getModule("Dashboard").getTrigger("Drive Off"),
            		modules.getModule("Dashboard").getTrigger("Debugging On")})));
    		
    		this.bind(new Binding(modules.getModule("Drive").getAction("Tank Drive"), new TriggerAnd(new TriggerAccess[] {
            		modules.getModule("Dashboard").getTrigger("Drive On"),
            		modules.getModule("Dashboard").getTrigger("Debugging On"),
            		modules.getModule("Dashboard").getTrigger("Tank Drive")})));
            this.fill(new DataWire(modules.getModule("Drive").getAction("Tank Drive"), "left",  driver.leftStick.Y));
            this.fill(new DataWire(modules.getModule("Drive").getAction("Tank Drive"), "right", driver.rightStick.Y));
            
    	}
    }
}
