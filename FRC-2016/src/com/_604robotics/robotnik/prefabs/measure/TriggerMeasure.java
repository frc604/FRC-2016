package com._604robotics.robotnik.prefabs.measure;

import com._604robotics.robotnik.coordinator.steps.Measure;
import com._604robotics.robotnik.trigger.TriggerAccess;

public class TriggerMeasure extends Measure {
    private final TriggerAccess trigger;

    public TriggerMeasure(TriggerAccess trigger) {
        this.trigger = trigger;
    }

    @Override
    public boolean complete () {
        return trigger.get();
    }
}
