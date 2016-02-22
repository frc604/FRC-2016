package com._604robotics.robotnik.prefabs.controller.xbox;

import com._604robotics.robotnik.prefabs.controller.ControllerAxis;
import edu.wpi.first.wpilibj.Joystick;

/**
 * A stick on an Xbox controller.
 */
public class XboxControllerStick {
    /**
     * X-axis.
     */
    public final ControllerAxis X;
    
    /**
     * Y-axis.
     */
    public final ControllerAxis Y;

    /**
     * Creates an Xbox controller stick.
     * @param joystick Xbox controller containing the stick.
     * @param left X-axis ID.
     * @param right Y-axis ID.
     */
    public XboxControllerStick (Joystick joystick, int x, int y) {
        this.X = new ControllerAxis(joystick, x);
        this.Y = new ControllerAxis(joystick, y);
    }
}