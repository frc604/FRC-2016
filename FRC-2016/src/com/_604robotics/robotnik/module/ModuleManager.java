package com._604robotics.robotnik.module;

import com._604robotics.robotnik.memory.IndexedTable;
import com._604robotics.robotnik.Safety;
import com._604robotics.robotnik.logging.InternalLogger;

import java.util.HashMap;
import java.util.Map;

public class ModuleManager {
    private final Map<String, ModuleReference> moduleTable;

    public ModuleManager (ModuleMap moduleMap, final IndexedTable table) {
        this.moduleTable = new HashMap<String, ModuleReference>();
        for(Map.Entry<String, Module> entry : moduleMap) {
        	this.moduleTable.put(entry.getKey(), new ModuleReference(entry.getKey(), entry.getValue(), table.getSubTable(entry.getKey())));
        }
    }

    public ModuleReference getModule (String name) {
        ModuleReference ref = (ModuleReference) this.moduleTable.get(name);
        if (ref == null) InternalLogger.missing("ModuleReference", name);
        return ref;
    }

    public void start (Safety safety) {
    	for(ModuleReference ref : this.moduleTable.values()) {
    		ref.start(safety);
    	}
    }

    public void update (Safety safety) {
    	for(ModuleReference ref : this.moduleTable.values()) {
    		ref.update(safety);
    	}
    }

    public void execute (Safety safety) {
    	for(ModuleReference ref : this.moduleTable.values()) {
    		ref.execute(safety);
    	}
    }

    public void stop (Safety safety) {
    	for(ModuleReference ref : this.moduleTable.values()) {
    		ref.stop(safety);
    	}
    }
}
