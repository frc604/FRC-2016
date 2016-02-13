package com._604robotics.robot2016;

import com._604robotics.robot2016.modes.AutonomousMode;
import com._604robotics.robot2016.modes.TeleopMode;
import com._604robotics.robot2016.modules.Dashboard;
import com._604robotics.robot2016.modules.Drive;
import com._604robotics.robot2016.modules.Gear;
import com._604robotics.robot2016.modules.Intake;
import com._604robotics.robot2016.modules.Pickup;
import com._604robotics.robot2016.modules.Regulator;
import com._604robotics.robot2016.modules.Shifter;
import com._604robotics.robot2016.modules.Shooter;
import com._604robotics.robot2016.systems.DashboardSystem;
import com._604robotics.robot2016.systems.GearSystem;
import com._604robotics.robotnik.Robot;
import com._604robotics.robotnik.module.ModuleReference;

public class Robot2016 extends Robot<Robot2016> {
    public final ModuleReference regulator = addModule("Regulator", new Regulator());
    public final ModuleReference drive = addModule("Drive", new Drive());
    public final ModuleReference dashboard = addModule("Dashboard", new Dashboard());
    public final ModuleReference pickup = addModule("Pickup", new Pickup());
    public final ModuleReference gear = addModule("Gear", new Gear());
    public final ModuleReference shifter = addModule("Shifter", new Shifter());
    public final ModuleReference intake = addModule("Intake", new Intake());
    public final ModuleReference shooter = addModule("Shooter", new Shooter());

    public Robot2016() {
        addSystem(new DashboardSystem());
        addSystem(new GearSystem());

        setAutonomousMode(new AutonomousMode());
        setTeleopMode(new TeleopMode());
    }
}
