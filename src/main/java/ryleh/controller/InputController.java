package ryleh.controller;

import javafx.scene.Scene;
import ryleh.model.components.PhysicsComponent;
import ryleh.model.physics.Direction;
import ryleh.view.PlayerGraphicComponent;

public class InputController {
	public static void initInput(final Scene scene, final Entity entity){
		PlayerGraphicComponent graphic = (PlayerGraphicComponent) entity.getView();
		final PhysicsComponent physics = (PhysicsComponent) entity.getGameObject()
		        .getComponent(PhysicsComponent.class).get();
		scene.setOnKeyPressed(key -> {
			switch (key.getCode()) {
			//TODO change to V2d indication (adesso c'è il metodo cape)
			case A: physics.setVelocityX(-1);
					physics.setDirection(Direction.LEFT);
					graphic.setDirection(Direction.LEFT);
				break;
			case D: physics.setVelocityX(1);
					physics.setDirection(Direction.RIGHT);
					graphic.setDirection(Direction.RIGHT);
				break;
			case W: physics.setVelocityY(-1);
					physics.setDirection(Direction.UP);
					graphic.setDirection(Direction.UP);
				break;
			case S: physics.setVelocityY(1);
					physics.setDirection(Direction.DOWN);
					graphic.setDirection(Direction.DOWN);
				break;
			default:
				break;
			}
		});
		scene.setOnKeyReleased(key -> {
			switch (key.getCode()) {
			case A:  physics.setVelocityX(0);
					physics.setDirection(Direction.IDLE);
					graphic.setDirection(Direction.IDLE);
				break;
			case D:  physics.setVelocityX(0);
					physics.setDirection(Direction.IDLE);
					graphic.setDirection(Direction.IDLE);
				break;
			case W:  physics.setVelocityY(0);
					physics.setDirection(Direction.IDLE);
					graphic.setDirection(Direction.IDLE);
				break;
			case S: physics.setVelocityY(0);
					physics.setDirection(Direction.IDLE);
					graphic.setDirection(Direction.IDLE);
				break;
			default:
				break;
			}
		});
	}
}
