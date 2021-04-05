package ryleh.view;

import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import ryleh.model.physics.Direction;

public class PlayerGraphicComponent implements GraphicComponent{

private Rectangle rectangle;
private Direction direction;
	
	public PlayerGraphicComponent() {
		rectangle = new Rectangle(190, 190);
		rectangle.setFill(Textures.PLAYER_DOWN.getImagePattern());
	}
	
	private Direction lastDir = Direction.IDLE;
	
	private AnimationLoop animRight = new AnimationLoop(
				List.of(Textures.PLAYER_RIGHT.getImagePattern(), Textures.PLAYER_RIGHT2.getImagePattern(), Textures.PLAYER_RIGHT.getImagePattern(), Textures.PLAYER_RIGHT4.getImagePattern()), 5);
	private AnimationLoop animLeft = new AnimationLoop(
				List.of(Textures.PLAYER_LEFT.getImagePattern(), Textures.PLAYER_LEFT2.getImagePattern(), Textures.PLAYER_LEFT.getImagePattern(), Textures.PLAYER_LEFT4.getImagePattern()), 5);
	private AnimationLoop animUp = new AnimationLoop(
				List.of(Textures.PLAYER_UP.getImagePattern(), Textures.PLAYER_UP2.getImagePattern(), Textures.PLAYER_UP.getImagePattern(), Textures.PLAYER_UP4.getImagePattern()), 5);
	private AnimationLoop animDown = new AnimationLoop(
				List.of(Textures.PLAYER_DOWN.getImagePattern(), Textures.PLAYER_DOWN2.getImagePattern(), Textures.PLAYER_DOWN.getImagePattern(), Textures.PLAYER_DOWN4.getImagePattern()), 5);

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

	@Override
	public void onAdded(final Scene scene) {
		Parent root = scene.getRoot();
        ((AnchorPane) root).getChildren().add(rectangle);
	}

	@Override
	public void render(final Point2D position, final int deltaTime) {
		rectangle.setX(position.getX());
		rectangle.setY(position.getY());
		this.updateImage(direction);
	}
	
	public void setDirection(final Direction direction) {
		this.direction = direction;
	}
}
