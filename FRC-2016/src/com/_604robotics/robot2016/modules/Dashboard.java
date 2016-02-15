package com._604robotics.robot2016.modules;

import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.ActionData;
import com._604robotics.robotnik.action.controllers.ElasticController;
import com._604robotics.robotnik.data.DataMap;
import com._604robotics.robotnik.data.sources.DashboardData;
import com._604robotics.robotnik.module.Module;
import com._604robotics.robotnik.trigger.TriggerMap;
import com._604robotics.robotnik.trigger.sources.DashboardTriggerChoice;
import com._604robotics.robotnik.action.field.FieldMap;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// TODO: Auto-generated Javadoc
/**
 * The Class Dashboard.
 */
public class Dashboard extends Module {
    
    /**
     * Instantiates a new dashboard.
     */
    public Dashboard () {
        this.set(new TriggerMap() {{
            final DashboardTriggerChoice driveMode = new DashboardTriggerChoice("Drive Mode");
            add("Tank Drive", driveMode.addDefault("Tank Drive"));
            add("Geared Drive", driveMode.add("Geared Drive"));
            add("Servo Drive", driveMode.add("Servo Drive"));
            
            final DashboardTriggerChoice driveOn = new DashboardTriggerChoice("Drive On");
            add("Drive On", driveOn.addDefault("Drive On"));
            add("Drive Off", driveOn.add("Drive Off"));
            
            final DashboardTriggerChoice autonMode = new DashboardTriggerChoice("Auton Mode");
            add("Auton Mode A", autonMode.addDefault("Auton Mode A"));
            add("Auton Mode B", autonMode.add("Auton Mode B"));
            
            final DashboardTriggerChoice debuggingOn = new DashboardTriggerChoice("Debugging On");
            add("Debugging Off", debuggingOn.addDefault("Debugging Off"));
            add("Debugging On", debuggingOn.add("Debugging On"));
            
            final DashboardTriggerChoice autonOn = new DashboardTriggerChoice("Auton On");
            add("Auton Off", autonOn.add("Auton Off"));
            add("Auton On", autonOn.addDefault("Auton On"));
            
        }});
        this.set(new DataMap() {{
        	/* Drive servo testing */
        	{
        		add("Left Drive Servo", new DashboardData("Left Drive Servo", -120D));
        		add("Right Drive Servo", new DashboardData("Right Drive Servo", -120D));
        		add("Drive Servo Power Cap", new DashboardData("Drive Servo Power Cap", 0.5D));
        	}
    	}});
    }
}
