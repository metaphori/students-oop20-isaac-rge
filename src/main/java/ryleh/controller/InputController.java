package ryleh.controller;

import javafx.scene.Scene;
import ryleh.model.components.PhysicsComponent;
import ryleh.model.physics.Direction;
import ryleh.view.PlayerGraphicComponent;

public class InputController {

	private boolean isMoveUp;
	private boolean isMoveDown;
	private boolean isMoveLeft;
	private boolean isMoveRight;
	private final PlayerGraphicComponent graphic;
	private final PhysicsComponent physics; 
	private final Scene scene;
	private final Entity player;
	
	public InputController(final Scene scene, final Entity player) {
		this.scene = scene;
		this.player = player;
		this.graphic = (PlayerGraphicComponent) this.player.getView();
		this.physics = (PhysicsComponent) this.player.getGameObject()
		        .getComponent(PhysicsComponent.class).get();
	}
	
	public void initInput(){
		this.scene.setOnKeyPressed(key -> {
			switch (key.getCode()) {
			case A: isMoveLeft = true;
				break;
			case D: isMoveRight = true;
				break;
			case W: isMoveUp = true;
				break;
			case S: isMoveDown = true;
				break;
			default:
				break;
			}
		});
		scene.setOnKeyReleased(key -> {
			switch (key.getCode()) {
			case A: isMoveLeft = false;
				break;
			case D: isMoveRight = false;
				break;
			case W: isMoveUp = false;
				break;
			case S: isMoveDown = false;
			default:
				break;
			}
		});
	}
	public void updateInput() {
		if (isMoveUp) {
			physics.setVelocity(Direction.UP.getPoint());
			physics.setDirection(Direction.UP);
			if(!physics.getBlocked().equals(Direction.UP)) {
			    physics.resetBlocked();
			}
			graphic.setDirection(Direction.UP);
		} else if (isMoveDown) {
			physics.setVelocity(Direction.DOWN.getPoint());
			physics.setDirection(Direction.DOWN);
			if(!physics.getBlocked().equals(Direction.DOWN)) {
                            physics.resetBlocked();
                        }
			graphic.setDirection(Direction.DOWN);
		} else if (isMoveLeft) {
			physics.setVelocity(Direction.LEFT.getPoint());
			physics.setDirection(Direction.LEFT);
			if(!physics.getBlocked().equals(Direction.LEFT)) {
                            physics.resetBlocked();
                        }
			graphic.setDirection(Direction.LEFT);
		} else if (isMoveRight) {
			physics.setVelocity(Direction.RIGHT.getPoint());
			physics.setDirection(Direction.RIGHT);
			if(!physics.getBlocked().equals(Direction.RIGHT)) {
                            physics.resetBlocked();
                        }
			graphic.setDirection(Direction.RIGHT);	
		} else {
			physics.setVelocity(Direction.IDLE.getPoint());
			physics.setDirection(Direction.IDLE);
			graphic.setDirection(Direction.IDLE);
		}
	}
}
