package com._604robotics.robot2016.modules;

import com._604robotics.robot2016.Ports;
import com._604robotics.robotnik.data.Data;
import com._604robotics.robotnik.data.DataMap;
import com._604robotics.robotnik.module.Module;
import com._604robotics.robotnik.trigger.Trigger;
import com._604robotics.robotnik.trigger.TriggerMap;

import edu.wpi.first.wpilibj.Compressor;

public class CompressorModule extends Module
{
    public CompressorModule()
    {
        Compressor c=new Compressor(Ports.COMPRESSOR_PORT);
        c.setClosedLoopControl(true);
        
        this.set(new TriggerMap()
        {{
            add("Compressor Enabled", new Trigger()
            {
                public boolean run()
                {
                    return c.enabled();
                }
            });
            
            add("Pressure Switch", new Trigger()
            {
                public boolean run()
                {
                    return c.getPressureSwitchValue();
                }
            });
        }});
        
        this.set(new DataMap()
        {{
            add("Compressor Current", new Data()
            {
                public double run()
                {
                    return c.getCompressorCurrent();
                }
            });
        }});
    }
}
