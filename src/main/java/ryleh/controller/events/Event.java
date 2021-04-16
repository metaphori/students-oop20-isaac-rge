package ryleh.controller.events;

import ryleh.core.GameState;

public interface Event {
	
	/**
	 * Perform some actions depending on the specified event.
	 * @param state Is the actual state of the game;
	 */
	void handle(GameState state);
}
