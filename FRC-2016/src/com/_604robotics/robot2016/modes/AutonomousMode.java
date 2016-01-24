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

public class AutonomousMode extends Procedure
{
    public AutonomousMode()
    {
    	
    }
    protected void apply(ModuleManager modules)
    {
    	{
    		add("Enable", new Step(new TriggerMeasure(modules.getModule("Dashboard").getTrigger("Auton On")), new Coordinator()));
    		
    		add("Forward", new Step(new TriggerMeasure(new TriggerOr(new TriggerAccess[] {
    			modules.getModule("Dashboard").getTrigger("Backward")
    		})), new Coordinator() {
    			protected void apply (ModuleManager modules) {
    				this.bind(new Binding(modules.getModule("Drive").getAction("Servo Drive")));
    				this.fill(new DataWire(modules.getModule("Drive").getAction("Servo Drive"), "left clicks", 300));
    				this.fill(new DataWire(modules.getModule("Drive").getAction("Servo Drive"), "right clicks", 300));
    			}
    		}));
    		
    		add("Moar Forward", new Step(new TriggerMeasure(new TriggerOr(new TriggerAccess[] {
        			modules.getModule("Dashboard").getTrigger("Backward")
        		})), new Coordinator() {
        			protected void apply (ModuleManager modules) {
        				this.bind(new Binding(modules.getModule("Drive").getAction("Servo Drive")));
        				this.fill(new DataWire(modules.getModule("Drive").getAction("Servo Drive"), "left clicks", 900));
        				this.fill(new DataWire(modules.getModule("Drive").getAction("Servo Drive"), "right clicks", 900));
        			}
        		}));
    		
    	}
    }
}
