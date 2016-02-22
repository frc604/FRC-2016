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
    
    protected void bind (TriggerRecipient recipient) {
        bind(recipient, false);
    }
    
    protected void bind (TriggerRecipient recipient, boolean safety) {
        bind(recipient, TriggerAlways.getInstance(), safety);
    }
    
    protected void bind (TriggerRecipient recipient, TriggerAccess trigger) {
        bind(recipient, trigger, false);
    }
    
    protected void bind (TriggerRecipient recipient, TriggerAccess trigger, boolean safety) {
        triggerBindings.add(new Binding(recipient, trigger, safety));
    }

    protected void fill (DataRecipient recipient, String fieldName, DataAccess data) {
        fill(recipient, fieldName, data, null);
    }
    
    protected void fill (DataRecipient recipient, String fieldName, TriggerAccess trigger) {
        fill(recipient, fieldName, new DataTriggerAdaptor(trigger), null);
    }
    
    protected void fill (DataRecipient recipient, String fieldName, TriggerAccess trigger, TriggerAccess activator) {
        fill(recipient, fieldName, new DataTriggerAdaptor(trigger), activator);
    }
    
    protected void fill (DataRecipient recipient, String fieldName, double value) {
        fill(recipient, fieldName, value, null);
    }
    
    protected void fill (DataRecipient recipient, String fieldName, double value, TriggerAccess activator) {
        fill(recipient, fieldName, new ConstData(value), activator);
    }
    
    protected void fill (DataRecipient recipient, String fieldName, boolean value) {
        fill(recipient, fieldName, value, null);
    }
    
    protected void fill (DataRecipient recipient, String fieldName, boolean value, TriggerAccess activator) {
        fill(recipient, fieldName, new ConstData(value), activator);
    }
    
    protected void fill (DataRecipient recipient, String fieldName, DataAccess data, TriggerAccess activator) {
        dataWires.add(new DataWire(recipient, fieldName, data, activator));
    }
    
    protected void group (TriggerAccess trigger, Coordinator coordinator) {
        groups.add(new Group(trigger, coordinator));
    }
    
    protected void step (String name, Coordinator coordinator) {
        step(name, (Measure) null, coordinator);
    }
    
    protected void step (String name, TriggerAccess trigger, Coordinator coordinator) {
        step(name, new TriggerMeasure(trigger), coordinator);
    }
    
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
