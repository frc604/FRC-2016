package com._604robotics.robot2016.modules;

import com._604robotics.robot2016.constants.Ports;
import com._604robotics.robotnik.data.DataMap;
import com._604robotics.robotnik.module.Module;
import com._604robotics.robotnik.trigger.TriggerMap;

import edu.wpi.first.wpilibj.Compressor;

public class Regulator extends Module {
    private final Compressor compressor = new Compressor(Ports.COMPRESSOR_PORT);

    public Regulator () {
        compressor.setClosedLoopControl(true);

        set(new DataMap() {{
            add("Compressor Current", compressor::getCompressorCurrent);
        }});

        set(new TriggerMap() {{
            add("Compressor Enabled", compressor::enabled);
            add("Pressure Switch", compressor::getPressureSwitchValue);
        }});
    }
}
