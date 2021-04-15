package ryleh.controller.events;

import ryleh.core.GameState;

public class GameOverEvent implements Event {
	
	public GameOverEvent() {
	}

	@Override
	public void handle(final GameState state) {
		state.callGameOver();
	}

}
