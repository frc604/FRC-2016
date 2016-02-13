package com._604robotics.robotnik.data;

import com._604robotics.robotnik.Safety;
import com._604robotics.robotnik.memory.IndexedTable.Row;

public class DataReference implements DataAccess {
    private final Data data;
    private final Row value;
    
    private final Safety safety;
    
    public DataReference (Data data, Row value, Safety safety) {
        this.data = data;
        this.value = value;
        
        this.safety = safety;
    }
    
    /* (non-Javadoc)
     * @see com._604robotics.robotnik.data.DataAccess#get()
     */
    public double get () {
        return value.getNumber(0D);
    }
    
    public void update () {
        safety.wrap("updating data value", () -> value.putNumber(data.run()));
    }
}
