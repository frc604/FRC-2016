package com._604robotics.robotnik.data;

import com._604robotics.robotnik.Safety;
import com._604robotics.robotnik.memory.IndexedTable.Slice;

public class DataReference implements DataAccess {
    private final Data data;
    private final Slice value;
    
    public DataReference (Data data, Slice value) {
        this.data = data;
        this.value = value;
    }
    
    /* (non-Javadoc)
     * @see com._604robotics.robotnik.data.DataAccess#get()
     */
    public double get () {
        return value.getNumber(0D);
    }
    
    public void update (Safety safety) {
        safety.wrap("updating data value", () -> value.putNumber(data.run()));
    }
}
