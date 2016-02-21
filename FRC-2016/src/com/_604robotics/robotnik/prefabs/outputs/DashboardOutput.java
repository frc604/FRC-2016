package com._604robotics.robotnik.prefabs.outputs;

import com._604robotics.robotnik.data.DataRecipient;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DashboardOutput {
    private static DoubleDashboardOutput doubleInstance = null;
    private static BooleanDashboardOutput booleanInstance = null;
    
    private DashboardOutput () {}
    
    public static DataRecipient asDouble () {
        if (doubleInstance == null) {
            doubleInstance = new DoubleDashboardOutput();
        }
        
        return doubleInstance;
    }
    
    public static DataRecipient asBoolean () {
        if (booleanInstance == null) {
            booleanInstance = new BooleanDashboardOutput();
        }
        
        return booleanInstance;
    }
    
    public static DataRecipient asBoolean (String falseText, String trueText) {
        return new FancyBooleanDashboardOutput(falseText, trueText);
    }
    
    private static class DoubleDashboardOutput implements DataRecipient {
        public void sendData (String fieldName, double dataValue) {
            SmartDashboard.putNumber(fieldName, dataValue);
        }
    }

    private static class BooleanDashboardOutput implements DataRecipient {
        public void sendData (String fieldName, double dataValue) {
                SmartDashboard.putBoolean(fieldName, dataValue > 0);
        }
    }

    private static class FancyBooleanDashboardOutput implements DataRecipient {
        private final String falseText;
        private final String trueText;
        
        public FancyBooleanDashboardOutput (String falseText, String trueText) {
            this.falseText = falseText;
            this.trueText = trueText;
        }

        public void sendData (String fieldName, double dataValue) {
            SmartDashboard.putString(fieldName, dataValue > 0 ? trueText : falseText);
        }
    }
}
