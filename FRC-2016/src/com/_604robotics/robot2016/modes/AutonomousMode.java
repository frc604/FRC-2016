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
    	
    }/*
    protected void apply(ModuleManager modules)
    {
    	{
        this.bind(new Binding(modules.getModule("Drive").getAction("Servo Drive"), new TriggerAnd(new TriggerAccess[] {
        		modules.getModule("Dashboard").getTrigger("Auton On"),
        		modules.getModule("Dashboard").getTrigger("Forwards")})));
        this.fill(new DataWire(modules.getModule("Drive").getAction("Servo Drive"), "left clicks", 300));
        this.fill(new DataWire(modules.getModule("Drive").getAction("Servo Drive"), "right clicks", 300));
        this.bind(new Binding(modules.getModule("Drive").getAction("Servo Drive"), new TriggerAnd(new TriggerAccess[] {
        		modules.getModule("Dashboard").getTrigger("Auton On"),
        		modules.getModule("Dashboard").getTrigger("Right")})));
        this.fill(new DataWire(modules.getModule("Drive").getAction("Servo Drive"), "left clicks", 300));
        this.fill(new DataWire(modules.getModule("Drive").getAction("Servo Drive"), "right clicks", -300));
        
        this.bind(new Binding(modules.getModule("Drive").getAction("Servo Drive"), new TriggerAnd(new TriggerAccess[] {
        		modules.getModule("Dashboard").getTrigger("Auton On"),
        		modules.getModule("Dashboard").getTrigger("Left")})));
        this.fill(new DataWire(modules.getModule("Drive").getAction("Servo Drive"), "left clicks", -300));
        this.fill(new DataWire(modules.getModule("Drive").getAction("Servo Drive"), "right clicks", 300));
        this.bind(new Binding(modules.getModule("Drive").getAction("Servo Drive"), new TriggerAnd(new TriggerAccess[] {
        		modules.getModule("Dashboard").getTrigger("Auton On"),
        		modules.getModule("Dashboard").getTrigger("Forwards")})));
        this.fill(new DataWire(modules.getModule("Drive").getAction("Servo Drive"), "left clicks", 300));
        this.fill(new DataWire(modules.getModule("Drive").getAction("Servo Drive"), "right clicks", 300));
    	
        this.bind(new Binding(modules.getModule("Drive").getAction("Servo Drive"), new TriggerAnd(new TriggerAccess[] {
        		modules.getModule("Dashboard").getTrigger("Auton On"),
        		modules.getModule("Dashboard").getTrigger("Right")})));
        this.fill(new DataWire(modules.getModule("Drive").getAction("Servo Drive"), "left clicks", 300));
        this.fill(new DataWire(modules.getModule("Drive").getAction("Servo Drive"), "right clicks", -300));
        
        this.bind(new Binding(modules.getModule("Drive").getAction("Servo Drive"), new TriggerAnd(new TriggerAccess[] {
        		modules.getModule("Dashboard").getTrigger("Auton On"),
        		modules.getModule("Dashboard").getTrigger("Left")})));
        this.fill(new DataWire(modules.getModule("Drive").getAction("Servo Drive"), "left clicks", -300));
        this.fill(new DataWire(modules.getModule("Drive").getAction("Servo Drive"), "right clicks", 300));
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
/*
    	add("Enable", new Step(new TriggerMeasure(modules.getModule("Dashboard").getTrigger("Auton On")), new Coordinator()));
    	
    	add("Left", new Step(new TriggerMeasure(new TriggerOr(new TriggerAccess[] {
    			modules.getModule("Dashboard").getTrigger("Test Left"),
    				new TriggerAnd(new TriggerAccess[] {
    						modules.getModule("Drive").getTrigger("At Left Servo Target"),
    						modules.getModule("Drive").getTrigger("At Right Servo Target")})
    	})), new Coordinator() {
    		protected void apply (ModuleManager modules) {
    			this.bind(new Binding(modules.getModule("Drive").getAction("Servo Drive")));
    			this.fill(new DataWire(modules.getModule("Drive").getAction("Servo Drive"), "left clicks", -120));
    			this.fill(new DataWire(modules.getModule("Drive").getAction("Servo Drive"), "right clicks", 0));
    		}
    	}));
    	
    	add("Forward", new Step(new TriggerMeasure(new TriggerOr(new TriggerAccess[] {
    			modules.getModule("Dashboard").getTrigger("Towards Dawn"),
    				new TriggerAnd(new TriggerAccess[] {
    						modules.getModule("Drive").getTrigger("At Left Servo Target"),
    						modules.getModule("Drive").getTrigger("At Right Servo Target")})
    	})), new Coordinator() {
    		protected void apply (ModuleManager modules) {
    			this.bind(new Binding(modules.getModule("Drive").getAction("Servo Drive")));
    			this.fill(new DataWire(modules.getModule("Drive").getAction("Servo Drive"), "left clicks", 120));
    			this.fill(new DataWire(modules.getModule("Drive").getAction("Servo Drive"), "right clicks", 120));
    		}
    	}));
    	
    	add("Right", new Step(new TriggerMeasure(new TriggerOr(new TriggerAccess[] {
    			modules.getModule("Dashboard").getTrigger("Test Right"),
    				new TriggerAnd(new TriggerAccess[] {
    						modules.getModule("Drive").getTrigger("At Left Servo Target"),
    						modules.getModule("Drive").getTrigger("At Right Servo Target")})
    	})), new Coordinator() {
    		protected void apply (ModuleManager modules) {
    			this.bind(new Binding(modules.getModule("Drive").getAction("Servo Drive")));
    			this.fill(new DataWire(modules.getModule("Drive").getAction("Servo Drive"), "left clicks", 0));
    			this.fill(new DataWire(modules.getModule("Drive").getAction("Servo Drive"), "right clicks", 120));
    		}
    	}));
    	*/
    	/*
    	add("Forward", new Step(new TriggerMeasure(new TriggerOr(new TriggerAccess[] {
    			modules.getModule("Dashboard").getTrigger("Drive Only"),
    				new TriggerAnd(new TriggerAccess[] {
    						modules.getModule("Drive").getTrigger("At Left Servo Target"),
    						modules.getModule("Drive").getTrigger("At Right Servo Target")})
    	})), new Coordinator() {
    		protected void apply (ModuleManager modules) {
    			this.bind(new Binding(modules.getModule("Drive").getAction("Servo Drive")));
    			this.fill(new DataWire(modules.getModule("Drive").getAction("Servo Drive"), "left clicks", <Duration [Ticks]>));
    			this.fill(new DataWire(modules.getModule("Drive").getAction("Servo Drive"), "right clicks", <Duration [Ticks]>));
    		}
    	}));
    	*/
    	/*
    	add("Forward", new Step(new TriggerMeasure(new TriggerOr(new TriggerAccess[] {
    			modules.getModule("Dashboard").getTrigger("Drive Only"),
    				new TriggerAnd(new TriggerAccess[] {
    						modules.getModule("Drive").getTrigger("At Left Servo Target"),
    						modules.getModule("Drive").getTrigger("At Right Servo Target")})
    	})), new Coordinator() {
    		protected void apply (ModuleManager modules) {
    			this.bind(new Binding(modules.getModule("Drive").getAction("Servo Drive")));
    			this.fill(new DataWire(modules.getModule("Drive").getAction("Servo Drive"), "left clicks", <Duration [Ticks]>));
    			this.fill(new DataWire(modules.getModule("Drive").getAction("Servo Drive"), "right clicks", <Duration [Ticks]>));
    		}
    	}));
    	*/
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
		}
		}));
	}
}
