package com._604robotics.robot2016;

import com._604robotics.robotnik.Robot;
import com._604robotics.robot2016.modes.*;
import com._604robotics.robot2016.modules.Dashboard;
import com._604robotics.robot2016.modules.Drive;
import com._604robotics.robot2016.modules.Gear;
import com._604robotics.robotnik.module.ModuleMap;
import com._604robotics.robotnik.procedure.ModeMap;

public class Robot2016 extends Robot
{
    public Robot2016()
    {
        this.set(new ModeMap()
        {{
            setAutonomousMode(new AutonomousMode());
            setTeleopMode(new TeleopMode());
        }});
        this.set(new ModuleMap()
        {{
            add("Dashboard", new Dashboard());
            add("Drive", new Drive());
            add("Gear", new Gear());
        }});
    }
    
}
