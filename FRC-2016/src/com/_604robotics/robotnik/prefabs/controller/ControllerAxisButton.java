package com._604robotics.robotnik.prefabs.controller;

import com._604robotics.robotnik.trigger.TriggerAccess;
import edu.wpi.first.wpilibj.Joystick;

public class ControllerAxisButton implements TriggerAccess {
    private final Joystick joystick;
    private final int axis;
    private final int direction;

    public ControllerAxisButton (Joystick joystick, int axis, int direction) {
        this.joystick = joystick;
        this.axis = axis;
        
        this.direction = direction;
    }
    
    /* (non-Javadoc)
     * @see com._604robotics.robotnik.trigger.TriggerAccess#get()
     */
    public boolean get () {
        return Math.round(this.joystick.getRawAxis(this.axis)) == this.direction;
    }
}
