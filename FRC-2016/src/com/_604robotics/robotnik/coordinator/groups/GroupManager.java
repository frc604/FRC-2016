package com._604robotics.robotnik.coordinator.groups;

import com._604robotics.robotnik.module.ModuleManager;

import java.util.ArrayList;
import java.util.List;

public class GroupManager {
    private final List<Group> groups = new ArrayList<Group>();

    public void clear () {
        this.groups.clear();
    }

    public void add (Group group) {
        this.groups.add(group);
    }

    public void attach (ModuleManager modules) {
    	for(Group group : this.groups) {
    		group.attach(modules);
    	}
    }

    public void update () {
    	for(Group group : this.groups) {
    		group.update();
    	}
    }

    public void stop () {
        for(Group group : this.groups) {
            group.reset();
        }
    }
}
