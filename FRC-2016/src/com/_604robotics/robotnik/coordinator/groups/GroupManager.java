package com._604robotics.robotnik.coordinator.groups;

import com._604robotics.robotnik.coordinator.Coordinator;
import com._604robotics.robotnik.module.ModuleManager;

import java.util.Enumeration;
import java.util.Vector;

public class GroupManager {
    private final Vector groups = new Vector();

    public void clear () {
        this.groups.removeAllElements();
    }

    public void add (Group group) {
        this.groups.addElement(group);
    }

    public void attach (ModuleManager modules) {
        final Enumeration i = this.groups.elements();
        while (i.hasMoreElements()) ((Group) i.nextElement()).attach(modules);
    }

    public void update () {
        final Enumeration i = this.groups.elements();
        while (i.hasMoreElements()) ((Group) i.nextElement()).update();
    }

    public void reset () {
        final Enumeration i = this.groups.elements();
        while (i.hasMoreElements()) ((Group) i.nextElement()).reset();
    }
}
