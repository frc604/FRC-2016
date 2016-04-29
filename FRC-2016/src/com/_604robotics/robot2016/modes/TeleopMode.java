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
            /* Drive Modes */
            {
            	final TriggerToggle modeToggle = new TriggerToggle(driver.buttons.LB, false);
            	this.bind(new Binding(modules.getModule("Drive").getAction("Tank Drive"), modeToggle.on));
            	this.bind(new Binding(modules.getModule("Drive").getAction("Arcade Drive"), modeToggle.off));
            }
            /* Safe Mode */
            {
            	final TriggerToggle gearToggle=new TriggerToggle(driver.buttons.RB, false);
                this.bind(new Binding(modules.getModule("SafeToggle").getAction("Safe Mode On"), gearToggle.on));
                this.bind(new Binding(modules.getModule("SafeToggle").getAction("Safe Mode Off"), gearToggle.off));
            }
            
            /* Arcade Drive */
            {   
                this.fill(new DataWire(modules.getModule("Drive").getAction("Arcade Drive"), "Move Power", driver.leftStick.Y));
                this.fill(new DataWire(modules.getModule("Drive").getAction("Arcade Drive"), "Rotate Power", driver.rightStick.X));
                this.fill(new DataWire(modules.getModule("Drive").getAction("Arcade Drive"), "Throttled", driver.buttons.LB));
            }

            /* Tank Drive */
            {
                this.fill(new DataWire(modules.getModule("Drive").getAction("Tank Drive"), "Left Power", driver.leftStick.Y));
                this.fill(new DataWire(modules.getModule("Drive").getAction("Tank Drive"), "Right Power", driver.rightStick.Y));
                this.fill(new DataWire(modules.getModule("Drive").getAction("Tank Drive"), "Throttled", driver.buttons.LB));
                
            }
            
            /* Shifter */
            {
                final TriggerToggle gearToggle=new TriggerToggle(driver.buttons.RB, false);
                this.bind(new Binding(modules.getModule("Shifter").getAction("High Gear"), gearToggle.on));
                this.bind(new Binding(modules.getModule("Shifter").getAction("Low Gear"), gearToggle.off));
            }
        }

        /* Manipulating */ 
        {
        	/*
            // Shooter 
            {
                this.bind(new Binding(modules.getModule("Shooter").getAction("Shoot"),
                		new TriggerAnd(
                                manipulator.buttons.RT,
                                new TriggerNot(modules.getModule("PneumaticPickup").getAction("Stow").active()))));
                
                this.bind(new Binding(modules.getModule("Shooter").getAction("Spit"),
                		new TriggerAnd(
                        manipulator.buttons.LT,
                        new TriggerNot(modules.getModule("PneumaticPickup").getAction("Stow").active()))));
            }
            */

        	/* Mantis */
        	{
        		this.bind(new Binding(modules.getModule("Mantis").getAction("Close"), manipulator.buttons.LT));
                this.bind(new Binding(modules.getModule("Mantis").getAction("Open"), manipulator.buttons.RT));
        	}
        	
            /* Intake */
            {
                this.fill(new DataWire(modules.getModule("Intake").getAction("Run"), "Power", manipulator.leftStick.Y));
            }
            
            /* PneumaticPickup */
            {
            	
            	this.bind(new Binding(modules.getModule("PneumaticPickup").getAction("Down"), manipulator.buttons.A));
                this.bind(new Binding(modules.getModule("PneumaticPickup").getAction("Stow"), manipulator.buttons.Y));
            }
            
        }
    }
}
