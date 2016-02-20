package com._604robotics.robot2016.modules;

import com._604robotics.robotnik.data.DataMap;
import com._604robotics.robotnik.data.sources.DashboardData;
import com._604robotics.robotnik.module.Module;
import com._604robotics.robotnik.trigger.TriggerMap;
import com._604robotics.robotnik.trigger.sources.DashboardTriggerChoice;

public class Dashboard extends Module {
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
            
            final DashboardTriggerChoice autonOn = new DashboardTriggerChoice("Auton On");
            add("Auton On", autonOn.addDefault("Auton On"));
            add("Auton Off", autonOn.add("Auton Off"));
        }});
        
        this.set(new DataMap() {{
        	/* Drive servo testing */
        	{
        		add("Left Drive Servo", new DashboardData("Left Drive Servo", -120D));
        		add("Right Drive Servo", new DashboardData("Right Drive Servo", -120D));
        		add("Drive Servo Power Cap", new DashboardData("Drive Servo Power Cap", 0.5D));
        	}

        	/* Shooter */
        	{
        		add("Shooter Target Speed", new DashboardData("Shooter Target Speed", 0));
        		add("Shooter Threshold", new DashboardData("Shooter Threshold", 10));
        	}
        	
        	/* PickUp */
        	{
        		add("Pickup Down Angle", new DashboardData("Pickup Down Angle", 0));
        		add("Pickup Mid Angle", new DashboardData("Pickup Mid Angle", 45));
        		add("Pickup Up Angle", new DashboardData("Pickup Up Angle", 90));
        	}
    	}});
    }
}
