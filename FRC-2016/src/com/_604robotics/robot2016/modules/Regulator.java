package com._604robotics.robot2016.modules;

import com._604robotics.robot2016.Ports;
import com._604robotics.robotnik.data.Data;
import com._604robotics.robotnik.data.DataMap;
import com._604robotics.robotnik.module.Module;
import com._604robotics.robotnik.trigger.Trigger;
import com._604robotics.robotnik.trigger.TriggerMap;

import edu.wpi.first.wpilibj.Compressor;

public class Regulator extends Module {
    private final Compressor compressor = new Compressor(Ports.COMPRESSOR_PORT);

    public Regulator () {
        compressor.setClosedLoopControl(true);
        
        set(new TriggerMap() {{
            add("Compressor Enabled", new Trigger() {
                public boolean run() {
                    return compressor.enabled();
                }
            });
            
            add("Pressure Switch", new Trigger() {
                public boolean run() {
                    return compressor.getPressureSwitchValue();
                }
            });
        }});
        
        set(new DataMap() {{
            add("Compressor Current", new Data() {
                public double run() {
                    return compressor.getCompressorCurrent();
                }
            });
        }});
    }
}
