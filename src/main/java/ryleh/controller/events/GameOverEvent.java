package ryleh.controller.events;

import ryleh.model.GameObject;

public class GameOverEvent extends AbstractEvent {

	public GameOverEvent(GameObject target) {
		super(target);
	}

	@Override
	void handle() {
		//cambio di scena e arrivo della scritta gameOver
		//System.exit(0);
	}

}
