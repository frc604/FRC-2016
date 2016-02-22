package com._604robotics.robotnik.coordinator.connectors;

import com._604robotics.robotnik.data.DataAccess;
import com._604robotics.robotnik.data.DataRecipient;
import com._604robotics.robotnik.data.sources.ConstData;
import com._604robotics.robotnik.data.sources.DataTriggerAdaptor;
import com._604robotics.robotnik.trigger.TriggerAccess;

/**
 * Wires data from a data access to a data recipient.
 */
public class DataWire {
    private final DataRecipient recipient;
    private final String fieldName;

    private final DataAccess data;
    private final TriggerAccess activator;

    /**
     * Creates a data wire.
     * @param recipient Recipient of the data.
     * @param fieldName Name of the data field.
     * @param data Data to wire.
     * @param activator Trigger activating the data wire.
     */
    public DataWire (DataRecipient recipient, String fieldName, DataAccess data, TriggerAccess activator) {
        this.recipient = recipient;
        this.fieldName = fieldName;

        this.data = data;
        this.activator = activator;
    }
    
    /**
     * Gets whether the data wire is active.
     * @return Whether the data wire is active.
     */
    public boolean isActive () {
        return this.activator == null || this.activator.get();
    }

    /**
     * Gets the recipient of the data.
     * @return The data's recipient.
     */
    public DataRecipient getRecipient () {
        return this.recipient;
    }

    /**
     * Gets the source of the data.
     * @return The wire's data source.
     */
    public DataAccess getData () {
        return this.data;
    }

    /**
     * Gets the name of the data field.
     * @return The field's name.
     */
    public String getFieldName () {
        return this.fieldName;
    }
    
    /**
     * Conducts the data wire.
     */
    public void conduct () {
        if (isActive()) {
            getRecipient().sendData(getFieldName(), getData().get());
        }
    }
}