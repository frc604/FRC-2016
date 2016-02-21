package com._604robotics.robotnik.prefabs.measure;

import com._604robotics.robotnik.coordinator.steps.Measure;
import com._604robotics.robotnik.data.DataAccess;

public class DataMeasure extends Measure {
    public static final int DELTA       = 1024;
    public static final int LOWER_BOUND = 1;
    public static final int UPPER_BOUND = 2;

    private final DataAccess data;
    private final int mode;
    private final double target;
    private double initialValue;

    public DataMeasure (DataAccess data, int mode, double target) {
        this.data = data;
        this.mode = mode;
        this.target = target;
    }

    @Override
    public void initialize () {
        initialValue = data.get();
    }

    @Override
    public boolean complete () {
        double value = data.get();
        if ((mode & DELTA) == DELTA)
            value -= initialValue;
        
        if ((mode & LOWER_BOUND) == LOWER_BOUND)
            return value <= target;
        else if ((mode & UPPER_BOUND) == UPPER_BOUND)
            return value >= target;
        else
            return false;
    }
}
