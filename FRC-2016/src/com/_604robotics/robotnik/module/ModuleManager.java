package com._604robotics.robotnik.module;

import com._604robotics.robotnik.meta.Repackager;
import com._604robotics.robotnik.memory.IndexedTable;
import com._604robotics.robotnik.logging.InternalLogger;

import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class ModuleManager.
 */
public class ModuleManager {
    
    /** The module table. */
    private final Map<String, ModuleReference> moduleTable;
    
    /**
     * Instantiates a new module manager.
     *
     * @param moduleMap the module map
     * @param table the table
     */
    public ModuleManager (ModuleMap moduleMap, final IndexedTable table) {
        this.moduleTable = Repackager.repackage(moduleMap.iterate(), new Repackager<ModuleReference, String, Module>() {
            public ModuleReference wrap (String key, Module value) {
                return new ModuleReference(key, value, table.getSubTable(key));
            }
        });
    }
    
    /**
     * Gets the module.
     *
     * @param name the name
     * @return the module
     */
    public ModuleReference getModule (String name) {
        ModuleReference ref = (ModuleReference) this.moduleTable.get(name);
        if (ref == null) InternalLogger.missing("ModuleReference", name);
        return ref;
    }
    
    /**
     * Start.
     */
    public void start () {
    	for(ModuleReference ref : this.moduleTable.values()) {
    		ref.start();
    	}
    }
    
    /**
     * Update.
     */
    public void update () {
    	for(ModuleReference ref : this.moduleTable.values()) {
    		ref.update();
    	}
    }
    
    /**
     * Execute.
     */
    public void execute () {
    	for(ModuleReference ref : this.moduleTable.values()) {
    		ref.execute();
    	}
    }
    
    /**
     * End.
     */
    public void end () {
    	for(ModuleReference ref : this.moduleTable.values()) {
    		ref.end();
    	}
    }
}