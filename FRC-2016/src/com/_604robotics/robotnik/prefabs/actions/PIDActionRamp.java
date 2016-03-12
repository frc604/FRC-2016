package com._604robotics.robotnik.prefabs.actions;

import com._604robotics.robot2016.constants.Calibration;
import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.ActionData;
import com._604robotics.robotnik.action.field.FieldMap;

import edu.wpi.first.wpilibj.PIDController;

/**
 * An action configuring the PID setpoint.
 */
public class PIDActionRamp extends Action {
    private final PIDController pid;
    
    /**
     * Creates a PID action.
     * @param pid PID controller to use.
     * @param defaultSetpoint Default setpoint value.
     */
    public PIDActionRamp (PIDController pid, double defaultSetpoint) {
        super(new FieldMap() {{
            define("Setpoint", defaultSetpoint);
        }});

        this.pid = pid;
    }

    @Override
    public void begin (ActionData data) {
        pid.enable();
        pid.setSetpoint(data.get("Setpoint"));
    }

    @Override
    public void run (ActionData data) {
        final double currentSetpoint = data.get("Setpoint");
        if (pid.getSetpoint() != currentSetpoint) {
            if(Math.abs(pid.getSetpoint()-currentSetpoint)<Calibration.INCREMENT)
            {
                pid.setSetpoint(currentSetpoint);
            }
            else
            {
                if(pid.getSetpoint() > currentSetpoint)
                {
                    pid.setSetpoint(pid.getSetpoint() - Calibration.INCREMENT);
                }
                else if(pid.getSetpoint() < currentSetpoint)
                {
                    pid.setSetpoint(pid.getSetpoint() + Calibration.INCREMENT);
                }
                else
                {
                    
                }
            }
        }
    }

    @Override
    public void end (ActionData data) {
        pid.reset();
    }
}
