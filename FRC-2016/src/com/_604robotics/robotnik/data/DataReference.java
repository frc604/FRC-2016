package com._604robotics.robotnik.data;

import com._604robotics.robotnik.Safety;
import com._604robotics.robotnik.memory.IndexedTable.Slice;

public class DataReference implements DataAccess {
    private final Data data;
    private final Slice value;
    
    private final Safety safety;
    
    public DataReference (Data data, Slice value, Safety safety) {
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
