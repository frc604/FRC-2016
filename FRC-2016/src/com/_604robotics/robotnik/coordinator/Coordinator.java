package com._604robotics.robotnik.coordinator;

import java.util.ArrayList;
import java.util.List;

import com._604robotics.robotnik.coordinator.connectors.Binding;
import com._604robotics.robotnik.coordinator.connectors.DataWire;
import com._604robotics.robotnik.coordinator.groups.Group;
import com._604robotics.robotnik.coordinator.groups.GroupManager;
import com._604robotics.robotnik.coordinator.steps.Measure;
import com._604robotics.robotnik.coordinator.steps.Step;
import com._604robotics.robotnik.coordinator.steps.StepManager;
import com._604robotics.robotnik.data.DataAccess;
import com._604robotics.robotnik.data.DataRecipient;
import com._604robotics.robotnik.data.sources.ConstData;
import com._604robotics.robotnik.data.sources.DataTriggerAdaptor;
import com._604robotics.robotnik.prefabs.measure.TriggerMeasure;
import com._604robotics.robotnik.prefabs.trigger.TriggerAlways;
import com._604robotics.robotnik.trigger.TriggerAccess;
import com._604robotics.robotnik.trigger.TriggerRecipient;

/**
 * Coordinates the flow of data and bindings, and the execution of groups and steps.
 */
public class Coordinator {
    private final List<Binding> triggerBindings = new ArrayList<Binding>();
    private final List<DataWire> dataWires = new ArrayList<DataWire>();
    
    private final GroupManager groups = new GroupManager();
    private final StepManager steps = new StepManager();
    
    /**
     * Binds a trigger recipient to always be on.
     * @param recipient Recipient of the trigger.
     */
    protected void bind (TriggerRecipient recipient) {
        bind(recipient, false);
    }
    
    /**
     * Binds a trigger recipient to always be triggered.
     * @param recipient Recipient of the trigger.
     */
    protected void bind (TriggerRecipient recipient, boolean safety) {
        bind(recipient, TriggerAlways.getInstance(), safety);
    }
    
    /**
     * Binds a trigger recipient to a trigger.
     * @param recipient Recipient of the trigger.
     * @param trigger Trigger to bind.
     */
    protected void bind (TriggerRecipient recipient, TriggerAccess trigger) {
        bind(recipient, trigger, false);
    }
    
    /**
     * Binds a trigger recipient to a trigger.
     * @param recipient Recipient of the trigger.
     * @param trigger Trigger to bind.
     * @param safety Whether the binding should have precedence for safety.
     */
    protected void bind (TriggerRecipient recipient, TriggerAccess trigger, boolean safety) {
        triggerBindings.add(new Binding(recipient, trigger, safety));
    }

    /**
     * Wires a data recipient to be filled with data.
     * @param recipient Recipient of the data.
     * @param fieldName Name of the data field.
     * @param data Data to fill with.
     */
    protected void fill (DataRecipient recipient, String fieldName, DataAccess data) {
        fill(recipient, fieldName, data, null);
    }
    
    /**
     * Wires a data recipient to be filled with data when a trigger is active.
     * @param recipient Recipient of the data.
     * @param fieldName Name of the data field.
     * @param data Data to fill with.
     * @param activator Trigger activating the transfer.
     */
    protected void fill (DataRecipient recipient, String fieldName, DataAccess data, TriggerAccess activator) {
        dataWires.add(new DataWire(recipient, fieldName, data, activator));
    }
    
    /**
     * Wires a data recipient to be filled with a trigger.
     * @param recipient Recipient of the data.
     * @param fieldName Name of the data field.
     * @param trigger Trigger to fill with.
     */
    protected void fill (DataRecipient recipient, String fieldName, TriggerAccess trigger) {
        fill(recipient, fieldName, new DataTriggerAdaptor(trigger), null);
    }
    
    /**
     * Wires a data recipient to be filled with a trigger when another is active.
     * @param recipient Recipient of the data.
     * @param fieldName Name of the data field.
     * @param trigger Trigger to fill with.
     * @param activator Trigger activating the transfer.
     */
    protected void fill (DataRecipient recipient, String fieldName, TriggerAccess trigger, TriggerAccess activator) {
        fill(recipient, fieldName, new DataTriggerAdaptor(trigger), activator);
    }
    
    /**
     * Wires a data recipient to be filled with a constant double value.
     * @param recipient Recipient of the data.
     * @param fieldName Name of the data field.
     * @param value Constant value to fill with.
     */
    protected void fill (DataRecipient recipient, String fieldName, double value) {
        fill(recipient, fieldName, value, null);
    }
    
    /**
     * Wires a data recipient to be filled with a constant double value when a trigger is active.
     * @param recipient Recipient of the data.
     * @param fieldName Name of the data field.
     * @param value Constant value to fill with.
     * @param activator Trigger activating the transfer.
     */
    protected void fill (DataRecipient recipient, String fieldName, double value, TriggerAccess activator) {
        fill(recipient, fieldName, new ConstData(value), activator);
    }
    
    /**
     * Wires a data recipient to be filled with a constant boolean value.
     * @param recipient Recipient of the data.
     * @param fieldName Name of the data field.
     * @param value Constant value to fill with.
     */
    protected void fill (DataRecipient recipient, String fieldName, boolean value) {
        fill(recipient, fieldName, value, null);
    }
    
    /**
     * Wires a data recipient to be filled with a constant boolean value when a trigger is active.
     * @param recipient Recipient of the data.
     * @param fieldName Name of the data field.
     * @param value Constant value to fill with.
     * @param activator Trigger activating the transfer.
     */
    protected void fill (DataRecipient recipient, String fieldName, boolean value, TriggerAccess activator) {
        fill(recipient, fieldName, new ConstData(value), activator);
    }
   
    /**
     * Adds a group.
     * @param trigger Trigger activating the group.
     * @param coordinator Coordinator driving the group.
     */
    protected void group (TriggerAccess trigger, Coordinator coordinator) {
        groups.add(new Group(trigger, coordinator));
    }
    
    /**
     * Adds a step.
     * @param name Name of the step.
     * @param coordinator Coordinator driving the step.
     */
    protected void step (String name, Coordinator coordinator) {
        step(name, (Measure) null, coordinator);
    }
    
    /**
     * Adds a step.
     * @param name Name of the step.
     * @param trigger Trigger activating the step.
     * @param coordinator Coordinator driving the step.
     */
    protected void step (String name, TriggerAccess trigger, Coordinator coordinator) {
        step(name, new TriggerMeasure(trigger), coordinator);
    }
    
    /**
     * Adds a step.
     * @param name Name of the step.
     * @param measure Measure controlling the execution of the step.
     * @param coordinator Coordinator driving the step.
     */
    protected void step (String name, Measure measure, Coordinator coordinator) {
        steps.add(name, new Step(measure, coordinator));
    }
    
    /**
     * Updates the coordinator.
     */
    public void update () {
        for (DataWire wire : this.dataWires) {
            wire.conduct();
        }
        
        for (Binding binding : this.triggerBindings) {
            binding.conduct();
        }

        this.groups.update();
        this.steps.update();
    }

    /**
     * Stops the coordinator's execution.
     */
    public void stop () {
        this.groups.stop();
        this.steps.stop();
    }
    
    /**
     * Gets whether the coordinator has completed execution.
     * @return Whether the coordinator is complete.
     */
    public boolean complete () {
        return this.steps.complete() && this.groups.complete();
    }
}
