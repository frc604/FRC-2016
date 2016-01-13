package com._604robotics.robot2016.modules;

import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.ActionData;
import com._604robotics.robotnik.action.controllers.ElasticController;
import com._604robotics.robotnik.data.DataMap;
import com._604robotics.robotnik.data.sources.NetworkData;
import com._604robotics.robotnik.module.Module;
import com._604robotics.robotnik.trigger.Trigger;
import com._604robotics.robotnik.trigger.TriggerMap;

public class Vision extends Module
{
    private boolean ready=false;
    
    public Vision()
    {
        
        this.set(new DataMap()
        {{
            /*Not sure where to put this
            NetworkTable GRIPtable;
            GRIPtable=NetworkTable.getTable("GRIP/myTestingReport");
            */
            //Alternately, use the Framework's NetworkData
            add("GRIP", new NetworkData("GRIP.myLinesReport","center",0));
        }});
        this.set(new TriggerMap()
        {{
            add("Ready", new Trigger()
            {
                public boolean run()
                {
                    return ready;
                }
            });
        }});
        this.set(new ElasticController()
        {{
            addDefault("Monitor", new Action()
            {
                //Process the NetworkData here
                public void run(ActionData data)
                {
                    
                }
            });
        }});
    }

}
