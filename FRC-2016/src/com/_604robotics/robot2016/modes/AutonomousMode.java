/* 
    Autonomous Mode Macros Needed:
	- Options for each defense
	- Options for location will be needed if we plan on shooting
 */

package com._604robotics.robot2016.modes;

import com._604robotics.robot2016.constants.Calibration;
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
        this.bind(new Binding(modules.getModule("Shifter").getAction("High Gear")));
        group(new Group(modules.getModule("Dashboard").getTrigger("Auton On"), new Coordinator() {
            protected void apply (ModuleManager modules) { 
// >>>>>>>> Auton Obstacles Options <<<<<<<< //
                group(new Group(modules.getModule("Dashboard").getTrigger("Default"), new Coordinator() {
                    protected void apply (ModuleManager modules) {
                        step("Forward", new Step(new TriggerMeasure(new TriggerAnd(
                                modules.getModule("Drive").getTrigger("At Move Servo Target")
                        )), new Coordinator() {
                            protected void apply (ModuleManager modules) {
                                this.bind(new Binding(modules.getModule("Drive").getAction("Servo Move")));
                                this.fill(new DataWire(modules.getModule("Drive").getAction("Servo Move"), 
                                        "Clicks", Calibration.AUTON_FORWARD_CLICKS));
                            }
                        }));
                    }
                }));
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
                    			modules.getModule("PneumaticPickup").getTrigger("Is Deployed")
                    	), new Coordinator() {
                    		protected void apply (ModuleManager modules) {
                    			this.bind(new Binding(modules.getModule("PneumaticPickup").getAction("Down")));
                    		}
                    	}));
                    	step("Backward", new Step(new TriggerMeasure(new TriggerAnd(
                                modules.getModule("Drive").getTrigger("At Move Servo Target")
                        )), new Coordinator() {
                            protected void apply (ModuleManager modules) {
                                this.bind(new Binding(modules.getModule("Drive").getAction("Servo Move")));
                                this.fill(new DataWire(modules.getModule("Drive").getAction("Servo Move"), 
                                        "Clicks", Calibration.AUTON_BACKWARD_CLICKS));
                            }
                        }));
                    }
                }));
                group(new Group(modules.getModule("Dashboard").getTrigger("Defense Mode"), new Coordinator() {
                    protected void apply(ModuleManager modules) {
                        step("Rotate", new Step(new TriggerMeasure(new TriggerAnd(
                            modules.getModule("Drive").getTrigger("At Rotate Servo Target")
                        )), new Coordinator() {
                            protected void apply (ModuleManager modules) {
                                this.bind(new Binding(modules.getModule("Drive").getAction("Servo Rotate")));
                                this.fill(new DataWire(modules.getModule("Drive").getAction("Servo Rotate"), "Angle", 180));
                            }
                        }));
                    }
                }));
                group(new Group(modules.getModule("Dashboard").getTrigger("Systems Check: Auton"), new Coordinator() {
                	protected void apply(ModuleManager modules) {
                		step("Test Forward", new Step(new TriggerMeasure(new TriggerAnd(
                				modules.getModule("Drive").getTrigger("At Move Servo Target")
                			)), new Coordinator() {
                				protected void apply (ModuleManager modules) {
                					this.bind(new Binding(modules.getModule("Drive").getAction("Servo Move")));
                					this.fill(new DataWire(modules.getModule("Drive").getAction("Servo Move"), "Clicks", 1000));
                				}
                		}));
                		step("Test Backward", new Step(new TriggerMeasure(new TriggerAnd(
                				modules.getModule("Drive").getTrigger("At Move Trigger Target")
                			)), new Coordinator() {
                				protected void apply (ModuleManager modules) {
                					this.bind(new Binding(modules.getModule("Drive").getAction("Servo Move")));
                					this.fill(new DataWire(modules.getModule("Drive").getAction("Servo Move"), "Clicks", -1000));
                				}
                		}));
                		
                	}
                }));
                group(new Group(modules.getModule("Dashboard").getTrigger("Systems Check: Pickup"), new Coordinator() {
                	protected void apply(ModuleManager modules) {
                		step("Test Deploy", new Step(new TriggerMeasure(new TriggerAnd(
                				modules.getModule("PneumaticPickup").getTrigger("Is Deployed")
                			)), new Coordinator() {
                				protected void apply (ModuleManager modules) {
                					this.bind(new Binding(modules.getModule("PneumaticPickup").getAction("Down")));
                				}
                		}));
                		step("Test Stow", new Step(new TriggerMeasure(new TriggerAnd(
                				modules.getModule("PneumaticPickup").getTrigger("Is Stowed")
                			)), new Coordinator() {
                				protected void apply (ModuleManager modules) {
                					this.bind(new Binding(modules.getModule("PneumaticPickup").getAction("Stow")));
                				}
                		}));
                	}
                }));
                group(new Group(modules.getModule("Dashboard").getTrigger("Systems Check: Shooter"), new Coordinator() {
                	protected void apply(ModuleManager modules) {
                		step("Test Shoot", new Step(new TriggerMeasure(new TriggerAnd(
                				modules.getModule("Shooter").getTrigger("Shot")
                			)), new Coordinator() {
                				protected void apply (ModuleManager modules) {
                					this.bind(new Binding(modules.getModule("Shooter").getAction("Test Shoot")));
                					this.fill(new DataWire(modules.getModule("Shooter").getAction("Test Shoot"), "Duration", 2));
                				}
                		}));
                		step("Test Spit", new Step(new TriggerMeasure(new TriggerAnd(
                				modules.getModule("Shooter").getTrigger("Spat")
                			)), new Coordinator() {
                				protected void apply (ModuleManager modules) {
                					this.bind(new Binding(modules.getModule("Shooter").getAction("Test Spit")));
                					this.fill(new DataWire(modules.getModule("Shooter").getAction("Test Spit"), "Duration", 2));
                				}
                		}));
                	}
                }));
// >>>>>>>> EO Auton Obstacles Options <<<<<<<< //
            }
        }));
    }
}
