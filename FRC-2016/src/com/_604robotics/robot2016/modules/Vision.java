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
import com._604robotics.utils.*;

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
            BoolFIFOPopQueue readystack=new BoolFIFOPopQueue(10,0.7);
            addDefault("VisionProcess", new Action()
            {
                public void begin(ActionData data)
                {
                    ready=false;
                }
                public void run(ActionData data)
                {
                    double[] GRIPV_x1=GRIPtableV.getNumberArray("x1", new double[0]);
                    double[] GRIPV_x2=GRIPtableV.getNumberArray("x2", new double[0]);
                    double[] Vx1Diff=new double[GRIPV_x1.length-1];
                    double[] Vx2Diff=new double[GRIPV_x2.length-1];
                    //for now, strict array size requirements
                    if (GRIPV_x1.length!=4 || GRIPV_x2.length!=4)
                    {
                        ready=false;
                    }
                    else
                    {
                        for(int i=0; i<Vx1Diff.length; i++)
                        {
                            Vx1Diff[i]=GRIPV_x1[i++]-GRIPV_x1[i];
                        }
                        for(int i=0; i<Vx2Diff.length; i++)
                        {
                            Vx2Diff[i]=GRIPV_x2[i++]-GRIPV_x2[i];
                        }
                        
                    }
                };
                public void end(ActionData data)
                {
                    ready=false;
                };
            });
        }});
    }

}
