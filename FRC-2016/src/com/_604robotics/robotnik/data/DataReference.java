package com._604robotics.robotnik.data;

import com._604robotics.robotnik.Safety;
import com._604robotics.robotnik.networking.Row;

public class DataReference implements DataAccess {
    private final Data data;
    private final Row row;
    
    private final Safety safety;
    
    public DataReference (Data data, Row row, Safety safety) {
        this.data = data;
        this.row = row;
        
        this.safety = safety;
    }
    
    /* (non-Javadoc)
     * @see com._604robotics.robotnik.data.DataAccess#get()
     */
    public double get () {
        return row.getNumber(0D);
    }
    
    public void update () {
        safety.wrap("updating data value", () -> row.putNumber(data.run()));
    }
}
