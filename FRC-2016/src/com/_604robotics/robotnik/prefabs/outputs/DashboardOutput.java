package com._604robotics.robotnik.prefabs.outputs;

import com._604robotics.robotnik.data.DataRecipient;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DashboardOutput implements DataRecipient {
    private static DashboardOutput doubleInstance = null;
    private static DashboardOutput booleanInstance = null;
    private final boolean asBoolean;

    public static DashboardOutput asDouble () {
        if (doubleInstance == null) {
            doubleInstance = new DashboardOutput(false);
        }
        
        return doubleInstance;
    }

    public static DashboardOutput asBoolean () {
        if (booleanInstance == null) {
            booleanInstance = new DashboardOutput(true);
        }
        
        return booleanInstance;
    }

    private DashboardOutput (boolean asBoolean) {
        this.asBoolean = asBoolean;
    }
    
    /* (non-Javadoc)
     * @see com._604robotics.robotnik.data.DataRecipient#sendData(java.lang.String, double)
     */
    public void sendData (String fieldName, double dataValue) {
        if (this.asBoolean) {
            SmartDashboard.putBoolean(fieldName, dataValue > 0);
        } else {
            SmartDashboard.putNumber(fieldName, dataValue);
        }
    }
}
