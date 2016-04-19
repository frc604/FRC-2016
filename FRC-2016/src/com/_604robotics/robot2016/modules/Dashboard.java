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
            add("Systems Check: Auton", autonObstacle.add("Systems Check: Auton"));
            add("Systems Check: Pickup", autonObstacle.add("Systems Check: Pickup"));
            add("Systems Check: Shooter", autonObstacle.add("Systems Check: Shooter"));
			
            final DashboardTriggerChoice autonOn = new DashboardTriggerChoice("Auton Mode");
            add("Auton On", autonOn.addDefault("Auton On"));
            add("Auton Off", autonOn.add("Auton Off"));
            
            final DashboardTriggerChoice debugOn = new DashboardTriggerChoice("Debug On");
            add("Debug Off", debugOn.addDefault("Debug Off"));
            add("Debug On", debugOn.add("Debug On"));
        }});

        this.set(new DataMap() {{
            /* Shooter */
            {
                add("Shooter Target Speed", new DashboardData("Shooter Target Speed", Calibration.SHOOTER_TARGET_SPEED));
                add("Shooter Threshold", new DashboardData("Shooter Threshold", Calibration.SHOOTER_SPEED_THRESHOLD));
                add("Shooter Minimum Charge Time", new DashboardData("Shooter Minimum Charge Time", Calibration.SHOOTER_MINIMUM_CHARGE_TIME));
            }
            
            /* Intake */
            {
	            add("Intake Shoot Power", new DashboardData("Intake Shoot Power", Calibration.INTAKE_SHOOT_POWER));
            }

            /* MANTIS */
            {
                add("Mantis Stow Angle", new DashboardData("Mantis Stow Angle", Calibration.MANTIS_STOW_ANGLE));
                add("Mantis Deploy Angle", new DashboardData("Mantis Deploy Angle", Calibration.MANTIS_DEPLOY_ANGLE));

                add("Mantis Stow Tolerance", new DashboardData("Mantis Stow Tolerance", Calibration.MANTIS_STOW_TOLERANCE));
                add("Mantis Deploy Tolerance", new DashboardData("Mantis Deploy Tolerance", Calibration.MANTIS_DEPLOY_TOLERANCE));
            }
        }});
    }
}
