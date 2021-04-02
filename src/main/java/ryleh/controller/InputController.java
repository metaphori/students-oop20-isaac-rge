package ryleh.controller;

import javafx.scene.Scene;
import ryleh.model.components.PhysicsComponent;

public class InputController {
	public static void initInput(final Scene scene, final Entity entity){
		final PhysicsComponent physics = (PhysicsComponent) entity.getGameObject()
		        .getComponent(PhysicsComponent.class).get();
		scene.setOnKeyPressed(key -> {
			switch (key.getCode()) {
			//TODO change to V2d indication (adesso c'è il metodo cape)
			case A: physics.setVelocityX(-1);
				break;
			case D: physics.setVelocityX(1);
				break;
			case W: physics.setVelocityY(-1);
				break;
			case S: physics.setVelocityY(1);
				break;
			default:
				break;
			}
		});
		scene.setOnKeyReleased(key -> {
			switch (key.getCode()) {
			case A:  physics.setVelocityX(0);
				break;
			case D:  physics.setVelocityX(0);
				break;
			case W:  physics.setVelocityY(0);
				break;
			case S: physics.setVelocityY(0);
				break;
			default:
				break;
			}
		});
	}
}
