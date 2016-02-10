package com._604robotics.robotnik.coordinator;

import java.util.ArrayList;
import java.util.List;

import com._604robotics.robotnik.module.ModuleManager;

// TODO: Auto-generated Javadoc
/**
 * The Class CoordinatorList.
 */
public class CoordinatorList {
    
    /** The coordinators. */
    private final List<Coordinator> coordinators = new ArrayList<Coordinator>();
    
    /**
     * Adds the.
     *
     * @param coordinator the coordinator
     */
    protected void add (Coordinator coordinator) {
        this.coordinators.add(coordinator);
    }
    
    /**
     * Attach.
     *
     * @param modules the modules
     */
    public void attach (ModuleManager modules) {
    	for(Coordinator coordinator : this.coordinators) {
    		coordinator.attach(modules);
    	}
    }
    
    /**
     * Update.
     */
    public void update () {
    	for(Coordinator coordinator : this.coordinators) {
    		coordinator.update();
    	}
    }
}