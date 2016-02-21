package com._604robotics.robotnik.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class ActionController implements Iterable<Map.Entry<String, Action>> {
    private final Map<String, Action> actionTable = new HashMap<String, Action>();
    private String defaultAction = "";

    protected abstract String pickAction (String lastAction, String triggeredAction);

    public void add (String name, Action action) {
        this.actionTable.put(name, action);
    }

    public void add (String name) {
        this.add(name, new Action());
    }

    public void addDefault (String name, Action action) {
        this.add(name, action);
        this.defaultAction = name;
    }

    public void addDefault (String name) {
        this.addDefault(name, new Action());
    }

    protected String getDefaultAction () {
        return this.defaultAction;
    }

    protected Action getAction (String name) {
        return this.actionTable.get(name);
    }

    @Override
    public Iterator<Map.Entry<String, Action>> iterator () {
        return this.actionTable.entrySet().iterator();
    }
}