package ryleh.model.events;

import ryleh.model.GameObject;

public class GameOverEvent extends AbstractEvent {

	public GameOverEvent(GameObject target) {
		super(target);
	}

	@Override
	public void handle() {
		//cambio di scena e arrivo della scritta gameOver
		//System.exit(0);
	}

}
