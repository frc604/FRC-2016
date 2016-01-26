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
        this.names.removeAllElements();
        this.steps.removeAllElements();
    }

    public void add (String name, Step step) {
        this.names.addElement(name);
        this.steps.addElement(step);
    }

    public void attach (ModuleManager modules) {
        final Enumeration i = this.steps.elements();
        while (i.hasMoreElements()) ((Step) i.nextElement()).attach(modules);
    }

    public void update () {
        if (this.currentStep < this.steps.size()) {
            final Step step = (Step) this.steps.elementAt(currentStep);

            if (step.complete()) {
                Logger.log(" ---- Completed step: " + this.names.elementAt(this.currentStep));
                
                this.currentStep++;
                this.initialized = false;
            } else {
                if (!this.initialized) {
                    this.initialized = true;
                    step.initialize();
                    
                    Logger.log(" ---- Entered step: " + this.names.elementAt(currentStep));
                }

                step.update();
            }
        }
    }

    public void reset () {
        this.currentStep = 0;
        this.initialized = false;

        final Enumeration i = this.steps.elements();
        while (i.hasMoreElements()) ((Step) i.nextElement()).reset();
    }
}
