package com._604robotics.robotnik.module;

import com._604robotics.robotnik.Safety;
import com._604robotics.robotnik.action.ActionManager;
import com._604robotics.robotnik.action.ActionReference;
import com._604robotics.robotnik.data.DataManager;
import com._604robotics.robotnik.data.DataReference;
import com._604robotics.robotnik.memory.IndexedTable;
import com._604robotics.robotnik.trigger.TriggerManager;
import com._604robotics.robotnik.trigger.TriggerReference;

public class ModuleReference {
    private final Module module;

    private final DataManager dataManager;
    private final TriggerManager triggerManager;
    private final ActionManager actionManager;
    
    public ModuleReference (String name, Module module, IndexedTable table) {
        this.dataManager = new DataManager(name, module.getDataMap(), table.getSubTable("data"));
        this.triggerManager = new TriggerManager(name, module.getTriggerMap(), table.getSubTable("triggers"));
        
        this.actionManager = new ActionManager(this, name, module.getActionController(), table.getSubTable("actions"));
        
        this.module = module;
    }
    
    public DataReference getData (String name) {
        return this.dataManager.getData(name);
    }
    
    public TriggerReference getTrigger (String name) {
        return this.triggerManager.getTrigger(name);
    }
    
    public ActionReference getAction (String name) {
        return this.actionManager.getAction(name);
    }
    
    public void start (Safety safety) {
        safety.wrap("module begin phase", module::begin);
    }
    
    public void update (Safety safety) {
        this.dataManager.update(safety);
        this.triggerManager.update(safety);
        
        this.actionManager.reset();
    }

    public void execute (Safety safety) {
        this.actionManager.update();
        this.actionManager.execute(safety);
    }
    
    public void stop (Safety safety) {
        this.actionManager.stop(safety);
        safety.wrap("module end phase", module::end);
    }
}
