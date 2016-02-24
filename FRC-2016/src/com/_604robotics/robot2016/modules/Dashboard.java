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

            final DashboardTriggerChoice visionOn = new DashboardTriggerChoice("Vision On");
            add("Vision On", visionOn.addDefault("Vision On"));
            add("Vision Off", visionOn.add("Vision Off"));
            
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
                add("Pickup Down Angle", new DashboardData("Pickup Down Angle", Calibration.PICKUP_DOWN_ANGLE));
                add("Pickup Mid Angle", new DashboardData("Pickup Mid Angle", Calibration.PICKUP_MID_ANGLE));
                add("Pickup Up Angle", new DashboardData("Pickup Up Angle", Calibration.PICKUP_UP_ANGLE));
            }
        }});
    }
}
