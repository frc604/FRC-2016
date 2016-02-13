package com._604robotics.robotnik.coordinator;

import java.util.Enumeration;
import java.util.Vector;

import com._604robotics.robotnik.Robot;
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
import com._604robotics.robotnik.data.sources.DataTriggerAdapter;
import com._604robotics.robotnik.prefabs.measure.TriggerMeasure;
import com._604robotics.robotnik.prefabs.trigger.TriggerAlways;
import com._604robotics.robotnik.trigger.TriggerAccess;
import com._604robotics.robotnik.trigger.TriggerRecipient;

public class Coordinator<T extends Robot<T>> {
    private final Vector triggerBindings = new Vector();
    private final Vector dataWires = new Vector();

    private final GroupManager<T> groups = new GroupManager();
    private final StepManager<T> steps = new StepManager();
    
    protected void apply (T robot) {}
    
    public void attach (T robot) {
        this.triggerBindings.removeAllElements();
        this.dataWires.removeAllElements();

        this.groups.clear();
        this.steps.clear();
        
        this.apply(robot);

        this.groups.attach(robot);
        this.steps.attach(robot);
    }
    
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
        this.triggerBindings.addElement(new Binding(recipient, trigger, safety));
    }

    protected void fill (DataRecipient recipient, String fieldName, DataAccess data) {
        fill(recipient, fieldName, data, null);
    }
    
    protected void fill (DataRecipient recipient, String fieldName, TriggerAccess trigger) {
        fill(recipient, fieldName, new DataTriggerAdapter(trigger), null);
    }
    
    protected void fill (DataRecipient recipient, String fieldName, TriggerAccess trigger, TriggerAccess activator) {
        fill(recipient, fieldName, new DataTriggerAdapter(trigger), activator);
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
        this.dataWires.addElement(new DataWire(recipient, fieldName, data, activator));
    }
    
    protected void group (TriggerAccess trigger, Coordinator<T> coordinator) {
        this.groups.add(new Group<T>(trigger, coordinator));
    }
    
    protected void step (String name, Coordinator<T> coordinator) {
        step(name, (Measure) null, coordinator);
    }
    
    protected void step (String name, TriggerAccess trigger, Coordinator<T> coordinator) {
        step(name, new TriggerMeasure(trigger), coordinator);
    }
    
    protected void step (String name, Measure measure, Coordinator<T> coordinator) {
        this.steps.add(name, new Step<T>(measure, coordinator));
    }
    
    public void update () {
        final Enumeration wires = this.dataWires.elements();
        while (wires.hasMoreElements()) ((DataWire) wires.nextElement()).conduct();
        
        final Enumeration bindings = this.triggerBindings.elements();
        while (bindings.hasMoreElements()) ((Binding) bindings.nextElement()).conduct();

        this.groups.update();
        this.steps.update();
    }

    public void stop () {
        this.groups.stop();
        this.steps.stop();
    }
}
