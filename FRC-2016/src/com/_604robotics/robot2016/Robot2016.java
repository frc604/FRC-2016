package com._604robotics.robot2016;

import java.io.IOException;

import com._604robotics.robot2016.systems.DashboardSystem;
import com._604robotics.robot2016.systems.GearSystem;
import com._604robotics.robot2016.systems.VisionSystem;
import com._604robotics.robot2016.modes.AutonomousMode;
import com._604robotics.robot2016.modes.TeleopMode;
import com._604robotics.robot2016.modules.Drive;
import com._604robotics.robot2016.modules.Dashboard;
import com._604robotics.robot2016.modules.Vision;
import com._604robotics.robot2016.modules.VisionProcessing;
import com._604robotics.robot2016.modules.Gear;
import com._604robotics.robotnik.Robot;
import com._604robotics.robotnik.coordinator.CoordinatorList;
import com._604robotics.robotnik.procedure.ModeMap;
import com._604robotics.robotnik.module.ModuleMap;

public class Robot2016 extends Robot
{
    
    public Robot2016()
    {
        //Initialize GRIP Instance
        try
        {
            Process GRIPProcess = Runtime.getRuntime().exec(new String[]{"/usr/local/frc/JRE/bin/java", "-jar", "grip.jar", "project.grip"});
            Thread closeGRIP = new Thread()
            {
                public void run()
                {
                    GRIPProcess.destroy();
                }
            };
            Runtime.getRuntime().addShutdownHook(closeGRIP);
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Initialize GRIP");//DEBUGPRINT
        System.out.println("Initialize Modes");//DEBUGPRINT
        this.set(new ModeMap()
        {{
            setAutonomousMode(new AutonomousMode());
            setTeleopMode(new TeleopMode());
        }});
        System.out.println("Initialize Modules");//DEBUGPRINT
        this.set(new ModuleMap()
   		{{
            add("Drive", new Drive());
            add("Dashboard", new Dashboard());
            add("Vision", new Vision());
            add("VisionProcessing", new VisionProcessing());
   			add("Gear", new Gear());
        }});
        System.out.println("Initialize CoordnatorList");//DEBUGPRINT
        this.set(new CoordinatorList() {{
            add(new DashboardSystem());
            add(new GearSystem());
            add(new VisionSystem());
        }});
    }
    
}
