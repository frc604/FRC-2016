package com._604robotics.robot2016.modules;

import com._604robotics.robotnik.data.DataMap;
import com._604robotics.robotnik.data.sources.DashboardData;
import com._604robotics.robotnik.module.Module;
import com._604robotics.robotnik.trigger.TriggerMap;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;;

public class Dashboard extends Module
{
    public Dashboard ()
    {
        this.set(new TriggerMap()
        {{
            
        }});
        this.set(new DataMap()
        {{
            add("Gear",new DashboardData("Gear", 0D));
        }});
    }
}
