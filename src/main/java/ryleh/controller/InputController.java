package ryleh.controller;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import ryleh.common.Timer;
import ryleh.controller.events.BulletSpawnEvent;
import ryleh.controller.events.NewLevelEvent;
import ryleh.core.GameState;
import ryleh.model.World;
import ryleh.model.components.PhysicsComponent;
import ryleh.model.physics.Direction;
import ryleh.view.PlayerGraphicComponent;

public class InputController {

	private static int shootingDelay = 500;
	
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
	private World world;
	private Timer timer;
	
	public InputController(final GameState state) {
		this.scene = state.getView().getScene();
		this.world = state.getWorld();
		this.player = state.getPlayer();
		this.graphic = (PlayerGraphicComponent) this.player.getView();
		this.physics = (PhysicsComponent) this.player.getGameObject()
		        .getComponent(PhysicsComponent.class).get();
		this.timer = new Timer(shootingDelay);
		this.timer.startTimer();
		this.lastDir = Direction.IDLE;
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
		if (this.canShoot()) {
			if (this.physics.getDirection().equals(Direction.IDLE)) {
				this.shoot(this.lastDir);
			} else {
				this.shoot(this.physics.getDirection());
			}
			graphic.setDirection(Direction.UP);
		} else if (isMoveDown) {
			physics.setVelocity(Direction.DOWN.getPoint());
			physics.setDirection(Direction.DOWN);
			if (!physics.getBlocked().equals(Direction.DOWN)) {
                            physics.resetBlocked();
                        }
			graphic.setDirection(Direction.DOWN);
		} else if (isMoveLeft) {
			physics.setVelocity(Direction.LEFT.getPoint());
			physics.setDirection(Direction.LEFT);
			if (!physics.getBlocked().equals(Direction.LEFT)) {
                            physics.resetBlocked();
                        }
			graphic.setDirection(Direction.LEFT);
		} else if (isMoveRight) {
			physics.setVelocity(Direction.RIGHT.getPoint());
			physics.setDirection(Direction.RIGHT);
			if (!physics.getBlocked().equals(Direction.RIGHT)) {
                            physics.resetBlocked();
                        }
			graphic.setDirection(Direction.RIGHT);	
		} else {
			physics.setVelocity(Direction.IDLE.getPoint());
			//physics.setDirection(Direction.IDLE);
			graphic.setDirection(Direction.IDLE);
		}
		this.move(isMoveDown, Direction.DOWN);
		this.move(isMoveUp, Direction.UP);
		this.move(isMoveRight, Direction.RIGHT);
		this.move(isMoveLeft, Direction.LEFT);
		if (this.notMoving()) {
			this.physics.setVelocity(Direction.IDLE.getPoint());
			this.physics.setDirection(Direction.IDLE);
			this.graphic.setDirection(Direction.IDLE);
		}
	}

	public void setShootingDelay(final int shootingDelay) {
		InputController.shootingDelay = shootingDelay;
	}

	private void setMoving(final KeyCode key, final boolean isMoving) {
		if (key.equals(KeyCode.SPACE)) {
			this.isShooting = isMoving;
		}
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
		default:
			break;
		}
	}
	private void shoot(final Direction direction) {
		world.notifyWorldEvent(new BulletSpawnEvent(this.player.getGameObject(), this.player.getGameObject().getPosition(), 
				direction.getPoint()));
		this.timer.startTimer();
	}
	
	private boolean canShoot() {
		return isShooting && timer.isElapsed();
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
