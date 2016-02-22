package com._604robotics.robotnik.module;

import java.util.ArrayList;
import java.util.List;

import com._604robotics.robotnik.Safety;
import com._604robotics.robotnik.memory.IndexedTable;

/**
 * Manages modules.
 */
public class ModuleManager {
    private final List<ModuleReference> modules = new ArrayList<>();
    private final IndexedTable table;
    
    private final Safety safety;

    /**
     * Creates a module manager.
     * @param table Table to store module data in.
     * @param safety Safety mode to operate under.
     */
    public ModuleManager (IndexedTable table, Safety safety) {
        this.table = table;
        this.safety = safety;
    }

    /**
     * Adds a module to the manager.
     * @param name The name to use for the module.
     * @param module The module to add.
     * @return A reference to the module added to the manager.
     */
    public ModuleReference addModule (String name, Module module) {
        final ModuleReference ref = new ModuleReference(name, module, table.getSubTable(name), safety);
        modules.add(ref);
        return ref;
    }
    
    /**
     * Starts all modules.
     */
    public void start () {
        for (ModuleReference module : modules) {
            module.start();
        }
    }
    
    /**
     * Updates all modules.
     * @param safety Safety mode to operate under.
     */
    public void update () {
        for (ModuleReference module : modules) {
            module.update();
        }
    }

    /**
     * Executes all modules.
     * @param safety Safety mode to operate under.
     */
    public void execute () {
        for (ModuleReference module : modules) {
            module.execute();
        }
    }
    
    /**
     * Stops all modules.
     * @param safety Safety mode to operate under.
     */
    public void stop () {
        for (ModuleReference module : modules) {
            module.stop();
        }
    }
}