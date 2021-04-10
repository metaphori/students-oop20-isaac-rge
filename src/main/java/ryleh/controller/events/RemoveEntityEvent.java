package ryleh.controller.events;

import ryleh.model.GameObject;

public class RemoveEntityEvent extends AbstractEvent {

	protected RemoveEntityEvent(GameObject target) {
		super(target);
	}

	@Override
	void handle() {
		// TODO Auto-generated method stub

	}

}
