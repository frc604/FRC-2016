package com._604robotics.robot2016.modes;

import com._604robotics.robotnik.coordinator.Coordinator;
import com._604robotics.robotnik.coordinator.connectors.Binding;
import com._604robotics.robotnik.coordinator.connectors.DataWire;
import com._604robotics.robotnik.module.ModuleManager;
import com._604robotics.robotnik.prefabs.controller.xbox.XboxController;
import com._604robotics.robotnik.prefabs.trigger.TriggerAnd;
import com._604robotics.robotnik.prefabs.trigger.TriggerNot;
import com._604robotics.robotnik.prefabs.trigger.TriggerToggle;
import com._604robotics.robot2016.constants.Calibration;
import com._604robotics.robot2016.constants.Ports;

public class TeleopMode extends Coordinator {
    private final XboxController driver = new XboxController(Ports.DRIVER);
    private final XboxController manipulator = new XboxController(Ports.MANIPULATOR);

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
        manipulator.rightStick.Y.setDeadband(Calibration.TELEOP_DEADBAND);
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
                this.fill(new DataWire(modules.getModule("Drive").getAction("Tank Drive"), "Throttled", driver.buttons.LB));
            }

            /* Shifter */
            {
                this.bind(new Binding(modules.getModule("Shifter").getAction("High Gear"), new TriggerToggle(driver.buttons.RB, false).on));
            }
        }

        /* Manipulating */
        {
            /* Shooter */
            {
                this.bind(new Binding(modules.getModule("Shooter").getAction("Shoot"), new TriggerAnd(
                        manipulator.buttons.RT,
                        new TriggerNot(modules.getModule("Pickup").getAction("Stow").active()))));
                
                this.bind(new Binding(modules.getModule("Shooter").getAction("Spit"), new TriggerAnd(
                        manipulator.buttons.LT,
                        new TriggerNot(modules.getModule("Pickup").getAction("Stow").active()))));
            }

            /* Intake */
            {
                this.fill(new DataWire(modules.getModule("Intake").getAction("Run"), "Power", manipulator.leftStick.Y));
            }

            /* Pickup */
            {
                this.bind(new Binding(modules.getModule("Pickup").getAction("Manual"), manipulator.buttons.X));
                this.fill(new DataWire(modules.getModule("Pickup").getAction("Manual"), "Power", manipulator.rightStick.Y));
                this.fill(new DataWire(modules.getModule("Pickup").getAction("Manual"), "Reset Encoder", manipulator.buttons.RB));

                this.bind(new Binding(modules.getModule("Pickup").getAction("Deploy"), manipulator.buttons.A));
                this.bind(new Binding(modules.getModule("Pickup").getAction("Stow"), manipulator.buttons.Y));
            }
        }
    }
}
