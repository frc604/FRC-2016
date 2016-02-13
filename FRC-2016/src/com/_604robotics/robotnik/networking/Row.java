package com._604robotics.robotnik.networking;

import edu.wpi.first.wpilibj.tables.ITable;

public class Row {
    private final ITable source;
    private final String key;
    
    public Row (ITable source, String key) {
        this.source = source;
        this.key = key;
    }
    
    public String getKey () {
        return key;
    }

    public String  getString  (String  defaultValue) { return this.source.getString (this.key, defaultValue); }
    public double  getNumber  (double  defaultValue) { return this.source.getNumber (this.key, defaultValue); }
    public boolean getBoolean (boolean defaultValue) { return this.source.getBoolean(this.key, defaultValue); }
    public byte[]  getRaw     (byte[]  defaultValue) { return this.source.getRaw    (this.key, defaultValue); }

    public void putString  (String  value) { this.source.putString (this.key, value); }
    public void putNumber  (double  value) { this.source.putNumber (this.key, value); }
    public void putBoolean (boolean value) { this.source.putBoolean(this.key, value); }
    public void putRaw     (byte[]  value) { this.source.putRaw    (this.key, value); }
}
