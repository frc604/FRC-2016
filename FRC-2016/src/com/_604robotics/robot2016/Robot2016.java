package com._604robotics.robot2016;

import com._604robotics.robot2016.systems.DashboardSystem;
import com._604robotics.robot2016.systems.GearSystem;
import com._604robotics.robot2016.modes.AutonomousMode;
import com._604robotics.robot2016.modes.TeleopMode;
import com._604robotics.robot2016.modules.Drive;
import com._604robotics.robot2016.modules.Dashboard;
import com._604robotics.robotnik.Robot;
import com._604robotics.robotnik.coordinator.CoordinatorList;
import com._604robotics.robotnik.procedure.ModeMap;
import com._604robotics.robotnik.module.ModuleMap;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot2016 extends Robot
{
    NetworkTable GRIPTableV;
    NetworkTable GRIPTableH;
    
    public Robot2016()
    {
        //Initialize GRIP data here??
        GRIPTableV=NetworkTable.getTable("GRIP/VerticalGoal");
        GRIPTableH=NetworkTable.getTable("GRIP/HorizontalGoal");
        this.set(new ModeMap()
        {{
            setAutonomousMode(new AutonomousMode());
            setTeleopMode(new TeleopMode());
        }});
        this.set(new ModuleMap()
   		{{
            add("Drive", new Drive());
            add("Dashboard", new Dashboard());
        }});
        this.set(new CoordinatorList() {{
            add(new DashboardSystem());
            add(new GearSystem());
        }});
    }
    
}
