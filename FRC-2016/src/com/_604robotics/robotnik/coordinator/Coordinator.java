package com._604robotics.robotnik.coordinator;

import com._604robotics.robotnik.coordinator.connectors.Binding;
import com._604robotics.robotnik.coordinator.connectors.DataWire;
import com._604robotics.robotnik.coordinator.groups.Group;
import com._604robotics.robotnik.coordinator.groups.GroupManager;
import com._604robotics.robotnik.coordinator.steps.Step;
import com._604robotics.robotnik.coordinator.steps.StepManager;
import com._604robotics.robotnik.module.ModuleManager;
import java.util.Enumeration;
import java.util.Vector;

public class Coordinator {
    private final Vector triggerBindings = new Vector();
    private final Vector dataWires = new Vector();
    
    private final GroupManager groups = new GroupManager();
    private final StepManager steps = new StepManager();
    
    protected void apply (ModuleManager modules) {}
    
    public void attach (ModuleManager modules) {
        this.triggerBindings.removeAllElements();
        this.dataWires.removeAllElements();

        this.groups.clear();
        this.steps.clear();
        
        this.apply(modules);

        this.groups.attach(modules);
        this.steps.attach(modules);
    }
    
    protected void bind (Binding binding) {
        this.triggerBindings.addElement(binding);
    }

    protected void fill (DataWire dataWire) {
        this.dataWires.addElement(dataWire);
    }
    
    protected void group (Group group) {
        this.groups.add(group);
    }
    
    protected void step (String name, Step step) {
        this.steps.add(name, step);
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
    
    public boolean complete () {
        return steps.complete() && groups.complete();
    }
}
