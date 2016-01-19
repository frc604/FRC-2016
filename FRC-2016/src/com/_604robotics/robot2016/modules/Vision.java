package com._604robotics.robot2016.modules;

import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.ActionData;
import com._604robotics.robotnik.action.controllers.ElasticController;
import com._604robotics.robotnik.coordinator.connectors.DataWire;
import com._604robotics.robotnik.data.DataMap;
import com._604robotics.robotnik.data.sources.NetworkData;
import com._604robotics.robotnik.data.sources.NetworkDataArray;
import com._604robotics.robotnik.module.Module;
import com._604robotics.robotnik.trigger.Trigger;
import com._604robotics.robotnik.trigger.TriggerMap;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

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
            double[] Default=new double[0];
            //while??
            //double[] x1_vert=GRIPtable.getNumberArray("x1", Default);
            //Do stuff*/
            //Alternately, use the Framework's NetworkData
            System.out.println("Vision.java");//DEBUGPRINT
            add("GRIP_Vert_len", new NetworkDataArray("GRIP.VerticalGoal","length",0D,0));
            add("GRIP_Vert_ang", new NetworkDataArray("GRIP.VerticalGoal","angle",0D,0));
            add("GRIP_Vert_x1", new NetworkDataArray("GRIP.VerticalGoal","x1",0D,0));
            add("GRIP_Vert_x2", new NetworkDataArray("GRIP.VerticalGoal","x2",0D,0));
            add("GRIP_Vert_y1", new NetworkDataArray("GRIP.VerticalGoal","y1",0D,0));
            add("GRIP_Vert_y2", new NetworkDataArray("GRIP.VerticalGoal","y2",0D,0));
            add("GRIP_Horiz_len", new NetworkDataArray("GRIP.HorizontalGoal","length",0D,0));
            add("GRIP_Horiz_ang", new NetworkDataArray("GRIP.HorizontalGoal","angle",0D,0));
            add("GRIP_Horiz_x1", new NetworkDataArray("GRIP.HorizontalGoal","x1",0D,0));
            add("GRIP_Horiz_x2", new NetworkDataArray("GRIP.HorizontalGoal","x2",0D,0));
            add("GRIP_Horiz_y1", new NetworkDataArray("GRIP.HorizontalGoal","y1",0D,0));
            add("GRIP_Horiz_y2", new NetworkDataArray("GRIP.HorizontalGoal","y2",0D,0));
            /*NetworkDataArray testing = new NetworkDataArray("test","y2",0,1);
            int length=testing.getlength();
            for (int i=0; i<length; i++)
            {
                
            }*/
        }});
    }

}
