package ryleh.controller.events;

import ryleh.core.GameState;

public class EnemiesDefeatedEvent implements Event {
	
	public EnemiesDefeatedEvent() {
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void handle(final GameState state) {
		state.getLevelHandler().spawnItem();
        state.getLevelHandler().spawnDoor();
	}
}
