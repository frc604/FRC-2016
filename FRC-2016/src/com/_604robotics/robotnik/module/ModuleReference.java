package com._604robotics.robotnik.module;

import com._604robotics.robotnik.Safety;
import com._604robotics.robotnik.action.ActionManager;
import com._604robotics.robotnik.action.ActionReference;
import com._604robotics.robotnik.data.DataManager;
import com._604robotics.robotnik.data.DataReference;
import com._604robotics.robotnik.memory.IndexedTable;
import com._604robotics.robotnik.trigger.TriggerManager;
import com._604robotics.robotnik.trigger.TriggerReference;

/**
 * A reference to a module.
 */
public class ModuleReference {
    private final String name;
    private final Module module;

    private final DataManager dataManager;
    private final TriggerManager triggerManager;
    private final ActionManager actionManager;
    
    private final Safety safety;
    
    /**
     * Creates a module reference.
     * @param name Name of the module.
     * @param module Module to refer to.
     * @param table Table to store module data in.
     * @param safety Safety mode to operate under.
     */
    public ModuleReference (String name, Module module, IndexedTable table, Safety safety) {
        this.dataManager = new DataManager(module.getDataMap(), table.getSubTable("data"), safety);
        this.triggerManager = new TriggerManager(module.getTriggerMap(), table.getSubTable("triggers"), safety);
        
        this.actionManager = new ActionManager(this, module.getActionController(), table.getSubTable("actions"), safety);
        
        this.name = name;
        this.module = module;
        
        this.safety = safety;
    }
    
    /**
     * Gets data belonging to the module.
     * @param name Name of the data.
     * @return The retrieved data.
     */
    public DataReference getData (String name) {
        return this.dataManager.getData(name);
    }
    
    /**
     * Gets a trigger belonging to the module.
     * @param name Name of the trigger.
     * @return The retrieved trigger.
     */
    public TriggerReference getTrigger (String name) {
        return this.triggerManager.getTrigger(name);
    }
    
    /**
     * Gets an action belonging to the module.
     * @param name Name of the action.
     * @return The retrieved action.
     */
    public ActionReference getAction (String name) {
        return this.actionManager.getAction(name);
    }
    
    /**
     * Starts the module.
     */
    public void start () {
        safety.wrap("module " + name + " begin phase", module::begin);
    }
    
    /**
     * Updates the module.
     */
    public void update () {
        this.dataManager.update();
        this.triggerManager.update();
        
        this.actionManager.reset();
    }

    /**
     * Executes the selected action of the module.
     */
    public void execute () {
        this.actionManager.update();
        this.actionManager.execute();
    }
    
    /**
     * Stops the module.
     */
    public void stop () {
        this.actionManager.stop();
        safety.wrap("module " + name + " end phase", module::end);
    }
}