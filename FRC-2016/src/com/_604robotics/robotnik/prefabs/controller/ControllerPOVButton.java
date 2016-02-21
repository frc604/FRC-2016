package com._604robotics.robotnik.prefabs.controller;

import com._604robotics.robotnik.trigger.TriggerAccess;
import edu.wpi.first.wpilibj.Joystick;

public class ControllerPOVButton implements TriggerAccess {
    private final Joystick joystick;
    private final int port;
    private final int directionBottom;
    private final int directionTop;

    public ControllerPOVButton (Joystick joystick, int port, int direction) {
        this.joystick = joystick;
        this.port = port;
        this.directionTop = direction;
        this.directionBottom = direction;
    }
    
    public ControllerPOVButton (Joystick joystick, int port, int bottomAngle, int topAngle){
    	this.joystick = joystick;
        this.port = port;
        this.directionTop = topAngle;
        this.directionBottom = bottomAngle;
    }
    
    @Override
    public boolean get () {
    	if(directionTop > directionBottom){
    		return joystick.getPOV(port) <= directionTop && joystick.getPOV(port) >= directionBottom;
    	}
    	else{
    		return (joystick.getPOV(port) <= directionTop && joystick.getPOV(port) >= 0) ||
    				(joystick.getPOV(port) <= 360 && joystick.getPOV(port) >= directionBottom);
    	}
    }
}
