/* 
    Autonomous Mode Macros Needed:
	- Options for each defense
	- Options for location will be needed if we plan on shooting
 */

package com._604robotics.robot2016.modes;

import com._604robotics.robotnik.coordinator.Coordinator;
import com._604robotics.robotnik.coordinator.connectors.Binding;
import com._604robotics.robotnik.coordinator.connectors.DataWire;
import com._604robotics.robotnik.coordinator.groups.Group;
import com._604robotics.robotnik.coordinator.steps.Step;
import com._604robotics.robotnik.module.ModuleManager;
import com._604robotics.robotnik.prefabs.measure.TriggerMeasure;
import com._604robotics.robotnik.prefabs.trigger.TriggerAnd;

public class AutonomousMode extends Coordinator {
    protected void apply (ModuleManager modules) {
        this.bind(new Binding(modules.getModule("Shifter").getAction("Low Gear")));
        group(new Group(modules.getModule("Dashboard").getTrigger("Auton Obstacle"), new Coordinator() {
            protected void apply (ModuleManager modules) { 
// >>>>>>>> Auton Obstacles <<<<<<<< //
                group(new Group(modules.getModule("Dashboard").getTrigger("Lowbar"), new Coordinator() {
                    protected void apply(ModuleManager modules) {
                    	step("Rotate", new Step(new TriggerMeasure(new TriggerAnd(
                                modules.getModule("Drive").getTrigger("At Rotate Servo Target")
                        )), new Coordinator() {
                            protected void apply (ModuleManager modules) {
                                this.bind(new Binding(modules.getModule("Drive").getAction("Servo Rotate")));
                                this.fill(new DataWire(modules.getModule("Drive").getAction("Servo Rotate"), "Angle", 180));
                            }
                        }));
                    	step("Deploy", new Step(new TriggerMeasure(
                    			modules.getModule("Pickup").getTrigger("On Deploy Target")
                    	), new Coordinator() {
                    		protected void apply (ModuleManager modules) {
                    			this.bind(new Binding(modules.getModule("Pickup").getAction("Deploy")));
                    		}
                    	}));
                    	step("Backward", new Step(new TriggerMeasure(new TriggerAnd(
                                modules.getModule("Drive").getTrigger("At Move Servo Target")
                        )), new Coordinator() {
                            protected void apply (ModuleManager modules) {
                                this.bind(new Binding(modules.getModule("Drive").getAction("Servo Move")));
                                this.fill(new DataWire(modules.getModule("Drive").getAction("Servo Move"), "Clicks", -2200));
                            }
                        }));
                    }
                }));
                group(new Group(modules.getModule("Dashboard").getTrigger("Everything Else"), new Coordinator() {
                    protected void apply (ModuleManager modules) {
                        step("Forward", new Step(new TriggerMeasure(new TriggerAnd(
                                modules.getModule("Drive").getTrigger("At Move Servo Target")
                        )), new Coordinator() {
                            protected void apply (ModuleManager modules) {
                                this.bind(new Binding(modules.getModule("Drive").getAction("Servo Move")));
                                this.fill(new DataWire(modules.getModule("Drive").getAction("Servo Move"), "Clicks", 2200));
                            }
                        }));
                    }
                }));
// >>>>>>>> EO Auton Obstacles Options <<<<<<<< //
                
// >>>>>>>> Auton Modes <<<<<<<< //
/*
                group(new Group(modules.getModule("Dashboard").getTrigger("Auton: Defense Mode"), new Coordinator() {
                    protected void apply(ModuleManager modules) {
                        step("Rotate", new Step(new TriggerMeasure(new TriggerAnd(
                            modules.getModule("Drive").getTrigger("At Rotate Servo Target")
                        )), new Coordinator() {
                            protected void apply (ModuleManager modules) {
                                this.bind(new Binding(modules.getModule("Drive").getAction("Servo Rotate")));
                                this.fill(new DataWire(modules.getModule("Drive").getAction("Servo Rotate), "Angle", 180));
                            }
                        }));
                    }
                }));
*/
                // >>>>>>>> EO Auton Mode Options <<<<<<<< //
            }
        }));
        group(new Group(modules.getModule("Dashboard").getTrigger("Auton Retreat"), new Coordinator() {
            protected void apply (ModuleManager modules) { 
                step("Rotate", new Step(new TriggerMeasure(new TriggerAnd(
                        modules.getModule("Drive").getTrigger("At Rotate Servo Target")
                )), new Coordinator() {
                    protected void apply (ModuleManager modules) {
                        this.bind(new Binding(modules.getModule("Drive").getAction("Servo Rotate")));
                        this.fill(new DataWire(modules.getModule("Drive").getAction("Servo Rotate"), "Angle", 180));
                    }
                }));
                step("Forward", new Step(new TriggerMeasure(new TriggerAnd(
                        modules.getModule("Drive").getTrigger("At Move Servo Target")
                )), new Coordinator() {
                    protected void apply (ModuleManager modules) {
                        this.bind(new Binding(modules.getModule("Drive").getAction("Servo Move")));
                        this.fill(new DataWire(modules.getModule("Drive").getAction("Servo Move"), "Clicks", 2200));
                    }
                }));
            }
        }));
    }
}
