package com._604robotics.robot2016.modes;

import com._604robotics.robot2016.Robot2016;
import com._604robotics.robot2016.constants.Calibration;
import com._604robotics.robotnik.coordinator.Coordinator;
import com._604robotics.robotnik.prefabs.controller.xbox.XboxController;
import com._604robotics.robotnik.prefabs.trigger.TriggerToggle;

public class TeleopMode extends Coordinator {
    private final XboxController driver = new XboxController(0);
    private final XboxController manipulator = new XboxController(1);
    
    public TeleopMode (Robot2016 robot) {        
        driver.leftStick.X.setDeadband(Calibration.TELEOP_DEADBAND);
        driver.leftStick.Y.setDeadband(Calibration.TELEOP_DEADBAND);

        driver.leftStick.X.setFactor(Calibration.TELEOP_FACTOR);
        driver.leftStick.Y.setFactor(Calibration.TELEOP_FACTOR);

        driver.rightStick.X.setDeadband(Calibration.TELEOP_DEADBAND);
        driver.rightStick.Y.setDeadband(Calibration.TELEOP_DEADBAND);

        driver.rightStick.X.setFactor(Calibration.TELEOP_FACTOR);
        driver.rightStick.Y.setFactor(Calibration.TELEOP_FACTOR);

        manipulator.leftStick.Y.setDeadband(Calibration.TELEOP_DEADBAND);

    	/* Driving */
    	{
    	    /* Tank Drive */
    		{	
	    		bind(robot.drive.getAction("Tank Drive"), robot.dashboard.getTrigger("Tank Drive"));
	            fill(robot.drive.getAction("Tank Drive"), "Left Power", driver.leftStick.Y);
	            fill(robot.drive.getAction("Tank Drive"), "Right Power", driver.rightStick.Y);
    		}
    		
    		/* Geared Drive */
    		{
        		bind(robot.drive.getAction("Geared Drive"), robot.dashboard.getTrigger("Geared Drive"));
                fill(robot.drive.getAction("Geared Drive"), "Left Power", driver.leftStick.Y);
                fill(robot.drive.getAction("Geared Drive"), "Right Power", driver.rightStick.Y);

                fill(robot.drive.getAction("Geared Drive"), "Left Low Gear", driver.buttons.LT);
                fill(robot.drive.getAction("Geared Drive"), "Left High Gear", driver.buttons.LB);
                fill(robot.drive.getAction("Geared Drive"), "Right Low Gear", driver.buttons.LT);
                fill(robot.drive.getAction("Geared Drive"), "Right High Gear", driver.buttons.LB);
        	}
        	
            /* Shifter */
            {
                final TriggerToggle shift = new TriggerToggle(driver.buttons.RB, false);
                bind(robot.shifter.getAction("Low Gear"), shift.off);
                bind(robot.shifter.getAction("High Gear"), shift.on);
    		}
    	}
    	
    	/* Manipulating */
    	{
        	/* Shooter */
        	{
        		bind(robot.shooter.getAction("Shoot"), manipulator.buttons.RT);
        	}
        	
        	/* Intake */
        	{
        		fill(robot.intake.getAction("Run"), "Power", manipulator.leftStick.Y);
        	}
        	
        	/* Pickup */
        	{
        		bind(robot.pickup.getAction("Down"), manipulator.buttons.A);
        		bind(robot.pickup.getAction("Mid"), manipulator.buttons.X);
        		bind(robot.pickup.getAction("Up"), manipulator.buttons.Y);
        	}
    	}
    }
}
