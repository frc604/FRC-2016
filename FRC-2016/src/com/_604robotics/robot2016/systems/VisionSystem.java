package com._604robotics.robot2016.systems;

import com._604robotics.robotnik.coordinator.Coordinator;
import com._604robotics.robotnik.coordinator.connectors.DataWire;
import com._604robotics.robotnik.module.ModuleManager;

public class VisionSystem extends Coordinator
{
    protected void apply(ModuleManager modules)
    {
        this.fill(new DataWire(modules.getModule("VisionProcessing").getAction("RunVision"),"Left_ang",
                modules.getModule("Vision").getData("GRIP_Vert_len")));
        this.fill(new DataWire(modules.getModule("VisionProcessing").getAction("RunVision"),"Left_ang_index",
                modules.getModule("Vision").getData("GRIP_Vert_len")));
    }
}
