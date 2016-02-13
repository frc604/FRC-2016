/* Autonomous Mode Macros Needed:
	- Options for each defense
    - Manipulate gate, drawbridge, etc
	- Options for location will be needed if we plan on shooting
*/

package com._604robotics.robot2016.modes;

import com._604robotics.robot2016.Robot2016;
import com._604robotics.robotnik.coordinator.Coordinator;
import com._604robotics.robotnik.prefabs.measure.TriggerMeasure;
import com._604robotics.robotnik.prefabs.trigger.TriggerAnd;
import com._604robotics.robotnik.trigger.TriggerAccess;

public class AutonomousMode extends Coordinator<Robot2016> {
    @Override
	protected void apply (Robot2016 robot) {
	    group(robot.dashboard.getTrigger("Auton On"), new Coordinator<Robot2016>() {
	        @Override
	        protected void apply (Robot2016 robot) { 
                group(robot.dashboard.getTrigger("Auton Mode A"), new Coordinator<Robot2016>() {
                    @Override
                    protected void apply (Robot2016 robot) {
                        step("Forward", new TriggerMeasure(new TriggerAnd(new TriggerAccess[] {
                                robot.drive.getTrigger("At Left Servo Target"),
                                robot.drive.getTrigger("At Right Servo Target")
                        })), new Coordinator<Robot2016>() {
                            @Override
                            protected void apply (Robot2016 robot) {
                                bind(robot.drive.getAction("Servo Drive"));
                                fill(robot.drive.getAction("Servo Drive"), "Left Clicks", 120);
                                fill(robot.drive.getAction("Servo Drive"), "Right Clicks", 120);
                            }
                        });

                        step("Turn Right", new TriggerMeasure(new TriggerAnd(new TriggerAccess[] {
                                robot.drive.getTrigger("At Left Servo Target"),
                                robot.drive.getTrigger("At Right Servo Target")
                        })), new Coordinator<Robot2016>() {
                            @Override
                            protected void apply (Robot2016 robot) {
                                bind(robot.drive.getAction("Servo Drive"));
                                fill(robot.drive.getAction("Servo Drive"), "Left Clicks", 120);
                                fill(robot.drive.getAction("Servo Drive"), "Right Clicks", -120);
                            }
                        });
                    }
                });

                group(robot.dashboard.getTrigger("Auton Mode B"), new Coordinator<Robot2016>() {
                    @Override
                    protected void apply (Robot2016 robot) {
                        step("Backward", new TriggerMeasure(new TriggerAnd(new TriggerAccess[] {
                                robot.drive.getTrigger("At Left Servo Target"),
                                robot.drive.getTrigger("At Right Servo Target")
                        })), new Coordinator<Robot2016>() {
                            protected void apply (Robot2016 robot) {
                                bind(robot.drive.getAction("Servo Drive"));
                                fill(robot.drive.getAction("Servo Drive"), "Left Clicks", -120);
                                fill(robot.drive.getAction("Servo Drive"), "Right Clicks", -120);
                            }
                        });

                        step("Turn Left", new TriggerMeasure(new TriggerAnd(new TriggerAccess[] {
                                robot.drive.getTrigger("At Left Servo Target"),
                                robot.drive.getTrigger("At Right Servo Target")
                        })), new Coordinator<Robot2016>() {
                            protected void apply (Robot2016 robot) {
                                bind(robot.drive.getAction("Servo Drive"));
                                fill(robot.drive.getAction("Servo Drive"), "Left Clicks", -120);
                                fill(robot.drive.getAction("Servo Drive"), "Right Clicks", 120);
                            }
                        });
                    }
                });
            }
        });
	}
}
