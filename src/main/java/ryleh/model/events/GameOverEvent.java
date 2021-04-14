package ryleh.model.events;

import ryleh.model.GameObject;

public class GameOverEvent extends AbstractEvent {

	public GameOverEvent(GameObject target) {
		super(target);
	}

	@Override
	public void handle() {	}

}
