package com._604robotics.robot2016;

import com._604robotics.robot2016.modes.AutonomousMode;
import com._604robotics.robot2016.modes.TeleopMode;
import com._604robotics.robot2016.modules.Dashboard;
import com._604robotics.robot2016.modules.DoubleClamp;
import com._604robotics.robot2016.modules.Drive;
import com._604robotics.robot2016.modules.Intake;
import com._604robotics.robot2016.modules.Clamp;
import com._604robotics.robot2016.modules.Regulator;
import com._604robotics.robot2016.modules.SafeToggle;
import com._604robotics.robot2016.modules.Shifter;
import com._604robotics.robot2016.modules.PneumaticPickup;
import com._604robotics.robot2016.systems.DashboardSystem;
import com._604robotics.robotnik.Robot;
import com._604robotics.robotnik.coordinator.CoordinatorList;
import com._604robotics.robotnik.coordinator.ModeMap;
import com._604robotics.robotnik.module.ModuleMap;

public class Robot2016 extends Robot {
    public Robot2016() {
        this.set(new ModeMap() {{
            setAutonomousMode(new AutonomousMode());
            setTeleopMode(new TeleopMode());
        }});

        this.set(new ModuleMap() {{
            add("Regulator", new Regulator());
            add("Drive", new Drive());
            add("Dashboard", new Dashboard());
            add("Clamp", new Clamp());
            add("DoubleClamp", new DoubleClamp());
            add("Shifter", new Shifter());
            add("Intake", new Intake());
            add("PneumaticPickup", new PneumaticPickup());
            add("SafeToggle", new SafeToggle());
        }});

        this.set(new CoordinatorList() {{
            add(new DashboardSystem());
        }});
    }
}
