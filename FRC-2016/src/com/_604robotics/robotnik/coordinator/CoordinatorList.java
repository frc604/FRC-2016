package com._604robotics.robotnik.coordinator;

import java.util.ArrayList;
import java.util.List;

import com._604robotics.robotnik.module.ModuleManager;

public class CoordinatorList {
    private final List<Coordinator> coordinators = new ArrayList<Coordinator>();
    
    protected void add (Coordinator coordinator) {
        this.coordinators.add(coordinator);
    }
    
    public void attach (ModuleManager modules) {
        for (Coordinator coordinator : this.coordinators) {
            coordinator.attach(modules);
        }
    }
    
    public void update () {
        for (Coordinator coordinator : this.coordinators) {
            coordinator.update();
        }
    }
}