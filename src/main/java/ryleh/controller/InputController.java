package ryleh.controller;

public interface InputController {

    /**
     * Initializes input keys and the actions to perform when a key is pressed or
     * released.
     */
    void initInput();

    /**
     * Actions to perform every loop.
     */
    void updateInput();
}
