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

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Mantis extends Module {
    private final MA3A10 encoder = new MA3A10(Ports.MANTIS_ENCODER);
    
    private final Victor Victor = new Victor(Ports.MANTIS_VICTOR);
    
    private final PIDController pidStow = new PIDController(
            Calibration.MANTIS_STOW_PID_P,
            Calibration.MANTIS_STOW_PID_I,
            Calibration.MANTIS_STOW_PID_D,
            encoder, Victor);
    private final PIDController pidDeploy = new PIDController(
            Calibration.MANTIS_DEPLOY_PID_P,
            Calibration.MANTIS_DEPLOY_PID_I,
            Calibration.MANTIS_DEPLOY_PID_D,
            encoder, Victor);
    
    public Mantis () {
        encoder.setZeroAngle(Calibration.MANTIS_ZERO_ANGLE);
        
        pidStow.setOutputRange(Calibration.MANTIS_PID_MIN, Calibration.MANTIS_PID_MAX);
        pidDeploy.setOutputRange(Calibration.MANTIS_PID_MIN, Calibration.MANTIS_PID_MAX);
        
        SmartDashboard.putData("MANTIS PID Up", pidStow);
        SmartDashboard.putData("MANTIS PID Down", pidDeploy);
        
        set(new DataMap() {{
            add("MANTIS Angle", encoder::getAngle);
        }});

        set(new ElasticController() {{
            addDefault("Manual", new Action(new FieldMap() {{
                define("Power", 0D);
                define("Reset Encoder", false);
                define("Deploy Angle", Calibration.MANTIS_DEPLOY_ANGLE);
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

                    if (resetTimer.get() > Calibration.MANTIS_RESET_TIME) {
                        // To reset, the driver manually positions the MANTIS at
                        // the Deploy position and holds down the reset button.
                        // Therefore, our zero angle should be set such that the
                        // current Mantis position would produce the correct
                        // angle for the Deploy position.
                        encoder.setZeroAngle(encoder.getRawAngle() - data.get("Deploy Angle"));
                    }

                    // TODO: Instead of stopMotor, make this set the motor power to the current value of the "Power" field.
                    // TODO: Hook up the "Power" field to the right manipulator joystick Y value in Teleop Mode.
                    Victor.stopMotor();
                }
                
                @Override
                public void end (ActionData data) {
                    resetTimer.stop();
                    resetTimer.reset();
                }
            });

            add("Stow", new AngleAction(pidStow, Calibration.MANTIS_STOW_ANGLE, Calibration.MANTIS_STOW_TOLERANCE));
            add("Deploy", new AngleAction(pidDeploy, Calibration.MANTIS_DEPLOY_ANGLE, Calibration.MANTIS_DEPLOY_TOLERANCE));
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
            final double angle = data.data("Mantis Angle");
            final double setpoint = data.get("Setpoint");
            final double tolerance = data.get("Tolerance");
            
            if (setpoint != pid.getSetpoint()) {
                pid.setSetpoint(setpoint);
            }
            
            if (Math.abs(angle - setpoint) < tolerance) {
                if (pid.isEnabled()) {
                    pid.disable();
                }
                Victor.stopMotor();
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