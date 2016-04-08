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
            add("Auton On", autonOn.addDefault("Auton Off"));
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

            /* Pickup */
            /*{
                add("Pickup Stow Angle", new DashboardData("Pickup Stow Angle", Calibration.PICKUP_STOW_ANGLE));
                add("Pickup Stow PID Tolerance", new DashboardData("Pickup Stow PID Tolerance", Calibration.PICKUP_STOW_PID_TOLERANCE));
                add("Pickup Stow Power", new DashboardData("Pickup Stow Power", Calibration.PICKUP_STOW_POWER));
                add("Pickup Stow Threshold", new DashboardData("Pickup Stow Threshold", Calibration.PICKUP_STOW_THRESHOLD));
                
                add("Pickup Deploy Angle", new DashboardData("Pickup Deploy Angle", Calibration.PICKUP_DEPLOY_ANGLE));
                add("Pickup Deploy PID Tolerance", new DashboardData("Pickup Deploy PID Tolerance", Calibration.PICKUP_DEPLOY_PID_TOLERANCE));
                add("Pickup Deploy Upper Power", new DashboardData("Pickup Deploy Upper Power", Calibration.PICKUP_UPPER_POWER));
                add("Pickup Deploy Upper Threshold", new DashboardData("Pickup Deploy Upper Threshold", Calibration.PICKUP_DEPLOY_UPPERTHRESHOLD));
                add("Pickup Deploy Lower Power", new DashboardData("Pickup Deploy Lower Power", Calibration.PICKUP_LOWER_POWER));
                add("Pickup Deploy Lower Threshold", new DashboardData("Pickup Deploy Lower Threshold", Calibration.PICKUP_DEPLOY_LOWERTHRESHOLD));
            }*/
        }});
    }
}
