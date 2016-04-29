package com._604robotics.robot2016.modules;

import com._604robotics.robot2016.constants.Calibration;
import com._604robotics.robotnik.data.DataMap;
import com._604robotics.robotnik.data.sources.DashboardData;
import com._604robotics.robotnik.module.Module;
import com._604robotics.robotnik.trigger.TriggerMap;
import com._604robotics.robotnik.trigger.sources.DashboardTriggerChoice;

public class Dashboard extends Module {
    public Dashboard () {
        this.set(new TriggerMap() {{
            final DashboardTriggerChoice driveOn = new DashboardTriggerChoice("Drive On");
            add("Drive On", driveOn.addDefault("Drive On"));
            add("Drive Off", driveOn.add("Drive Off"));
            
            final DashboardTriggerChoice autonObstacle = new DashboardTriggerChoice("Auton Obstacle");
            add("Default", autonObstacle.addDefault("Default"));
            add("Lowbar", autonObstacle.add("Lowbar"));
            add("Defense Mode", autonObstacle.add("Defense Mode"));
			
            final DashboardTriggerChoice autonOn = new DashboardTriggerChoice("Auton Mode");
            add("Auton On", autonOn.addDefault("Auton On"));
            add("Auton Off", autonOn.add("Auton Off"));
            
            final DashboardTriggerChoice debugOn = new DashboardTriggerChoice("Debug On");
            add("Debug Off", debugOn.addDefault("Debug Off"));
            add("Debug On", debugOn.add("Debug On"));            
        }});

        this.set(new DataMap() {{
            /* Intake */
            {
	            add("Intake Shoot Power", new DashboardData("Intake Shoot Power", Calibration.INTAKE_SHOOT_POWER));
            }
        }});
    }
}
