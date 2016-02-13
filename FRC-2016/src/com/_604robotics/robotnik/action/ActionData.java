package com._604robotics.robotnik.action;

import java.util.Enumeration;

import com._604robotics.robotnik.action.field.Field;
import com._604robotics.robotnik.action.field.FieldMap;
import com._604robotics.robotnik.data.DataReference;
import com._604robotics.robotnik.logging.InternalLogger;
import com._604robotics.robotnik.module.ModuleReference;
import com._604robotics.robotnik.trigger.TriggerReference;

import edu.wpi.first.wpilibj.tables.ITable;

public class ActionData {
    private final FieldMap map;
    private final ITable table;
    private final ModuleReference module;

    public ActionData (FieldMap map, ITable table, ModuleReference module) {
        this.map = map;
        this.table = table;
        this.module = module;
        
        table.putStringArray("__fields", map.stream().map(Field::getName).toArray(n -> new String[n]));
    }
    
    public double get (String name) {
        return this.lookup(name);
    }
    
    public boolean is (String name) {
        return this.lookup(name) > 0;
    }
    
    public boolean trigger (String name) {
        final TriggerReference trigger = module.getTrigger(name);
        if (trigger == null) {
            InternalLogger.missing("TriggerReference", name);
            return false;
        } else {
            return trigger.get();
        }
    }
    
    public double data (String name) {
        final DataReference data = module.getData(name);
        if (data == null) {
            InternalLogger.missing("DataReference", name);
            return 0D;
        } else {
            return data.get();
        }
    }
    
    protected void reset () {
        map.stream().forEach(field -> table.putNumber(field.getName(), field.getDefaultValue()));
    }
    
    private double lookup (String name) {
        return this.table.getNumber(name, 0D);
    }
}
