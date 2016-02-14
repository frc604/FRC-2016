package com._604robotics.robotnik.coordinator.steps;

import com._604robotics.robotnik.logging.Logger;
import com._604robotics.robotnik.module.ModuleManager;

import java.util.ArrayList;
import java.util.List;

public class StepManager {
    private final List<String> names = new ArrayList<String>();
    private final List<Step> steps = new ArrayList<Step>();

    private int currentStep = 0;
    private boolean initialized = false;

    public void clear () {
        this.names.clear();
        this.steps.clear();
    }

    public void add (String name, Step step) {
        this.names.add(name);
        this.steps.add(step);
    }

    public void attach (ModuleManager modules) {
    	for(Step step : this.steps) {
    		step.attach(modules);
    	}
    }

    public void update () {
        if (this.currentStep < this.steps.size()) {
            final Step step = this.steps.get(currentStep);

            if (!this.initialized) {
				this.initialized = true;
				step.initialize();

				Logger.log(" ---- Entered step: " + this.names.get(currentStep));
            } else if (!step.complete()) {
                step.update();
            } else {
                Logger.log(" ---- Completed step: " + this.names.get(this.currentStep));
                
                this.currentStep++;
                this.initialized = false;
            }
        }
    }

    public void stop () {
        this.currentStep = 0;
        this.initialized = false;

    	for(Step step : this.steps) {
    		step.reset();
    	}
    }
}
