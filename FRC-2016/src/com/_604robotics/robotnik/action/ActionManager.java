package com._604robotics.robotnik.action;

import com._604robotics.robotnik.Safety;
import com._604robotics.robotnik.memory.IndexedTable;
import com._604robotics.robotnik.logging.InternalLogger;
import com._604robotics.robotnik.module.ModuleReference;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ActionManager {
    private final String moduleName;
    private final ActionController controller;
    private final IndexedTable triggerTable;
    private final IndexedTable statusTable;
    private final Map<String, ActionReference> actionTable;

    public ActionManager (final ModuleReference module, String moduleName, ActionController controller, final IndexedTable table) {
        this.moduleName = moduleName;
        
        this.controller = controller;
        
        this.triggerTable = table.getSubTable("triggers");
        
        this.statusTable = table.getSubTable("status");
        this.statusTable.putString("triggeredAction", "");
        this.statusTable.putString("lastAction", "");
        
        final IndexedTable dataTable = table.getSubTable("data");
        this.actionTable = new HashMap<String, ActionReference>();
        for(Map.Entry<String, Action> entry : controller) {
            this.actionTable.put(entry.getKey(), new ActionReference(module, entry.getValue(), this.triggerTable.getSlice(entry.getKey()), dataTable.getSubTable(entry.getKey())));
        }
    }

    public ActionReference getAction (String name) {
        ActionReference ref = this.actionTable.get(name);
        if (ref == null) InternalLogger.missing("ActionReference", name);
        return ref;
    }

    public void reset () {
        final Iterator<Map.Entry<String, ActionReference>> i = this.actionTable.entrySet().iterator();
        while (i.hasNext()) i.next().getValue().reset();
    }

    public void update () {
        double score = 0;
        String action = "";
        for (Map.Entry<String, Action> entry : this.controller) {
            double currScore = this.triggerTable.getNumber(entry.getKey(), 0D);
            if (currScore > score) {
                score = currScore;
                action = entry.getKey();
            }
        }
        
        this.statusTable.putString("triggeredAction", action);
    }

    public void execute (Safety safety) {
        final String triggeredAction = this.statusTable.getString("triggeredAction", "");
        final String lastAction = this.statusTable.getString("lastAction", "");
        
        final String selectedAction = this.controller.pickAction(lastAction, triggeredAction);
        
        if (!lastAction.equals("") && !lastAction.equals(selectedAction)) {
            getAction(lastAction).end(safety);
        }

        if (!selectedAction.equals("")) {
            final ActionReference action = this.getAction(selectedAction);
            if (lastAction.equals("") || !lastAction.equals(selectedAction)) {
                action.begin(safety);
            }
            action.run(safety);
        }
        
        this.statusTable.putString("lastAction", selectedAction);
    }

    public void stop (Safety safety) {
        final String lastAction = this.statusTable.getString("lastAction", "");
        
        if (!lastAction.equals("")) {
            getAction(lastAction).end(safety);
        }
        
        this.statusTable.putString("lastAction", "");
    }
}
