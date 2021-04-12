package ryleh.model.events;

import ryleh.model.GameObject;

public class RemoveEntityEvent extends AbstractEvent {

	public RemoveEntityEvent(GameObject target) {
		super(target);
	}
	@Override
	void handle() {

	}

}
