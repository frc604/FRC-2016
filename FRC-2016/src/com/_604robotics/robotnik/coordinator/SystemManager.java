package com._604robotics.robotnik.coordinator;

import java.util.ArrayList;
import java.util.List;

import com._604robotics.robotnik.Robot;

public class SystemManager<T extends Robot<T>> {
    private final List<Coordinator<T>> systems = new ArrayList<>();
    
    public void addSystem (Coordinator<T> system) {
        systems.add(system);
    }
    
    public void attach (T robot) {
        for (Coordinator<T> system : systems) {
            system.attach(robot);
        }
    }
    
    public void update () {
        for (Coordinator<T> system : systems) {
            system.update();
        }
    }
}