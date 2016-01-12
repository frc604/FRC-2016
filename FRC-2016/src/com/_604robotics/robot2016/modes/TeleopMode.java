package com._604robotics.robot2016.modes;

import com._604robotics.robotnik.coordinator.Coordinator;
import com._604robotics.robotnik.module.ModuleManager;
import com._604robotics.robotnik.prefabs.controller.xbox.XboxController;

public class TeleopMode extends Coordinator
{
    private final XboxController      driver = new XboxController(0);
    private final XboxController manipulator = new XboxController(2);
    
    public TeleopMode()
    {
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
    protected void apply(ModuleManager modules)
    {
        
    }
}
