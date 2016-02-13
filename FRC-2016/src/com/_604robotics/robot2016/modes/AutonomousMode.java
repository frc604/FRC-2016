/* Autonomous Mode Macros Needed:
	- Options for each defense
		- Manipulate gate, drawbridge, etc
	- Options for location will be needed if we plan on shooting
*/
package com._604robotics.robot2016.modes;

import com._604robotics.robot2016.Robot2016;
import com._604robotics.robotnik.coordinator.Coordinator;
import com._604robotics.robotnik.coordinator.connectors.Binding;
import com._604robotics.robotnik.coordinator.connectors.DataWire;
import com._604robotics.robotnik.coordinator.groups.Group;
import com._604robotics.robotnik.coordinator.steps.Step;
import com._604robotics.robotnik.module.ModuleManager;
import com._604robotics.robotnik.prefabs.measure.TriggerMeasure;
import com._604robotics.robotnik.prefabs.trigger.TriggerAnd;
import com._604robotics.robotnik.trigger.TriggerAccess;

public class AutonomousMode extends Coordinator<Robot2016> {
    @Override
	protected void apply (Robot2016 robot) {
		/* bind/wire things common to all auton modes outside the Groups */

		group(new Group<>(robot.dashboard.getTrigger("Auton Mode A"), new Coordinator<Robot2016>() {
		    @Override
			protected void apply(Robot2016 robot) {
				step("Forward", new Step<>(new TriggerMeasure(new TriggerAnd(new TriggerAccess[] {
		    			robot.drive.getTrigger("At Left Servo Target"),
		    			robot.drive.getTrigger("At Right Servo Target")
		    	})), new Coordinator<Robot2016>() {
				    @Override
		    		protected void apply (Robot2016 robot) {
		    			this.bind(new Binding(robot.drive.getAction("Servo Drive")));
		    			this.fill(new DataWire(robot.drive.getAction("Servo Drive"), "left clicks", 120));
		    			this.fill(new DataWire(robot.drive.getAction("Servo Drive"), "right clicks", 120));
		    		}
		    	}));

				step("Backward", new Step<>(new TriggerMeasure(new TriggerAnd(new TriggerAccess[] {
						robot.drive.getTrigger("At Left Servo Target"),
		    			robot.drive.getTrigger("At Right Servo Target")
		    	})), new Coordinator<Robot2016>() {
		    		protected void apply (Robot2016 robot) {
		    			this.bind(new Binding(robot.drive.getAction("Servo Drive")));
		    			this.fill(new DataWire(robot.drive.getAction("Servo Drive"), "left clicks", -120));
		    			this.fill(new DataWire(robot.drive.getAction("Servo Drive"), "right clicks", -120));
		    		}
		    	}));
            }
        }));

		group(new Group<>(robot.dashboard.getTrigger("Auton Mode B"), new Coordinator<Robot2016>() {
		    @Override
			protected void apply (Robot2016 robot) {
				/* bind/wire things common to all steps of Mode B outside the Steps */

				step("Backward", new Step<>(new TriggerMeasure(new TriggerAnd(new TriggerAccess[] {
						robot.drive.getTrigger("At Left Servo Target"),
		    			robot.drive.getTrigger("At Right Servo Target")
		    	})), new Coordinator<Robot2016>() {
		    		protected void apply (Robot2016 robot) {
		    			this.bind(new Binding(robot.drive.getAction("Servo Drive")));
		    			this.fill(new DataWire(robot.drive.getAction("Servo Drive"), "left clicks", -120));
		    			this.fill(new DataWire(robot.drive.getAction("Servo Drive"), "right clicks", -120));
		    		}
		    	}));
            }
		}));
	}
    
    /*
    protected void apply(ModuleManager robot)
    {
    	{
    		add("Enable", new Step(new TriggerMeasure(robot.dashboard.getTrigger("Auton On")), new Coordinator()));
    		
    		add("Forward", new Step(new TriggerMeasure(new TriggerOr(new TriggerAccess[] {
    			robot.dashboard.getTrigger("Backward")
    		})), new Coordinator() {
    			protected void apply (ModuleManager robot) {
    				this.bind(new Binding(robot.drive.getAction("Servo Drive")));
    				this.fill(new DataWire(robot.drive.getAction("Servo Drive"), "left clicks", 300));
    				this.fill(new DataWire(robot.drive.getAction("Servo Drive"), "right clicks", 300));
    			}
    		}));
    		
    		add("Moar Forward", new Step(new TriggerMeasure(new TriggerOr(new TriggerAccess[] {
        			robot.dashboard.getTrigger("Backward")
        		})), new Coordinator() {
        			protected void apply (ModuleManager robot) {
        				this.bind(new Binding(robot.drive.getAction("Servo Drive")));
        				this.fill(new DataWire(robot.drive.getAction("Servo Drive"), "left clicks", 900));
        				this.fill(new DataWire(robot.drive.getAction("Servo Drive"), "right clicks", 900));
        			}
        		}));
    		
    	}
    }
    */
}
