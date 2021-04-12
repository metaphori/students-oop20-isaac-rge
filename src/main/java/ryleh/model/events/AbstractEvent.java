package ryleh.model.events;

import ryleh.model.GameObject;

public abstract class AbstractEvent implements Event {
	
	private GameObject target;
	
	protected AbstractEvent(final GameObject target) {
		this.target = target;
	}
	public GameObject getTarget() {
		return this.target;
	}
	abstract void handle();
}
