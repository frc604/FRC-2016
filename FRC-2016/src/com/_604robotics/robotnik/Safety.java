package com._604robotics.robotnik;

public enum Safety {
	ENABLED, DISABLED;
	
	public boolean enabled () {
		return this == ENABLED;
	}
	
	public boolean disabled () {
		return this == DISABLED;
	}
}
