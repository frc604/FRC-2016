package com._604robotics.robotnik.coordinator.steps;

import java.util.Enumeration;
import java.util.Vector;

import com._604robotics.robotnik.Robot;
import com._604robotics.robotnik.logging.Logger;

public class StepManager<T extends Robot<T>> {
    private final Vector names = new Vector();
    private final Vector steps = new Vector();

    private int currentStep = 0;
    private boolean initialized = false;

    public void clear () {
        this.names.removeAllElements();
        this.steps.removeAllElements();
    }

    public void add (String name, Step<T> step) {
        this.names.addElement(name);
        this.steps.addElement(step);
    }

    public void attach (T robot) {
        final Enumeration i = this.steps.elements();
        while (i.hasMoreElements()) ((Step<T>) i.nextElement()).attach(robot);
    }

    public void update () {
        if (this.currentStep < this.steps.size()) {
            final Step<T> step = (Step<T>) this.steps.elementAt(currentStep);

            if (!this.initialized) {
				this.initialized = true;
				step.initialize();

				Logger.log(" ---- Entered step: " + this.names.elementAt(currentStep));
            } else if (!step.complete()) {
                step.update();
            } else {
                Logger.log(" ---- Completed step: " + this.names.elementAt(this.currentStep));
                
                this.currentStep++;
                this.initialized = false;
            }
        }
    }

    public void stop () {
        this.currentStep = 0;
        this.initialized = false;

        final Enumeration i = this.steps.elements();
        while (i.hasMoreElements()) ((Step<T>) i.nextElement()).stop();
    }
}
