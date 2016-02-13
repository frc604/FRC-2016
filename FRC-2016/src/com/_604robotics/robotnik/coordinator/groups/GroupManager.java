package com._604robotics.robotnik.coordinator.groups;

import java.util.Enumeration;
import java.util.Vector;

import com._604robotics.robotnik.Robot;

public class GroupManager<T extends Robot<T>> {
    private final Vector groups = new Vector();

    public void clear () {
        this.groups.removeAllElements();
    }

    public void add (Group group) {
        this.groups.addElement(group);
    }

    public void attach (T robot) {
        final Enumeration i = this.groups.elements();
        while (i.hasMoreElements()) ((Group<T>) i.nextElement()).attach(robot);
    }

    public void update () {
        final Enumeration i = this.groups.elements();
        while (i.hasMoreElements()) ((Group<T>) i.nextElement()).update();
    }

    public void stop () {
        final Enumeration i = this.groups.elements();
        while (i.hasMoreElements()) ((Group<T>) i.nextElement()).stop();
    }
    
    public boolean complete () {
        final Enumeration i = this.groups.elements();
        while (i.hasMoreElements()) {
            if (!((Group) i.nextElement()).complete()) {
                return false;
            }
        }
        return true;
    }
}
