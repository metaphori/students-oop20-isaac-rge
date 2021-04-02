package ryleh.controller;

import javafx.scene.Scene;

public class PlayerInputController {
	public PlayerInputController(final Scene scene) {
		scene.setOnKeyPressed(key -> {
			switch (key.getCode()) {
			case A: 
				break;
			case D:
				break;
			case W:
				break;
			case S:
				break;
			default:
				break;
			}
		});
		scene.setOnKeyReleased(key -> {
			switch (key.getCode()) {
			case A: 
				break;
			case D:
				break;
			case W:
				break;
			case S:
				break;
			default:
				break;
			}
		});
	}
}
