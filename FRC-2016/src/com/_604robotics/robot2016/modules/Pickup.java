package com._604robotics.robot2016.modules;

import com._604robotics.robot2016.constants.Calibration;
import com._604robotics.robot2016.constants.Ports;
import com._604robotics.robotnik.action.controllers.StateController;
import com._604robotics.robotnik.data.DataMap;
import com._604robotics.robotnik.module.Module;
import com._604robotics.robotnik.prefabs.actions.PIDAction;
import com._604robotics.robotnik.prefabs.devices.MA3A10;
import com._604robotics.robotnik.prefabs.devices.MultiOutput;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Pickup extends Module {
    private final MA3A10 encoder = new MA3A10(Ports.PICKUP_ENCODER);
    
    private final Victor rightVictor = new Victor(Ports.PICKUP_MOTOR_RIGHT);
    
    private final MultiOutput motors = new MultiOutput(
            rightVictor,
            new Victor(Ports.PICKUP_MOTOR_LEFT));

    private final PIDController pid = new PIDController(
            Calibration.PICKUP_PID_P,
            Calibration.PICKUP_PID_I,
            Calibration.PICKUP_PID_D,
            encoder, motors);

    public Pickup () {
        encoder.setZeroAngle(Calibration.PICKUP_ZERO_ANGLE);
        pid.setOutputRange(Calibration.INTAKE_PID_MIN, Calibration.INTAKE_PID_MAX);
        rightVictor.setInverted(true);
        SmartDashboard.putData("Pickup PID", pid);

        set(new DataMap() {{
            add("Pickup Angle", encoder::getAngle);
        }});

        
        set(new StateController() {{
            add("Down", new PIDAction(pid, Calibration.PICKUP_DOWN_ANGLE));
            add("Mid", new PIDAction(pid, Calibration.PICKUP_MID_ANGLE));
            addDefault("Up", new PIDAction(pid, Calibration.PICKUP_UP_ANGLE));
        }});
        
    }
}
