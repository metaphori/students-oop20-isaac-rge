package ryleh.controller;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import ryleh.core.GameState;
import ryleh.model.World;
import ryleh.model.components.PhysicsComponent;
import ryleh.model.components.ShootingComponent;
import ryleh.model.events.NewLevelEvent;
import ryleh.model.physics.Direction;
import ryleh.view.PlayerGraphicComponent;

public class InputController {
	
	private boolean isMoveUp;
	private boolean isMoveDown;
	private boolean isMoveLeft;
	private boolean isMoveRight;
	private boolean isShooting;
	private boolean newLevel;
	private Direction lastDir;
	private final PlayerGraphicComponent graphic;
	private final PhysicsComponent physics; 
	private final Scene scene;
	private final Entity player;
	private final World world;
	
	public InputController(final GameState state) {
		this.scene = state.getView().getScene();
		this.world = state.getWorld();
		this.player = state.getPlayer();
		this.graphic = (PlayerGraphicComponent) this.player.getView();
		this.physics = (PhysicsComponent) this.player.getGameObject()
		        .getComponent(PhysicsComponent.class).get();
		this.lastDir = Direction.UP;
	}
	
	public void initInput(){
		this.scene.setOnKeyPressed(key -> {
			this.setMoving(key.getCode(), true);
		});
		scene.setOnKeyReleased(key -> {
			this.setMoving(key.getCode(), false);
		});
	}
	public void updateInput() {
		if (newLevel) {
			world.notifyWorldEvent(new NewLevelEvent(this.player.getGameObject()));
		}
		if (isShooting) {
			if (this.physics.getDirection().equals(Direction.IDLE)) {
				this.shoot(this.lastDir);
			} else {
				this.shoot(this.physics.getDirection());
			}
		}
		this.move(isMoveDown, Direction.DOWN);
		this.move(isMoveUp, Direction.UP);
		this.move(isMoveRight, Direction.RIGHT);
		this.move(isMoveLeft, Direction.LEFT);
		if (this.notMoving()) {
			this.physics.setVelocity(Direction.IDLE.getPoint());
			this.physics.setDirection(Direction.IDLE);
			this.graphic.setDirection(Direction.IDLE);
			this.physics.resetBlocked();
		}
	}

	private void setMoving(final KeyCode key, final boolean isMoving) {
		switch (key) {
		case A: this.isMoveLeft = isMoving;
			break;
		case D: this.isMoveRight = isMoving;
			break;
		case W: this.isMoveUp = isMoving;
			break;
		case S: this.isMoveDown = isMoving;
			break;
		case L: this.newLevel = isMoving;
			break;
		case SPACE: this.isShooting = isMoving;
		break;
		default:
			break;
		}
	}
	private void shoot(final Direction direction) {
		((ShootingComponent) player.getGameObject().getComponent(ShootingComponent.class).get())
		    .shoot(direction.getPoint());
	}
	
	private void move(final boolean isMoving, final Direction direction) {
		if (isMoving) {
			this.lastDir = direction;
			this.physics.setVelocity(direction.getPoint());
			this.physics.setDirection(direction);
			if (!this.physics.getBlocked().equals(direction)) {
                            this.physics.resetBlocked();
                        }
			this.graphic.setDirection(direction);
		} 
	}
	
	private boolean notMoving() {
		return !this.isMoveDown && !this.isMoveUp && !this.isMoveLeft && !this.isMoveRight;
	}
}
