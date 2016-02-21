package com._604robotics.robotnik.coordinator.connectors;

import com._604robotics.robotnik.data.DataAccess;
import com._604robotics.robotnik.data.DataRecipient;
import com._604robotics.robotnik.data.sources.ConstData;
import com._604robotics.robotnik.data.sources.DataTriggerAdaptor;
import com._604robotics.robotnik.trigger.TriggerAccess;

public class DataWire {
    private final DataRecipient recipient;
    private final String fieldName;

    private final DataAccess data;
    private final TriggerAccess activator;
    
    public DataWire (DataRecipient recipient, String fieldName, DataAccess data, TriggerAccess activator) {
        this.recipient = recipient;
        this.fieldName = fieldName;

        this.data = data;
        this.activator = activator;
    }
    
    public boolean isActive () {
        return this.activator == null || this.activator.get();
    }

    public DataRecipient getRecipient () {
        return this.recipient;
    }

    public DataAccess getData () {
        return this.data;
    }

    public String getFieldName () {
        return this.fieldName;
    }
    
    public void conduct () {
        if (isActive()) {
            getRecipient().sendData(getFieldName(), getData().get());
        }
    }
}