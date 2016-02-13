package com._604robotics.robotnik.module;

import java.util.ArrayList;
import java.util.List;

import com._604robotics.robotnik.Safety;
import com._604robotics.robotnik.memory.IndexedTable;

public class ModuleManager {
    private final List<ModuleReference> modules = new ArrayList<>();
    private final IndexedTable table;
    
    private final Safety safety;

    public ModuleManager (IndexedTable table, Safety safety) {
        this.table = table;
        this.safety = safety;
    }

    public ModuleReference addModule (String name, Module module) {
        final ModuleReference ref = new ModuleReference(name, module, table.getSubTable(name), safety);
        modules.add(ref);
        return ref;
    }
    
    public void start () {
        for (ModuleReference module : modules) {
            module.start();
        }
    }
    
    public void update () {
        for (ModuleReference module : modules) {
            module.update();
        }
    }
    
    public void execute () {
        for (ModuleReference module : modules) {
            module.execute();
        }
    }
    
    public void stop () {
        for (ModuleReference module : modules) {
            module.stop();
        }
    }
}