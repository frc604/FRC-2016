package com._604robotics.robotnik.action;

import java.util.HashMap;
import java.util.Map;

import com._604robotics.robotnik.Safety;
import com._604robotics.robotnik.logging.Logger;
import com._604robotics.robotnik.memory.IndexedTable;
import com._604robotics.robotnik.module.ModuleReference;

/**
 * Manages multiple actions.
 */
public class ActionManager {
    private final ActionController controller;

    private final IndexedTable triggerTable;
    private final IndexedTable statusTable;

    private final Map<String, ActionReference> actionTable;

    /**
     * Creates an action manager.
     * @param module Reference to the module this manager belongs to.
     * @param controller Controller to control action execution.
     * @param table Table to contain action data.
     */
    public ActionManager (final ModuleReference module, ActionController controller, final IndexedTable table, Safety safety) {
        this.controller = controller;
        
        triggerTable = table.getSubTable("triggers");
        
        statusTable = table.getSubTable("status");
        statusTable.putString("triggeredAction", "");
        statusTable.putString("lastAction", "");
        
        final IndexedTable dataTable = table.getSubTable("data");
        actionTable = new HashMap<String, ActionReference>();
        for (Map.Entry<String, Action> entry : controller) {
            actionTable.put(entry.getKey(), 
                    new ActionReference(module,
                            entry.getValue(),
                            triggerTable.getRow(entry.getKey()),
                            dataTable.getSubTable(entry.getKey()),
                            safety));
        }
    }

    /**
     * Gets an action belonging to this manager.
     * @param name Name of the action.
     * @return The retrieved action.
     */
    public ActionReference getAction (String name) {
        ActionReference ref = this.actionTable.get(name);
        if (ref == null) Logger.missing("ActionReference", name);
        return ref;
    }

    /**
     * Resets all actions belonging to this manager.
     */
    public void reset () {
        for (ActionReference ref : this.actionTable.values()) {
            ref.reset();
        }
    }

    /**
     * Updates this manager.
     */
    public void update () {
        double score = 0;
        String action = "";
        for(Map.Entry<String, Action> actionEntry : this.controller) {
            double currScore = this.triggerTable.getNumber(actionEntry.getKey(), 0);
            if(currScore > score) {
                score = currScore;
                action = actionEntry.getKey();
            }
        }
        
        this.statusTable.putString("triggeredAction", action);
    }
    
    /**
     * Chooses and executes an action from this manager.
     */
    public void execute () {
        final String triggeredAction = this.statusTable.getString("triggeredAction", "");
        final String lastAction = this.statusTable.getString("lastAction", "");
        
        final String selectedAction = this.controller.pickAction(lastAction, triggeredAction);
        
        if (!lastAction.equals("") && !lastAction.equals(selectedAction)) {
            getAction(lastAction).end();
        }

        if (!selectedAction.equals("")) {
            final ActionReference action = this.getAction(selectedAction);
            if (lastAction.equals("") || !lastAction.equals(selectedAction)) {
                action.begin();
            }
            action.end();
        }
        
        this.statusTable.putString("lastAction", selectedAction);
    }

    /**
     * Stops this manager's action execution.
     */
    public void stop () {
        final String lastAction = this.statusTable.getString("lastAction", "");
        
        if (!lastAction.equals("")) {
            getAction(lastAction).end();
        }
        
        this.statusTable.putString("lastAction", "");
    }
}