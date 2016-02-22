package com._604robotics.robotnik.coordinator;

import java.util.ArrayList;
import java.util.List;

public class SystemManager {
    private final List<Coordinator> systems = new ArrayList<>();
    
    public void addSystem (Coordinator system) {
        systems.add(system);
    }
    
    public void update () {
        for (Coordinator system : systems) {
            system.update();
        }
    }
}