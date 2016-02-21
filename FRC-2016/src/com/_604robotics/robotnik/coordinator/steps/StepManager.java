package com._604robotics.robotnik.coordinator.steps;

import com._604robotics.robotnik.logging.Logger;
import com._604robotics.robotnik.module.ModuleManager;

import java.util.Enumeration;
import java.util.Vector;

public class StepManager {
    private final Vector names = new Vector();
    private final Vector steps = new Vector();

    private int currentStep = 0;
    private boolean initialized = false;

    public void clear () {
        names.removeAllElements();
        steps.removeAllElements();
    }

    public void add (String name, Step step) {
        names.addElement(name);
        steps.addElement(step);
    }

    public void attach (ModuleManager modules) {
        final Enumeration i = steps.elements();
        while (i.hasMoreElements()) ((Step) i.nextElement()).attach(modules);
    }

    public void update () {
        if (!complete()) {
            final Step step = (Step) this.steps.elementAt(currentStep);

            if (!initialized) {
				initialized = true;
				step.initialize();

				Logger.log(" ---- Entered step: " + this.names.elementAt(currentStep));
            } else if (!step.complete()) {
                step.update();
            } else {
                Logger.log(" ---- Completed step: " + this.names.elementAt(this.currentStep));
                
                ++currentStep;
                initialized = false;
            }
        }
    }

    public void stop () {
        this.currentStep = 0;
        this.initialized = false;

        final Enumeration i = this.steps.elements();
        while (i.hasMoreElements()) ((Step) i.nextElement()).reset();
    }
    
    public boolean complete () {
        return currentStep >= steps.size();
    }
}
