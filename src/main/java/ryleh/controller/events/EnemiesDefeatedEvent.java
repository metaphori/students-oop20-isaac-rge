package ryleh.controller.events;

import ryleh.controller.core.GameState;

public class EnemiesDefeatedEvent implements Event {
	
	/**
	 * {@inheritDoc}
	 * Spawns a new item and a new door after all enemies are defeated
	 */
	@Override
	public void handle(final GameState state) {
		state.getLevelHandler().spawnItem();
        state.getLevelHandler().spawnDoor();
	}
}
