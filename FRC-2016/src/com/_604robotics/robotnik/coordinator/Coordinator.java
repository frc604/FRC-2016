package com._604robotics.robotnik.coordinator;

import com._604robotics.robotnik.ConnectorProxy;
import com._604robotics.robotnik.coordinator.connectors.Binding;
import com._604robotics.robotnik.coordinator.connectors.DataWire;
import com._604robotics.robotnik.coordinator.groups.Group;
import com._604robotics.robotnik.coordinator.groups.GroupManager;
import com._604robotics.robotnik.coordinator.steps.Step;
import com._604robotics.robotnik.coordinator.steps.StepManager;
import com._604robotics.robotnik.module.ModuleManager;
import java.util.Enumeration;
import java.util.Vector;

// TODO: Auto-generated Javadoc
/**
 * The Class Coordinator.
 */
public class Coordinator {
    
    /** The trigger bindings. */
    private final Vector triggerBindings = new Vector();
    
    /** The data wires. */
    private final Vector dataWires = new Vector();
    
    /** The groups. */
    private final GroupManager groups = new GroupManager();

    /** The steps. */
    private final StepManager steps = new StepManager();
    
    /**
     * Apply.
     *
     * @param modules the modules
     */
    protected void apply (ModuleManager modules) {}
    
    /**
     * Attach.
     *
     * @param modules the modules
     */
    public void attach (ModuleManager modules) {
        this.triggerBindings.removeAllElements();
        this.dataWires.removeAllElements();

        this.groups.clear();
        this.steps.clear();
        
        this.apply(modules);

        this.groups.attach(modules);
        this.steps.attach(modules);
    }
    
    /**
     * Bind.
     *
     * @param binding the binding
     */
    protected void bind (Binding binding) {
        this.triggerBindings.addElement(binding);
    }
    
    /**
     * Fill.
     *
     * @param dataWire the data wire
     */
    protected void fill (DataWire dataWire) {
        this.dataWires.addElement(dataWire);
    }
    
    /**
     * Group.
     *
     * @param group the group
     */
    protected void group (Group group) {
        this.groups.add(group);
    }
    
    /**
     * Step.
     *
     * @param step the step
     */
    protected void step (String name, Step step) {
        this.steps.add(name, step);
    }
    
    /**
     * Update.
     */
    public void update () {
        final Enumeration wires = this.dataWires.elements();
        while (wires.hasMoreElements()) ConnectorProxy.pipe((DataWire) wires.nextElement());
        
        final Enumeration bindings = this.triggerBindings.elements();
        while (bindings.hasMoreElements()) ConnectorProxy.pipe((Binding) bindings.nextElement());

        this.groups.update();
        this.steps.update();
    }

    /**
     * Reset.
     */
    public void reset () {
        this.groups.reset();
        this.steps.reset();
    }
}
