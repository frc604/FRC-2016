package com._604robotics.robot2016.modules;

import com._604robotics.robotnik.action.Action;
import com._604robotics.robotnik.action.ActionData;
import com._604robotics.robotnik.action.controllers.ElasticController;
import com._604robotics.robotnik.data.Data;
import com._604robotics.robotnik.data.DataMap;
import com._604robotics.robotnik.module.Module;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Shifter extends Module
{
    //Check ports
    //private final DoubleSolenoid solenoid=new DoubleSolenoid(1,2);
    public boolean gearHigh=false;
    
    public Shifter()
    {
        this.set(new DataMap()
        {{
            add("Shifting", new Data()
            {
                public double run()
                {
                    return gearHigh?1:0;
                }
            });
        }});
        
        this.set(new ElasticController()
        {{
            addDefault("Low Gear", new Action()
            {
                public void begin(ActionData data)
                {
                    //solenoid.set(Value.kReverse);
                    gearHigh=false;
                };
            });
            add("High Gear", new Action()
            {
                public void begin(ActionData data)
                {
                    //solenoid.set(Value.kForward);
                    gearHigh=true;
                }
            });
        }});
    }
}
