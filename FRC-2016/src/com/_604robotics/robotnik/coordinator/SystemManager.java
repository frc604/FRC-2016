package com._604robotics.robotnik.coordinator;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages systems.
 */
public class SystemManager {
    private final List<Coordinator> systems = new ArrayList<>();
    
    /**
     * Adds a system.
     * @param system System to add.
     */
    public void addSystem (Coordinator system) {
        systems.add(system);
    }
    
    /**
     * Updates all systems.
     */
    public void update () {
        for (Coordinator system : systems) {
            system.update();
        }
    }
}