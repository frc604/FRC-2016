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

public class AutonomousMode extends Coordinator {
	public AutonomousMode (Robot2016 robot) {
	    group(robot.dashboard.getTrigger("Auton On"), new Coordinator() {{
            group(robot.dashboard.getTrigger("Auton Mode A"), new Coordinator() {{
                step("Forward", new TriggerAnd(new TriggerAccess[] {
                        robot.drive.getTrigger("At Left Servo Target"),
                        robot.drive.getTrigger("At Right Servo Target")
                }), new Coordinator() {{
                    bind(robot.drive.getAction("Servo Drive"));
                    fill(robot.drive.getAction("Servo Drive"), "Left Clicks", 120);
                    fill(robot.drive.getAction("Servo Drive"), "Right Clicks", 120);
                }});

                step("Turn Right", new TriggerMeasure(new TriggerAnd(new TriggerAccess[] {
                        robot.drive.getTrigger("At Left Servo Target"),
                        robot.drive.getTrigger("At Right Servo Target")
                })), new Coordinator() {{
                    bind(robot.drive.getAction("Servo Drive"));
                    fill(robot.drive.getAction("Servo Drive"), "Left Clicks", 120);
                    fill(robot.drive.getAction("Servo Drive"), "Right Clicks", -120);
                }});
            }});

            group(robot.dashboard.getTrigger("Auton Mode B"), new Coordinator() {{
                step("Backward", new TriggerAnd(new TriggerAccess[] {
                        robot.drive.getTrigger("At Left Servo Target"),
                        robot.drive.getTrigger("At Right Servo Target")
                }), new Coordinator() {{
                    bind(robot.drive.getAction("Servo Drive"));
                    fill(robot.drive.getAction("Servo Drive"), "Left Clicks", -120);
                    fill(robot.drive.getAction("Servo Drive"), "Right Clicks", -120);
                }});

                step("Turn Left", new TriggerAnd(new TriggerAccess[] {
                        robot.drive.getTrigger("At Left Servo Target"),
                        robot.drive.getTrigger("At Right Servo Target")
                }), new Coordinator() {{
                    bind(robot.drive.getAction("Servo Drive"));
                    fill(robot.drive.getAction("Servo Drive"), "Left Clicks", -120);
                    fill(robot.drive.getAction("Servo Drive"), "Right Clicks", 120);
                }});
            }});
        }});
	}
}
