package ryleh.controller;

import ryleh.model.GameObject;

public abstract class AbstractEvent implements Event {
	
	private GameObject target;
	
	protected AbstractEvent(final GameObject target) {
		this.target = target;
	}
	public GameObject getPlayer() {
		return this.target;
	}
	abstract void handle();
}
