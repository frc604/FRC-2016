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

public class AutonomousMode extends Coordinator
{
    public AutonomousMode()
    {
    	
    }
    protected void apply(ModuleManager modules)
    {
    	// If 'Auton On' is on, Enable
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
    }
}
