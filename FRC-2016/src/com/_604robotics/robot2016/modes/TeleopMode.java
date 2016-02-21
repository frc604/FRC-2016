package com._604robotics.robot2016.modes;

import com._604robotics.robotnik.coordinator.Coordinator;
import com._604robotics.robotnik.coordinator.connectors.Binding;
import com._604robotics.robotnik.coordinator.connectors.DataWire;
import com._604robotics.robotnik.module.ModuleManager;
import com._604robotics.robotnik.prefabs.controller.xbox.XboxController;
import com._604robotics.robotnik.prefabs.trigger.TriggerToggle;
import com._604robotics.robot2016.constants.Calibration;

public class TeleopMode extends Coordinator {
    private final XboxController driver = new XboxController(0);
    private final XboxController manipulator = new XboxController(1);

    public TeleopMode () {        
        driver.leftStick.X.setDeadband(Calibration.TELEOP_DEADBAND);
        driver.leftStick.Y.setDeadband(Calibration.TELEOP_DEADBAND);

        driver.leftStick.X.setFactor(Calibration.TELEOP_FACTOR);
        driver.leftStick.Y.setFactor(Calibration.TELEOP_FACTOR);

        driver.rightStick.X.setDeadband(Calibration.TELEOP_DEADBAND);
        driver.rightStick.Y.setDeadband(Calibration.TELEOP_DEADBAND);

        driver.rightStick.X.setFactor(Calibration.TELEOP_FACTOR);
        driver.rightStick.Y.setFactor(Calibration.TELEOP_FACTOR);

        manipulator.leftStick.Y.setDeadband(Calibration.TELEOP_DEADBAND);
    }

    @Override
    protected void apply (ModuleManager modules) {
        /* Driving */
        {
            /* Tank Drive */
            {   
                this.bind(new Binding(modules.getModule("Drive").getAction("Tank Drive"), modules.getModule("Dashboard").getTrigger("Tank Drive")));

                this.fill(new DataWire(modules.getModule("Drive").getAction("Tank Drive"), "Left Power", driver.leftStick.Y));
                this.fill(new DataWire(modules.getModule("Drive").getAction("Tank Drive"), "Right Power", driver.rightStick.Y));
            }

            /* Geared Drive */
            {
                this.bind(new Binding(modules.getModule("Drive").getAction("Geared Drive"), modules.getModule("Dashboard").getTrigger("Geared Drive")));

                this.fill(new DataWire(modules.getModule("Drive").getAction("Geared Drive"), "Left Power", driver.leftStick.Y));
                this.fill(new DataWire(modules.getModule("Drive").getAction("Geared Drive"), "Right Power", driver.rightStick.Y));

                this.fill(new DataWire(modules.getModule("Drive").getAction("Geared Drive"), "Left Low Gear", driver.buttons.LT));
                this.fill(new DataWire(modules.getModule("Drive").getAction("Geared Drive"), "Left High Gear", driver.buttons.LB));
                this.fill(new DataWire(modules.getModule("Drive").getAction("Geared Drive"), "Right Low Gear", driver.buttons.LT));
                this.fill(new DataWire(modules.getModule("Drive").getAction("Geared Drive"), "Right High Gear", driver.buttons.LB));
            }

            /* Shifter */
            {
                final TriggerToggle shift = new TriggerToggle(driver.buttons.RB, false);
                this.bind(new Binding(modules.getModule("Shifter").getAction("Low Gear"), shift.off));
                this.bind(new Binding(modules.getModule("Shifter").getAction("High Gear"), shift.on));
            }
        }

        /* Manipulating */
        {
            /* Shooter */
            {
                this.bind(new Binding(modules.getModule("Shooter").getAction("Shoot"), manipulator.buttons.RT));
            }

            /* Intake */
            {
                this.fill(new DataWire(modules.getModule("Intake").getAction("Run"), "Power", manipulator.leftStick.Y));
            }

            /* Pickup */
            {
                this.bind(new Binding(modules.getModule("Pickup").getAction("Down"), manipulator.buttons.A));
                this.bind(new Binding(modules.getModule("Pickup").getAction("Mid"), manipulator.buttons.X));
                this.bind(new Binding(modules.getModule("Pickup").getAction("Up"), manipulator.buttons.Y));
            }
        }
    }
}
