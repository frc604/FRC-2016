package com._604robotics.robot2016.modules;

import com._604robotics.robotnik.module.Module;
import com._604robotics.robotnik.trigger.Trigger;
import com._604robotics.robotnik.trigger.TriggerMap;
import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.ActionData;
import com._604robotics.robotnik.action.controllers.ElasticController;
import com._604robotics.robotnik.action.field.FieldMap;

public class VisionProcessing extends Module
{
    private boolean ready;    
    public VisionProcessing()
    {
        this.set(new ElasticController()
        {{
            addDefault("RunVision", new Action(new FieldMap()
            {{
                define("Left_ang", 0D);
                define("Left_x1", 0D);
                define("Left_x2", 0D);
            }})
            {
                public void run(ActionData data)
                {
                    
                }
            });
        }});
        this.set(new TriggerMap()
        {{
            add("Ready2", new Trigger()
            {
                public boolean run()
                {
                    return ready;
                }
            });
        }});
    }
}
