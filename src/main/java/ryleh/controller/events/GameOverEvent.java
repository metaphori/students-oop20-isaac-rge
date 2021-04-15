package ryleh.controller.events;

import ryleh.core.GameState;

public class GameOverEvent implements Event {
    /**
     * {@inheritDoc} Sets the game over view
     */
    @Override
    public void handle(final GameState state) {
        state.callGameOver();
    }

}
