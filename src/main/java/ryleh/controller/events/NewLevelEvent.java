package ryleh.controller.events;

import ryleh.core.GameState;

public class NewLevelEvent implements Event {
	
	public NewLevelEvent() {
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void handle(final GameState state) {
        state.generateNewLevel();
        state.getView().getLevel().setText("Level: " + state.getLevelHandler().getnRooms());
	}
	
}
