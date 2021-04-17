package ryleh.model.items;

import ryleh.controller.core.GameState;

public interface Item {

    /**
     * Performs some actions depending on the Item type.
     * @param state
     */
    void apply(GameState state);
}
