package com._604robotics.robotnik.action;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract class ActionController {
    private final Map<String, Action> actions = new HashMap<>();

    private String defaultAction = "";
    
    protected abstract String pickAction (String lastAction, String triggeredAction);
    
    public void add (String name, Action action) {
        actions.put(name, action);
    }
    
    public void add (String name) {
        this.add(name, new Action());
    }

    public void addDefault (String name, Action action) {
        add(name, action);
        defaultAction = name;
    }
    
    public void addDefault (String name) {
        addDefault(name, new Action());
    }
    
    protected String getDefaultAction () {
        return this.defaultAction;
    }
    
    protected Action getAction (String name) {
        return actions.get(name);
    }
    
    protected String[] getNames () {
        return actions.keySet().toArray(new String[actions.keySet().size()]);
    }
    
    protected Set<Entry<String, Action>> getActions () {
        return actions.entrySet();
    }
}