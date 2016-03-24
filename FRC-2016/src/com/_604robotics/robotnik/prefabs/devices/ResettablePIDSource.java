package com._604robotics.robotnik.prefabs.devices;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class ResettablePIDSource implements PIDSource {
    private final PIDSource source;
    private double zero = 0;

    public ResettablePIDSource (PIDSource source) {
        this.source = source;
    }

    public ResettablePIDSource (PIDSource source, double zero) {
        this.source = source;
        this.zero = zero;
    }
    
    public double getZero () {
        return zero;
    }

    public void setZero (double zero) {
        this.zero = zero;
    }
    
    public void setZero () {
        setZero(source.pidGet());
    }
    
    public double get () {
        return pidGet();
    }

    @Override
    public double pidGet () {
        return source.pidGet() - zero;
    }

    @Override
    public PIDSourceType getPIDSourceType () {
        return source.getPIDSourceType();
    }

    @Override
    public void setPIDSourceType (PIDSourceType sourceType) {
        source.setPIDSourceType(sourceType);
    }
}
