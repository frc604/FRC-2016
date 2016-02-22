package com._604robotics.robotnik.data;

import com._604robotics.robotnik.Safety;
import com._604robotics.robotnik.memory.IndexedTable.Row;

/**
 * A reference to data.
 */
public class DataReference implements DataAccess {
    private final Data data;
    private final Row value;
    
    private final Safety safety;
    
    /**
     * Creates a data reference.
     * @param data Data to refer to.
     * @param value Slice to store the data value in.
     */
    public DataReference (Data data, Row value, Safety safety) {
        this.data = data;
        this.value = value;
        
        this.safety = safety;
    }
    
    @Override
    public double get () {
        return value.getNumber(0D);
    }
    
    /**
     * Updates the value of data.
     * @param safety Safety mode to operate with.
     */
    public void update () {
        safety.wrap("updating data value", () -> value.putNumber(data.run()));
    }
}
