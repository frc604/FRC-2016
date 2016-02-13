package com._604robotics.robotnik.action;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com._604robotics.robotnik.Safety;
import com._604robotics.robotnik.logging.InternalLogger;
import com._604robotics.robotnik.meta.Scorekeeper;
import com._604robotics.robotnik.module.ModuleReference;
import com._604robotics.robotnik.networking.Row;

import edu.wpi.first.wpilibj.tables.ITable;

public class ActionManager {
    private final ActionController controller;

    private final ITable triggerTable;
    private final ITable statusTable;

    private final Map<String, ActionReference> actions = new HashMap<>();

    public ActionManager (final ModuleReference module, ActionController controller, final ITable table, Safety safety) {
        this.controller = controller;
        
        this.triggerTable = table.getSubTable("triggers");
        
        this.statusTable = table.getSubTable("status");
        this.statusTable.putString("triggeredAction", "");
        this.statusTable.putString("lastAction", "");
        
        final ITable dataTable = table.getSubTable("data");
        for (Entry<String, Action> action : controller.getActions()) {
            actions.put(action.getKey(), new ActionReference(module, action.getValue(), new Row(triggerTable, action.getKey()), dataTable.getSubTable(action.getKey()), safety));
        }
        
        table.putStringArray("__actions", controller.getNames());
    }
    
    public ActionReference getAction (String name) {
        if (!actions.containsKey(name)) {
            InternalLogger.missing("ActionReference", name);
            return null;
        } else {
            return actions.get(name);
        }
    }
    
    public void reset () {
        for (ActionReference action : actions.values()) {
            action.reset();
        }
    }
    
    public void update () {
        final Scorekeeper r = new Scorekeeper(0D);
        
        for (String actionName : actions.keySet()) {
            r.consider(actionName, this.triggerTable.getNumber(actionName, 0D));
        }
        
        this.statusTable.putString("triggeredAction", r.score > 0 ? (String) r.victor : "");
    }
    
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
    
    public void stop (Safety safety) {
        final String lastAction = this.statusTable.getString("lastAction", "");
        
        if (!lastAction.equals("")) {
            getAction(lastAction).end();
        }
        
        this.statusTable.putString("lastAction", "");
    }
}