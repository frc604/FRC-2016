package com._604robotics.robotnik.action.controllers;

import com._604robotics.robotnik.action.ActionController;

/**
 * A dummy controller, running no actions.
 */
public class DummyController extends ActionController {
    /* (non-Javadoc)
     * @see com._604robotics.robotnik.action.ActionController#pickAction(java.lang.String, java.lang.String)
     */
    protected String pickAction (String lastAction, String triggeredAction) {
        return "";
    }
}
