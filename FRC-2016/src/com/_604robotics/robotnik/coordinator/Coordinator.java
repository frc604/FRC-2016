package com._604robotics.robotnik.coordinator;

import java.util.ArrayList;
import java.util.List;

import com._604robotics.robotnik.coordinator.connectors.Binding;
import com._604robotics.robotnik.coordinator.connectors.DataWire;
import com._604robotics.robotnik.coordinator.groups.Group;
import com._604robotics.robotnik.coordinator.groups.GroupManager;
import com._604robotics.robotnik.coordinator.steps.Step;
import com._604robotics.robotnik.coordinator.steps.StepManager;
import com._604robotics.robotnik.module.ModuleManager;

public class Coordinator {
    private final List<Binding> triggerBindings = new ArrayList<Binding>();
    private final List<DataWire> dataWires = new ArrayList<DataWire>();
    
    private final GroupManager groups = new GroupManager();
    private final StepManager steps = new StepManager();
    
    protected void apply (ModuleManager modules) {}
    
    public void attach (ModuleManager modules) {
        this.triggerBindings.clear();
        this.dataWires.clear();

        this.groups.clear();
        this.steps.clear();
        
        this.apply(modules);

        this.groups.attach(modules);
        this.steps.attach(modules);
    }
    
    protected void bind (Binding binding) {
        this.triggerBindings.add(binding);
    }

    protected void fill (DataWire dataWire) {
        this.dataWires.add(dataWire);
    }
    
    protected void group (Group group) {
        this.groups.add(group);
    }
    
    protected void step (String name, Step step) {
        this.steps.add(name, step);
    }
    
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

    public void stop () {
        this.groups.stop();
        this.steps.stop();
    }
    
    public boolean complete () {
        return steps.complete() && groups.complete();
    }
}
