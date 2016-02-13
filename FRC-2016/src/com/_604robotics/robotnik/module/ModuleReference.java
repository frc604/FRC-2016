package com._604robotics.robotnik.module;

import com._604robotics.robotnik.Safety;
import com._604robotics.robotnik.action.ActionManager;
import com._604robotics.robotnik.action.ActionReference;
import com._604robotics.robotnik.data.DataManager;
import com._604robotics.robotnik.data.DataReference;
import com._604robotics.robotnik.memory.IndexedTable;
import com._604robotics.robotnik.trigger.TriggerManager;
import com._604robotics.robotnik.trigger.TriggerReference;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ModuleReference {
    private final String name;
    private final Module module;

    private final DataManager dataManager;
    private final TriggerManager triggerManager;
    private final ActionManager actionManager;
    
    private final Safety safety;
    
    public ModuleReference (String name, Module module, IndexedTable table, Safety safety) {
        this.dataManager = new DataManager(name, module.getDataMap(), table.getSubTable("data"), safety);
        this.triggerManager = new TriggerManager(name, module.getTriggerMap(), table.getSubTable("triggers"), safety);
        
        this.actionManager = new ActionManager(this, name, module.getActionController(), table.getSubTable("actions"), safety);
        
        this.name = name;
        this.module = module;
        
        this.safety = safety;
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
    
    public void start () {
        safety.wrap("module " + name + " begin phase", module::begin);
    }
    
    public void update () {
        this.dataManager.update();
        this.triggerManager.update();
        
        this.actionManager.reset();
    }

    public void execute () {
        this.actionManager.update();
        this.actionManager.execute();
    }
    
    public void stop () {
        this.actionManager.stop(safety);
        safety.wrap("module " + name + " end phase", module::end);
    }
}