package com._604robotics.robot2016.modes;

import com._604robotics.robotnik.coordinator.Coordinator;
import com._604robotics.robotnik.coordinator.connectors.Binding;
import com._604robotics.robotnik.coordinator.connectors.DataWire;
import com._604robotics.robotnik.module.Module;
import com._604robotics.robotnik.module.ModuleManager;
import com._604robotics.robotnik.prefabs.controller.joystick.JoystickController;
import com._604robotics.robotnik.prefabs.controller.xbox.XboxController;
import com._604robotics.robotnik.prefabs.trigger.TriggerAnd;
import com._604robotics.robotnik.prefabs.trigger.TriggerNot;
import com._604robotics.robotnik.prefabs.trigger.TriggerToggle;
import com._604robotics.robot2016.constants.Calibration;
import com._604robotics.robot2016.constants.Ports;

import edu.wpi.first.wpilibj.Joystick;

public class TeleopMode extends Coordinator {
    private final XboxController driver = new XboxController(Ports.DRIVER);
    private final XboxController manipulator = new XboxController(Ports.MANIPULATOR);
    private final JoystickController manip = new JoystickController(Ports.DRIVER);

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
        
        manip.axisX.setFactor(-1);
        manip.axisY.setDeadband(0.3);
    }

    @Override
    protected void apply (ModuleManager modules) {
        /* Driving */
        {
            /* Drive Modes */
            {
            	//final TriggerToggle modeToggle = new TriggerToggle(driver.buttons.LB, false);
            	//this.bind(new Binding(modules.getModule("Drive").getAction("Tank Drive"), modeToggle.on));
            	//this.bind(new Binding(modules.getModule("Drive").getAction("Arcade Drive"), modeToggle.off));
            }
            final TriggerToggle gearToggle=new TriggerToggle(driver.buttons.RB, false);
            this.bind(new Binding(modules.getModule("Shifter").getAction("High Gear"), gearToggle.on));
            this.bind(new Binding(modules.getModule("Shifter").getAction("Low Gear"), gearToggle.off));
            
            final TriggerToggle clampToggle = new TriggerToggle(manipulator.buttons.LT, false);
            this.bind(new Binding(modules.getModule("DoubleClamp").getAction("Open"), clampToggle.on));
            this.bind(new Binding(modules.getModule("DoubleClamp").getAction("Close"), clampToggle.off));
            /* Safe Toggle */
            {
            	final TriggerToggle safeToggle=new TriggerToggle(driver.buttons.RB, false);
                this.bind(new Binding(modules.getModule("SafeToggle").getAction("Safe Mode On"), safeToggle.on));
                this.bind(new Binding(modules.getModule("SafeToggle").getAction("Safe Mode Off"), safeToggle.off));
            }
            
            /* Arcade Drive */
            {   
            	this.bind(new Binding(modules.getModule("Drive").getAction("Stick Drive"), modules.getModule("Dashboard").getTrigger("Arcade Drive")));
                this.fill(new DataWire(modules.getModule("Drive").getAction("Stick Drive"), "throttle", driver.leftStick.Y));
                this.fill(new DataWire(modules.getModule("Drive").getAction("Stick Drive"), "turn", driver.rightStick.X));
                this.fill(new DataWire(modules.getModule("Drive").getAction("Stick Drive"), "Throttled", driver.buttons.LB));
            }
            /* Stick Drive */
            {
            	this.bind(new Binding(modules.getModule("Drive").getAction("Stick Drive"), modules.getModule("Dashboard").getTrigger("Stick Drive")));
            	this.fill(new DataWire(modules.getModule("Drive").getAction("Stick Drive"), "throttle", manip.axisY));
            	this.fill(new DataWire(modules.getModule("Drive").getAction("Stick Drive"), "turn", manip.axisX));
            	final TriggerToggle stickToggle=new TriggerToggle(manip.buttons.Button3, false);
                this.bind(new Binding(modules.getModule("Shifter").getAction("High Gear"), stickToggle.on));
                this.bind(new Binding(modules.getModule("Shifter").getAction("Low Gear"), stickToggle.off));
            }

            /* Tank Drive */
            {
            	this.bind(new Binding(modules.getModule("Drive").getAction("Tank Drive"), modules.getModule("Dashboard").getTrigger("Tank Drive")));
                this.fill(new DataWire(modules.getModule("Drive").getAction("Tank Drive"), "Left Power", driver.leftStick.Y));
                this.fill(new DataWire(modules.getModule("Drive").getAction("Tank Drive"), "Right Power", driver.rightStick.Y));
                this.fill(new DataWire(modules.getModule("Drive").getAction("Tank Drive"), "Throttled", driver.buttons.LB));
            }
            
            /* Shifter */
            {
                // localized to the different modes
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
