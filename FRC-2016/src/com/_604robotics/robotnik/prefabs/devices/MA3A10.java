package com._604robotics.robotnik.prefabs.devices;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

/**
 * A MA3A10 encoder.
 */
public class MA3A10 implements PIDSource {
    private static final double MAX_VOLTAGE = 5;
    
    private final AnalogInput input;
    private double zero = 0D;
    
    /**
     * Creates a MA3A10.
     * @param port Port of the encoder.
     */
    public MA3A10 (int port) {
        this.input = new AnalogInput(port);
    }
    
    /**
     * Sets the zero of the encoder to its current value.
     */
    public void setZero () {
        this.setZero(this.getRaw());
    }
    
    /**
     * Sets the zero of an encoder.
     * @param zero Zero value to set.
     */
    public void setZero (double zero) {
        this.zero = zero;
    }
    
    /**
     * Sets the zero of the encoder to an angle.
     * @param zeroAngle Zero angle to set.
     */
    public void setZeroAngle (double zeroAngle) {
        this.setZero(zeroAngle / 360 * MAX_VOLTAGE);
    }
    
    /**
     * Gets the raw value of the encoder.
     * @return The raw value of the encoder.
     */
    public double getRaw () {
        double voltage = this.input.getVoltage() - this.zero;
        if (voltage < 0) {
            voltage += MAX_VOLTAGE;
        }
        return voltage;
    }
    
    /**
     * Gets the angle of the encoder.
     * @return The angle of the encoder.
     */
    public double getAngle () {
        return this.getRaw() / MAX_VOLTAGE * 360;
    }

    @Override
    public double pidGet () {
        return this.getAngle();
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
