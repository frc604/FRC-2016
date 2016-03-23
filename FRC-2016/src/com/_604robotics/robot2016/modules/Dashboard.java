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

            /* Pickup */
            {
                add("Pickup Stow Angle", new DashboardData("Pickup Stow Angle", Calibration.PICKUP_STOW_ANGLE));
                add("Pickup Deploy Angle", new DashboardData("Pickup Deploy Angle", Calibration.PICKUP_DEPLOY_ANGLE));

                add("Pickup Stow Tolerance", new DashboardData("Pickup Stow Tolerance", Calibration.PICKUP_STOW_TOLERANCE));
                add("Pickup Deploy Tolerance", new DashboardData("Pickup Deploy Tolerance", Calibration.PICKUP_DEPLOY_TOLERANCE));
            }
        }});
    }
}
