package com._604robotics.robot2016.modules;

import com._604robotics.robot2016.Ports;
import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.ActionData;
import com._604robotics.robotnik.action.controllers.ElasticController;
import com._604robotics.robotnik.action.field.FieldMap;
import com._604robotics.robotnik.data.Data;
import com._604robotics.robotnik.data.DataMap;
import com._604robotics.robotnik.module.Module;
import com._604robotics.robotnik.prefabs.devices.MultiOutput;
import com._604robotics.robotnik.trigger.Trigger;
import com._604robotics.robotnik.trigger.TriggerMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;

public class Shooter extends Module {
    private final MultiOutput motors = new MultiOutput(new PIDOutput[] { new Victor(Ports.SHOOTER_MOTOR_LEFT), new Victor(Ports.SHOOTER_MOTOR_RIGHT) });
    private final Encoder encoder = new Encoder(Ports.SHOOTER_ENCODER_A, Ports.SHOOTER_ENCODER_B);

    private final Timer chargeTimer = new Timer();

    public Shooter () {
        set(new DataMap() {{
            add("Current Speed", new Data() {
                @Override
                public double run () {
                    return encoder.getRate();
                }
            });
        }});

        set(new TriggerMap() {{
            add("Charged", new Trigger() {
                public boolean run () {
                    return chargeTimer.get() >= 0.5;
                }
            });
        }});

        set(new ElasticController() {{
            addDefault("Off", new Action() {
                public void run (ActionData data){
                    motors.stopMotor();
                }
            });

            add("Shoot", new Action(new FieldMap () {{
                define("Target Speed", 0);
                define("Threshold", 10);
            }}) {
                public void begin (ActionData data) {
                    chargeTimer.start();
                }

                public void run (ActionData data) {
                    if(Math.abs(data.get("Target Speed") - encoder.getRate()) >= data.get("Threshold")) {
                        chargeTimer.reset();
                    }

                    if (encoder.getRate() >= data.get("Target Speed")) {
                        motors.stopMotor();
                    } else {
                        motors.set(-1);
                    }
                }

                public void end (ActionData data) {
                    chargeTimer.stop();
                    chargeTimer.reset();

                    motors.stopMotor();
                }
            });
        }});
    }
}