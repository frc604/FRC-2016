package com._604robotics.robotnik.prefabs.devices;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class MA3A10 implements PIDSource {
    private final AnalogInput input;
    private double zero = 0D;
    
    public MA3A10 (int port) {
        input = new AnalogInput(port);
    }
    
    public void setZero () {
        zero = this.getRaw();
    }
    
    public void setZero (double zero) {
        this.zero = zero;
    }
    
    public void setZeroAngle (double zeroAngle) {
        setZero(zeroAngle / 360 * 5);
    }
    
    public double getRaw () {
        return input.getVoltage() - zero;
    }
    
    public double getAngle () {
        return getRaw() / 5 * 360;
    }

    @Override
    public double pidGet () {
        return getAngle();
    }

    @Override
    public PIDSourceType getPIDSourceType () {
        return PIDSourceType.kDisplacement;
    }

    @Override
    public void setPIDSourceType (PIDSourceType sourceType) {
        if (sourceType != PIDSourceType.kRate) {
            throw new IllegalArgumentException("MA3A10 class only implements PIDSourceType.kDisplacement");
        }
    }
}
