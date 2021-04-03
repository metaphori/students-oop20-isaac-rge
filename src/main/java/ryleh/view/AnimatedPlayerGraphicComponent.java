package ryleh.view;

import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import ryleh.model.physics.Direction;

public class AnimatedPlayerGraphicComponent implements GraphicComponent{

private Rectangle rectangle;


	/*
	//blocco per generare una direzione a caso finchè non posso ottenerla in modo certo
	//private Direction direction = Direction.IDLE;
	private Direction getRandomDirection() {
		Random r = new Random();
		int random;
		random = r.nextInt(4);
		switch (random) {
		case 0:
			return Direction.UP;
		case 1:
			return Direction.DOWN;
		case 2:
			return Direction.LEFT;
		case 3:
			return Direction.RIGHT;
		default:
			return Direction.IDLE;
		}
	}
	*/
	
	
	
	public AnimatedPlayerGraphicComponent() {
		rectangle = new Rectangle(190, 190);
		rectangle.setFill(Textures.PLAYER_DOWN.getImagePattern());
	}
	
	int currentTime = 0;
	Direction lastDir = Direction.IDLE;
	
	private void updateImage(final Direction direction) {
		switch (direction) {
		case RIGHT:
			if (!rectangle.getFill().equals(Textures.PLAYER_RIGHT.getImagePattern()) && lastDir != Direction.RIGHT) {
				rectangle.setFill(Textures.PLAYER_RIGHT.getImagePattern());
				lastDir = Direction.RIGHT;
				currentTime = 0;
			} else {
				switch (currentTime) {
				case 4:
					rectangle.setFill(Textures.PLAYER_RIGHT.getImagePattern());
					break;
				case 8:
					rectangle.setFill(Textures.PLAYER_RIGHT2.getImagePattern());
					break;
				case 12:
					rectangle.setFill(Textures.PLAYER_RIGHT.getImagePattern());
					break;
				case 16:
					rectangle.setFill(Textures.PLAYER_RIGHT4.getImagePattern());
					break;
				case 17:
					currentTime = 0;
					break;
				default:
					break;
				}
			}
			break;

		case LEFT:
			if (!rectangle.getFill().equals(Textures.PLAYER_LEFT.getImagePattern())) {
				rectangle.setFill(Textures.PLAYER_LEFT.getImagePattern());
				lastDir = Direction.LEFT;
				currentTime = 0;
			} else {
				switch (currentTime) {
				case 4:
					rectangle.setFill(Textures.PLAYER_LEFT.getImagePattern());
					break;
				case 8:
					rectangle.setFill(Textures.PLAYER_LEFT2.getImagePattern());
					break;
				case 12:
					rectangle.setFill(Textures.PLAYER_LEFT.getImagePattern());
					break;
				case 16:
					rectangle.setFill(Textures.PLAYER_LEFT4.getImagePattern());
					break;
				case 17:
					currentTime = 0;
					break;
				default:
					break;
				}
			}
			break;

		case UP:
			if (!rectangle.getFill().equals(Textures.PLAYER_UP.getImagePattern())) {
				rectangle.setFill(Textures.PLAYER_UP.getImagePattern());
				lastDir = Direction.UP;
				currentTime = 0;
			} else {
				switch (currentTime) {
				case 4:
					rectangle.setFill(Textures.PLAYER_UP.getImagePattern());
					break;
				case 8:
					rectangle.setFill(Textures.PLAYER_UP2.getImagePattern());
					break;
				case 12:
					rectangle.setFill(Textures.PLAYER_UP.getImagePattern());
					break;
				case 16:
					rectangle.setFill(Textures.PLAYER_UP4.getImagePattern());
					break;
				case 17:
					currentTime = 0;
					break;
				default:
					break;
				}
			}
			break;

		case DOWN:
			if (!rectangle.getFill().equals(Textures.PLAYER_DOWN.getImagePattern())) {
				rectangle.setFill(Textures.PLAYER_DOWN.getImagePattern());
				lastDir = Direction.DOWN;
				currentTime = 0;
			} else {
				switch (currentTime) {
				case 4:
					rectangle.setFill(Textures.PLAYER_DOWN.getImagePattern());
					break;
				case 8:
					rectangle.setFill(Textures.PLAYER_DOWN2.getImagePattern());
					break;
				case 12:
					rectangle.setFill(Textures.PLAYER_DOWN.getImagePattern());
					break;
				case 16:
					rectangle.setFill(Textures.PLAYER_DOWN4.getImagePattern());
					break;
				case 17:
					currentTime = 0;
					break;
				default:
					break;
				}
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
	public void render(final Point2D position) {
		rectangle.setX(position.getX());
		rectangle.setY(position.getY());
		System.out.println(rectangle);
		currentTime = currentTime + 1;
		this.updateImage(Direction.RIGHT);
	}
	
	@Override
	public void render() {
	}
}
