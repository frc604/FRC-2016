package com._604robotics.robot2016.modes;

import com._604robotics.robot2016.Robot2016;
import com._604robotics.robotnik.coordinator.Coordinator;
import com._604robotics.robotnik.coordinator.connectors.Binding;
import com._604robotics.robotnik.coordinator.connectors.DataWire;
import com._604robotics.robotnik.module.ModuleManager;
import com._604robotics.robotnik.prefabs.controller.xbox.XboxController;
import com._604robotics.robotnik.prefabs.trigger.TriggerAnd;
import com._604robotics.robotnik.prefabs.trigger.TriggerToggle;
import com._604robotics.robotnik.trigger.TriggerAccess;

public class TeleopMode extends Coordinator<Robot2016> {
    private final XboxController      driver = new XboxController(0);
    private final XboxController manipulator = new XboxController(2);
    
    public TeleopMode () {
        final double deadband = 0.2;
        final double factor = -1D;
        
        driver.leftStick.X.setDeadband(deadband);
        driver.leftStick.Y.setDeadband(deadband);
        
        driver.leftStick.X.setFactor(factor);
        driver.leftStick.Y.setFactor(factor);
        
        driver.rightStick.X.setDeadband(deadband);
        driver.rightStick.Y.setDeadband(deadband);
        
        driver.rightStick.X.setFactor(factor);
        driver.rightStick.Y.setFactor(factor);
    }

    @Override
    protected void apply (Robot2016 robot) {
        /* Drive */
    	{
    		bind(robot.drive.getAction("Off"), robot.dashboard.getTrigger("Drive Off"));
    		
    		bind(robot.drive.getAction("Tank Drive"), new TriggerAnd(new TriggerAccess[] {
            		robot.dashboard.getTrigger("Drive On"),
            		robot.dashboard.getTrigger("Tank Drive")}));
            fill(robot.drive.getAction("Tank Drive"), "left",  driver.leftStick.Y);
            fill(robot.drive.getAction("Tank Drive"), "right", driver.rightStick.Y);

    		bind(robot.drive.getAction("Nicole Drive"), new TriggerAnd(new TriggerAccess[] {
            		robot.dashboard.getTrigger("Drive On"),
            		robot.dashboard.getTrigger("Nicole Drive")}));
            fill(robot.drive.getAction("Nicole Drive"), "throttle",   driver.leftStick.Y);
            fill(robot.drive.getAction("Nicole Drive"), "turn",       driver.rightStick.X);
            fill(robot.drive.getAction("Nicole Drive"), "accelerate", driver.buttons.A);
            fill(robot.drive.getAction("Nicole Drive"), "back",       driver.buttons.B);
    	}
    	
    	/* Shifter */
    	{
            final TriggerToggle shift=new TriggerToggle(driver.buttons.RB, false);
            bind(robot.shifter.getAction("Low Gear"),  shift.off);
            bind(robot.shifter.getAction("High Gear"), shift.on); 
    	}
    }
}
