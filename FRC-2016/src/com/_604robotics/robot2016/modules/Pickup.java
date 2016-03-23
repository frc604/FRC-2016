package com._604robotics.robot2016.modules;

import com._604robotics.robot2016.constants.Calibration;
import com._604robotics.robot2016.constants.Ports;
import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.ActionData;
import com._604robotics.robotnik.action.controllers.ElasticController;
import com._604robotics.robotnik.action.field.FieldMap;
import com._604robotics.robotnik.data.DataMap;
import com._604robotics.robotnik.module.Module;
import com._604robotics.robotnik.prefabs.devices.MA3A10;
import com._604robotics.robotnik.prefabs.devices.MultiOutput;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Pickup extends Module {
    private final MA3A10 encoder = new MA3A10(Ports.PICKUP_ENCODER);
    
    private final Victor leftVictor = new Victor(Ports.PICKUP_MOTOR_LEFT);
    private final Victor rightVictor = new Victor(Ports.PICKUP_MOTOR_RIGHT);
    
    private final MultiOutput motors = new MultiOutput(leftVictor, rightVictor);

    private final PIDController pidStow = new PIDController(
            Calibration.PICKUP_STOW_PID_P,
            Calibration.PICKUP_STOW_PID_I,
            Calibration.PICKUP_STOW_PID_D,
            encoder, motors);
    private final PIDController pidDeploy = new PIDController(
            Calibration.PICKUP_DEPLOY_PID_P,
            Calibration.PICKUP_DEPLOY_PID_I,
            Calibration.PICKUP_DEPLOY_PID_D,
            encoder, motors);
    
    public Pickup () {
        encoder.setZeroAngle(Calibration.PICKUP_ZERO_ANGLE);
        rightVictor.setInverted(true);

        pidStow.setOutputRange(Calibration.PICKUP_PID_MIN, Calibration.PICKUP_PID_MAX);
        pidDeploy.setOutputRange(Calibration.PICKUP_PID_MIN, Calibration.PICKUP_PID_MAX);
        
        SmartDashboard.putData("Pickup PID Up", pidStow);
        SmartDashboard.putData("Pickup PID Down", pidDeploy);
        
        set(new DataMap() {{
            add("Pickup Angle", encoder::getAngle);
        }});

        set(new ElasticController() {{
            addDefault("Manual", new Action(new FieldMap() {{
                define("Power", 0D);
                define("Reset Encoder", false);
                define("Deploy Angle", Calibration.PICKUP_DEPLOY_ANGLE);
            }}) {
                private final Timer resetTimer = new Timer();
                
                @Override
                public void begin (ActionData data) {
                    resetTimer.start();
                }

                @Override
                public void run (ActionData data) {
                    if (!data.is("Reset Encoder")) {
                        resetTimer.reset();
                    }

                    if (resetTimer.get() > Calibration.PICKUP_RESET_TIME) {
                        // To reset, the driver manually positions the pickup at
                        // the Deploy position and holds down the reset button.
                        // Therefore, our zero angle should be set such that the
                        // current pickup position would produce the correct
                        // angle for the Deploy position.
                        encoder.setZeroAngle(encoder.getRawAngle() - data.get("Deploy Angle"));
                    }

                    // TODO: Instead of stopMotor, make this set the motor power to the current value of the "Power" field.
                    // TODO: Hook up the "Power" field to the right manipulator joystick Y value in Teleop Mode.
                    //motors.stopMotor();
                    motors.set(data.get("Power"));
                }
                
                @Override
                public void end (ActionData data) {
                    resetTimer.stop();
                    resetTimer.reset();
                }
            });

            add("Stow", new AngleAction(pidStow, Calibration.PICKUP_STOW_ANGLE, Calibration.PICKUP_STOW_TOLERANCE));
            add("Deploy", new AngleAction(pidDeploy, Calibration.PICKUP_DEPLOY_ANGLE, Calibration.PICKUP_DEPLOY_TOLERANCE));
        }});
    }
        
    private class AngleAction extends Action {
        private final PIDController pid;
        
        public AngleAction (PIDController pid, double defaultAngle, double defaultTolerance) {
            super(new FieldMap() {{
                define("Setpoint", defaultAngle);
                define("Tolerance", defaultTolerance);
            }});
            this.pid = pid;
        }

        @Override
        public void begin (ActionData data) {
            pid.setSetpoint(data.get("Setpoint"));
            pid.enable();
        }

        @Override
        public void run (ActionData data) {
            final double angle = data.data("Pickup Angle");
            final double setpoint = data.get("Setpoint");
            final double tolerance = data.get("Tolerance");
            
            if (setpoint != pid.getSetpoint()) {
                pid.setSetpoint(setpoint);
            }
            
            if (Math.abs(angle - setpoint) < tolerance) {
                if (pid.isEnabled()) {
                    pid.disable();
                }
                motors.stopMotor();
            } else if(!pid.isEnabled()) {
                pid.enable();
            }
        }

        @Override
        public void end (ActionData data) {
            pid.disable();
        }
    }
}
