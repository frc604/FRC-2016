package com._604robotics.robotnik.trigger.sources;

import com._604robotics.robotnik.trigger.Trigger;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DashboardTrigger implements Trigger {
    private final String key;
    private final boolean defaultValue;

    public DashboardTrigger (String key, boolean defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
        
        SmartDashboard.putBoolean(key, defaultValue);
    }

    /* (non-Javadoc)
     * @see com._604robotics.robotnik.trigger.Trigger#run()
     */
    public boolean run () {
        return SmartDashboard.getBoolean(this.key, this.defaultValue);
    }
}