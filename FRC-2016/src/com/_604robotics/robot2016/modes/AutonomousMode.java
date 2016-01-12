package com._604robotics.robot2016.modes;

import com._604robotics.robotnik.coordinator.Coordinator;
import com._604robotics.robotnik.module.ModuleManager;
import com._604robotics.robotnik.procedure.Procedure;

public class AutonomousMode extends Procedure
{
    public AutonomousMode()
    {
        super(new Coordinator()
            {
                protected void apply(ModuleManager modules)
                {
                    
                }
            });
    }
    protected void apply(ModuleManager modulues)
    {
        
    }
}
