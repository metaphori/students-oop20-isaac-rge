package ryleh.view;

import java.util.List;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import ryleh.model.physics.Direction;

/**
 * A class that provides the GraphicComponent of the view related to the Player Entity.
 */
public class PlayerGraphicComponent implements GraphicComponent {
	private Rectangle rectangle;
	private Direction direction;
	private Direction lastDir;
	private FadeTransition playerFade;
	private Boolean invincible;
	private static final int ANIM_DURATION = 5;
	private static final int FADE_DURATION = 200;
	
	private AnimationLoop animRight = new AnimationLoop(
			List.of(Textures.PLAYER_RIGHT.getImagePattern(), Textures.PLAYER_RIGHT2.getImagePattern(), Textures.PLAYER_RIGHT.getImagePattern(), Textures.PLAYER_RIGHT4.getImagePattern()), ANIM_DURATION);
	private AnimationLoop animLeft = new AnimationLoop(
			List.of(Textures.PLAYER_LEFT.getImagePattern(), Textures.PLAYER_LEFT2.getImagePattern(), Textures.PLAYER_LEFT.getImagePattern(), Textures.PLAYER_LEFT4.getImagePattern()), ANIM_DURATION);
	private AnimationLoop animUp = new AnimationLoop(
			List.of(Textures.PLAYER_UP.getImagePattern(), Textures.PLAYER_UP2.getImagePattern(), Textures.PLAYER_UP.getImagePattern(), Textures.PLAYER_UP4.getImagePattern()), ANIM_DURATION);
	private AnimationLoop animDown = new AnimationLoop(
			List.of(Textures.PLAYER_DOWN.getImagePattern(), Textures.PLAYER_DOWN2.getImagePattern(), Textures.PLAYER_DOWN.getImagePattern(), Textures.PLAYER_DOWN4.getImagePattern()), ANIM_DURATION);
	
	/**
	 * Creates a new Instance of PlayerGraphicComponent.
	 */
	public PlayerGraphicComponent() {
		this.rectangle = new Rectangle(Textures.PLAYER_DOWN.getWidth(), Textures.PLAYER_DOWN.getHeight());
		this.rectangle.setFill(Textures.PLAYER_DOWN.getImagePattern());
	
		this.playerFade = new FadeTransition(Duration.millis(FADE_DURATION), rectangle);
		this.playerFade.setFromValue(1.0);
		this.playerFade.setToValue(0.0);
		this.playerFade.setCycleCount(4);
		this.playerFade.setAutoReverse(true);
	
		this.invincible = false;
		this.direction = Direction.IDLE;
		this.lastDir = Direction.IDLE;
	}
	
	/**
	 * Creates a new Instance of PlayerGraphicComponent with the given initial position.
	 * @param position The position at witch the PlayerGraphicComponent needs to be inizialized.
	 */
	public PlayerGraphicComponent(final Point2D position) {
		this.rectangle = new Rectangle(Textures.PLAYER_DOWN.getWidth(), Textures.PLAYER_DOWN.getHeight());
		this.rectangle.setX(position.getX() - rectangle.getWidth() / 2);
		this.rectangle.setY(position.getY() - rectangle.getHeight() / 2);
		this.rectangle.setFill(Textures.PLAYER_DOWN.getImagePattern());

		this.playerFade = new FadeTransition(Duration.millis(FADE_DURATION), rectangle);
	    this.playerFade.setFromValue(1.0);
	    this.playerFade.setToValue(0.0);
	    this.playerFade.setCycleCount(4);
	    this.playerFade.setAutoReverse(true);

	    this.invincible = false;
		this.direction = Direction.IDLE;
		this.lastDir = Direction.IDLE;
	}

	/**
	 * A method to update the state of this GraphicComponent in the view.
	 * @param direction The direction at which the entity is in the current GameLoop, to determine the right animation.
	 */
	private void updateImage(final Direction direction) {
		switch (direction) {
		case RIGHT:
			if (!rectangle.getFill().equals(Textures.PLAYER_RIGHT.getImagePattern()) && lastDir != Direction.RIGHT) {
				rectangle = animRight.setFrame(rectangle);
				animRight.resetTimer();
				lastDir = Direction.RIGHT;
			} else {
				animRight.incTimer();
				rectangle = animRight.setFrame(rectangle);
			}
			break;

		case LEFT:
			if (!rectangle.getFill().equals(Textures.PLAYER_LEFT.getImagePattern()) && lastDir != Direction.LEFT) {
				rectangle = animLeft.setFrame(rectangle);
				animLeft.resetTimer();
				lastDir = Direction.LEFT;
			} else {
				animLeft.incTimer();
				rectangle = animLeft.setFrame(rectangle);
			}
			break;

		case UP:
			if (!rectangle.getFill().equals(Textures.PLAYER_UP.getImagePattern()) && lastDir != Direction.UP) {
				rectangle = animUp.setFrame(rectangle);
				animUp.resetTimer();
				lastDir = Direction.UP;
			} else {
				animUp.incTimer();
				rectangle = animUp.setFrame(rectangle);
			}
			break;

		case DOWN:
			if (!rectangle.getFill().equals(Textures.PLAYER_DOWN.getImagePattern()) && lastDir != Direction.DOWN) {
				rectangle = animDown.setFrame(rectangle);
				animDown.resetTimer();
				lastDir = Direction.DOWN;
			} else {
				animDown.incTimer();
				rectangle = animDown.setFrame(rectangle);
			}
			break;

		case IDLE:
			switch (lastDir) {
			case RIGHT:
				rectangle.setFill(Textures.PLAYER_RIGHT.getImagePattern());
				break;
			case LEFT:
				rectangle.setFill(Textures.PLAYER_LEFT.getImagePattern());
				break;
			case DOWN:
				rectangle.setFill(Textures.PLAYER_DOWN.getImagePattern());
				break;
			case UP:
				rectangle.setFill(Textures.PLAYER_UP.getImagePattern());
				break;
			default:
				break;
			}
		default:
			break;
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onAdded(final Scene scene) {
	    Parent root = scene.getRoot();
        ((AnchorPane) root).getChildren().add(rectangle);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void render(final Point2D position, final double deltaTime) {
		rectangle.setX(position.getX() - rectangle.getWidth() / 2);
		rectangle.setY(position.getY() - rectangle.getHeight() / 2);
		this.checkInvincible();
		this.updateImage(direction);
	}
	
	/**
	 * A method to check if the Invincible animations needs to be played.
	 */
	private void checkInvincible() {
		if (invincible) {
			playerFade.play();
		} 
	}
	
	/**
	 * Sets the invincible field with the given parameter.
	 * @param invincible The value at which the field invincible is to be set.
	 */
	public void setInvincible(final Boolean invincible) {
		this.invincible = invincible;
	}

	/**
	 * Sets the direction field with the given parameter.
	 * @param direction The value at which the field direction is to be set.
	 */
	public void setDirection(final Direction direction) {
		this.direction = direction;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Rectangle getNode() {
		return rectangle;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onRemoved(final EventHandler<ActionEvent> event) {
		event.handle(null);
	}
}
