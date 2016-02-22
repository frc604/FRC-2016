package com._604robotics.robot2016.modules;

import com._604robotics.robot2016.constants.Calibration;
import com._604robotics.robot2016.constants.Ports;
import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.ActionData;
import com._604robotics.robotnik.action.controllers.ElasticController;
import com._604robotics.robotnik.data.DataMap;
import com._604robotics.robotnik.module.Module;
import com._604robotics.robotnik.prefabs.actions.PIDAction;
import com._604robotics.robotnik.prefabs.devices.MA3A10;
import com._604robotics.robotnik.prefabs.devices.MultiOutput;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Pickup extends Module {
    private final MA3A10 encoder = new MA3A10(Ports.PICKUP_ENCODER);

    private final Victor leftMotor = new Victor(Ports.PICKUP_MOTOR_LEFT);
    private final Victor rightMotor = new Victor(Ports.PICKUP_MOTOR_RIGHT);

    private final MultiOutput motors = new MultiOutput(new PIDOutput[] { leftMotor, rightMotor });

    private final PIDController pid = new PIDController(
            Calibration.PICKUP_PID_P,
            Calibration.PICKUP_PID_I,
            Calibration.PICKUP_PID_D,
            encoder, motors);

    public Pickup () {
        encoder.setZeroAngle(Calibration.PICKUP_ZERO_ANGLE);

        SmartDashboard.putData("Pickup PID", pid);

        set(new DataMap() {{
            add("Pickup Angle", encoder::getAngle);
        }});

        set(new ElasticController() {{
            addDefault("Off", new Action() {
                @Override
                public void run (ActionData data) {
                    motors.set(0);
                }
            });

            add("Down", new PIDAction(pid, Calibration.PICKUP_DOWN_ANGLE));
            add("Mid", new PIDAction(pid, Calibration.PICKUP_MID_ANGLE));
            add("Up", new PIDAction(pid, Calibration.PICKUP_UP_ANGLE));
        }});
    }
}
