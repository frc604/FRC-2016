package com._604robotics.robotnik.module;

import java.util.HashMap;
import java.util.Map;

import com._604robotics.robotnik.Safety;

import edu.wpi.first.wpilibj.tables.ITable;

public class ModuleManager {
    private final Map<String, ModuleReference> modules = new HashMap<>();
    private final ITable table;
    
    private final Safety safety;

    public ModuleManager (ITable table, Safety safety) {
        this.table = table;
        this.safety = safety;
    }

    public ModuleReference addModule (String name, Module module) {
        final ModuleReference ref = new ModuleReference(module, table.getSubTable(name), safety);
        modules.put(name, ref);
        return ref;
    }
    
    public void index () {
        table.putStringArray("__modules", modules.keySet().toArray(new String[modules.keySet().size()]));
    }
    
    public void start () {
        for (ModuleReference module : modules.values()) {
            module.start();
        }
    }
    
    public void update () {
        for (ModuleReference module : modules.values()) {
            module.update();
        }
    }
    
    public void execute () {
        for (ModuleReference module : modules.values()) {
            module.execute();
        }
    }
    
    public void stop () {
        for (ModuleReference module : modules.values()) {
            module.stop();
        }
    }
}