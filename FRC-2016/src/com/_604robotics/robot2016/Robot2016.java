package com._604robotics.robot2016;

import com._604robotics.robot2016.systems.DashboardSystem;
import com._604robotics.robot2016.systems.GearSystem;
import com._604robotics.robot2016.modes.AutonomousMode;
import com._604robotics.robot2016.modes.TeleopMode;
import com._604robotics.robot2016.modules.Drive;
import com._604robotics.robot2016.modules.Dashboard;
import com._604robotics.robot2016.modules.Vision;
import com._604robotics.robot2016.modules.Gear;
import com._604robotics.robot2016.modules.Shifter;
import com._604robotics.robot2016.modules.Shooter;
import com._604robotics.robot2016.modules.Intake;
import com._604robotics.robotnik.Robot;
import com._604robotics.robotnik.coordinator.CoordinatorList;
import com._604robotics.robotnik.coordinator.ModeMap;
import com._604robotics.robotnik.module.ModuleMap;
import com._604robotics.robotnik.logging.Logger;

import java.io.IOException;

public class Robot2016 extends Robot
{
    
    public Robot2016()
    {
        //Initialize GRIP Instance
        /*try
        {
            Process GRIPProcess = Runtime.getRuntime().exec(new String[]
                    {"/usr/local/frc/JRE/bin/java", "-jar", "grip.jar", "project.grip"});
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
        }*/
        boolean startGRIP=true;
        
        if (startGRIP)
        {
            Logger.log("Starting GRIP");
            try
            {
                new ProcessBuilder("/home/lvuser/grip").inheritIO().start();
                Logger.log("GRIP started successfully");
            }
            catch (IOException e)
            {
                Logger.error("Unable to Start GRIP", e);
                //No longer necessary since Logger.error prints the stack trace
                //e.printStackTrace();
            }
        }
        
        this.set(new ModeMap() {{
            setAutonomousMode(new AutonomousMode());
            setTeleopMode(new TeleopMode());
        }});
        
        this.set(new ModuleMap() {{
            add("Drive", new Drive());
            add("Dashboard", new Dashboard());
            add("Vision", new Vision());
   			add("Intake", new Intake());
            add("Gear", new Gear());
            add("Shifter", new Shifter());
            add("Shooter", new Shooter());
        }});
        
        this.set(new CoordinatorList() {{
            add(new DashboardSystem());
            add(new GearSystem());
        }});
    }
}
