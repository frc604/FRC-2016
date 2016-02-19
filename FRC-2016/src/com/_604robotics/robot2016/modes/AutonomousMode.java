/* Autonomous Mode Macros Needed:
	- Options for each defense
		- Manipulate gate, drawbridge, etc
	- Options for location will be needed if we plan on shooting
*/
package com._604robotics.robot2016.modes;

import com._604robotics.robotnik.coordinator.Coordinator;
import com._604robotics.robotnik.coordinator.connectors.Binding;
import com._604robotics.robotnik.coordinator.connectors.DataWire;
import com._604robotics.robotnik.coordinator.steps.Step;
import com._604robotics.robotnik.module.ModuleManager;
import com._604robotics.robotnik.prefabs.measure.TriggerMeasure;
import com._604robotics.robotnik.prefabs.trigger.TriggerAnd;
import com._604robotics.robotnik.prefabs.trigger.TriggerNot;
import com._604robotics.robotnik.prefabs.trigger.TriggerOr;
import com._604robotics.robotnik.prefabs.trigger.TriggerToggle;
import com._604robotics.robotnik.trigger.TriggerAccess;
import com._604robotics.robotnik.coordinator.groups.Group;
import com._604robotics.robotnik.coordinator.groups.GroupManager;

public class AutonomousMode extends Coordinator
{

    public AutonomousMode()
    {
    	
    }
    
	protected void apply(ModuleManager modules)
	{
		/* bind/wire things common to all auton modes outside the Groups */

		group(new Group(modules.getModule("Dashboard").getTrigger("Auton Mode A"), new Coordinator() {
			protected void apply(ModuleManager modules) {
				/* bind/wire things common to all steps of Mode A outside the Steps */

				step("Forward", new Step(new TriggerMeasure(new TriggerAnd(new TriggerAccess[] {
		    			modules.getModule("Drive").getTrigger("At Left Servo Target"),
		    			modules.getModule("Drive").getTrigger("At Right Servo Target")
		    	})), new Coordinator() {
		    		protected void apply (ModuleManager modules) {
		    			this.bind(new Binding(modules.getModule("Drive").getAction("Servo Drive")));
		    			this.fill(new DataWire(modules.getModule("Drive").getAction("Servo Drive"), "left clicks", 120));
		    			this.fill(new DataWire(modules.getModule("Drive").getAction("Servo Drive"), "right clicks", 120));
		    		}
		    	}));
				step("Turn Right", new Step(new TriggerMeasure(new TriggerAnd(new TriggerAccess[] {
						modules.getModule("Drive").getTrigger("At Left Servo Target"),
		    			modules.getModule("Drive").getTrigger("At Right Servo Target")
		    	})), new Coordinator() {
		    		protected void apply (ModuleManager modules) {
		    			this.bind(new Binding(modules.getModule("Drive").getAction("Servo Drive")));
		    			this.fill(new DataWire(modules.getModule("Drive").getAction("Servo Drive"), "left clicks", 120));
		    			this.fill(new DataWire(modules.getModule("Drive").getAction("Servo Drive"), "right clicks", -120));
		    		}
		    	}));
		}
	}));

		group(new Group(modules.getModule("Dashboard").getTrigger("Auton Mode B"), new Coordinator() {
			protected void apply(ModuleManager modules) {
				/* bind/wire things common to all steps of Mode B outside the Steps */

				step("Backward", new Step(new TriggerMeasure(new TriggerAnd(new TriggerAccess[] {
						modules.getModule("Drive").getTrigger("At Left Servo Target"),
		    			modules.getModule("Drive").getTrigger("At Right Servo Target")
		    	})), new Coordinator() {
		    		protected void apply (ModuleManager modules) {
		    			this.bind(new Binding(modules.getModule("Drive").getAction("Servo Drive")));
		    			this.fill(new DataWire(modules.getModule("Drive").getAction("Servo Drive"), "left clicks", -120));
		    			this.fill(new DataWire(modules.getModule("Drive").getAction("Servo Drive"), "right clicks", -120));
		    		}
		    	}));
				step("Turn Left", new Step(new TriggerMeasure(new TriggerAnd(new TriggerAccess[] {
						modules.getModule("Drive").getTrigger("At Left Servo Target"),
		    			modules.getModule("Drive").getTrigger("At Right Servo Target")
		    	})), new Coordinator() {
		    		protected void apply (ModuleManager modules) {
		    			this.bind(new Binding(modules.getModule("Drive").getAction("Servo Drive")));
		    			this.fill(new DataWire(modules.getModule("Drive").getAction("Servo Drive"), "left clicks", -120));
		    			this.fill(new DataWire(modules.getModule("Drive").getAction("Servo Drive"), "right clicks", 120));
		    		}
		    	}));
		}
		}));
	}
}
