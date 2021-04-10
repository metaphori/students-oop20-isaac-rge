package ryleh.controller.events;

import ryleh.model.GameObject;

public class RemoveEntityEvent extends AbstractEvent {

	public RemoveEntityEvent(GameObject target) {
		super(target);
	}

	@Override
	void handle() {
		// TODO Auto-generated method stub

	}

}
