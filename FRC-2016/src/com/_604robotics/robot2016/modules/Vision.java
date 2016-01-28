package com._604robotics.robot2016.modules;

import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.ActionData;
import com._604robotics.robotnik.action.controllers.ElasticController;
/*import com._604robotics.robotnik.coordinator.connectors.DataWire;
import com._604robotics.robotnik.data.DataMap;
import com._604robotics.robotnik.data.sources.NetworkData;
import com._604robotics.robotnik.data.sources.NetworkDataArray;*/
import com._604robotics.robotnik.module.Module;
import com._604robotics.robotnik.trigger.Trigger;
import com._604robotics.robotnik.trigger.TriggerMap;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class Vision extends Module
{
    private boolean ready=false;
    
    NetworkTable GRIPtableH;
    NetworkTable GRIPtableV;

    public Vision()
    {
        GRIPtableH=NetworkTable.getTable("GRIP/HorizontalGoal");
        GRIPtableV=NetworkTable.getTable("GRIP/VerticalGoal");

        this.set(new TriggerMap()
        {{
            add("On Target", new Trigger()
            {
                public boolean run()
                {
                    return ready;
                };
            });
        }});
        this.set(new ElasticController()
        {{
            addDefault("VisionProcess", new Action()
            {
                public void begin(ActionData data)
                {
                    ready=false;
                }
                public void run(ActionData data)
                {
                    
                };
                public void end(ActionData data)
                {
                    
                };
            });
        }});
    }

}
