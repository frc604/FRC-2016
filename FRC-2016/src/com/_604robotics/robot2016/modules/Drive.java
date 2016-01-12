package com._604robotics.robot2016.modules;

import com._604robotics.robotnik.action.controllers.ElasticController;
import com._604robotics.robotnik.module.Module;

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.PIDSource.PIDSourceParameter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive extends Module
{
    private final RobotDrive drive=new RobotDrive(0,1,2,3);//Adjust channel numbers!!
    
    private final Encoder encoderLeft =new Encoder(0,1,true,CounterBase.EncodingType.k4X);
    private final Encoder encoderRight=new Encoder(2,3,true,CounterBase.EncodingType.k4X);
    
    private double PIDLeftOut =0D;
    private double PIDRightOut=0D;
    
    private double pid_power_cap=0.6;//ADJUST
    
    private final PIDController pidLeft = new PIDController(0.020, 0D, 0.005, encoderLeft, new PIDOutput () {
        public void pidWrite (double output) {
            if (output > 0) PIDLeftOut = (output > pid_power_cap) ? pid_power_cap : output;
            else PIDLeftOut = (output < -pid_power_cap) ? -pid_power_cap : output;
        }
    });
    
    /** The pid right. */
    private final PIDController pidRight = new PIDController(0.020, 0D, 0.005, encoderRight, new PIDOutput () {
        public void pidWrite (double output) {
            if (output > 0) PIDRightOut = (output > pid_power_cap) ? pid_power_cap : output;
            else PIDRightOut = (output < -pid_power_cap) ? -pid_power_cap : output;
        }
    });
    
    public Drive()
    {
        this.set(new ElasticController()
        {{
            
        }});
    }
}
