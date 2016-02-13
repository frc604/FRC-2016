package com._604robotics.robotnik.coordinator;

import java.util.Enumeration;
import java.util.Vector;

import com._604robotics.robotnik.Robot;
import com._604robotics.robotnik.coordinator.connectors.Binding;
import com._604robotics.robotnik.coordinator.connectors.DataWire;
import com._604robotics.robotnik.coordinator.groups.Group;
import com._604robotics.robotnik.coordinator.groups.GroupManager;
import com._604robotics.robotnik.coordinator.steps.Step;
import com._604robotics.robotnik.coordinator.steps.StepManager;

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
    
    protected void bind (Binding binding) {
        this.triggerBindings.addElement(binding);
    }
    
    protected void fill (DataWire dataWire) {
        this.dataWires.addElement(dataWire);
    }
    
    protected void group (Group<T> group) {
        this.groups.add(group);
    }
    
    protected void step (String name, Step<T> step) {
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
}
