package com._604robotics.robotnik;

import com._604robotics.robotnik.logging.Logger;

public enum Safety {
	ENABLED, DISABLED;
	
	public boolean enabled () {
		return this == ENABLED;
	}
	
	public boolean disabled () {
		return this == DISABLED;
	}

	public void wrap (String desc, Runnable todo) {
        if (enabled()) {
            try {
                todo.run();
            } catch (Exception e) {
                Logger.error("Caught exception " + desc, e);
            }
        } else {
            todo.run();
        }
    }
}
