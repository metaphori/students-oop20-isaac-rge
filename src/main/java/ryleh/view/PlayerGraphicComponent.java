package ryleh.view;

import java.util.Random;

import javafx.animation.Animation;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import ryleh.model.physics.Direction;

public class PlayerGraphicComponent implements GraphicComponent {
	
	private Rectangle rectangle;

	
	//blocco per generare una direzione a caso finch� non posso ottenerla in modo certo
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
	
	
	
	
	public PlayerGraphicComponent() {
		rectangle = new Rectangle(190, 190);
		rectangle.setFill(Textures.PLAYER_DOWN.getImagePattern());
	}
	
	private void updateImage(final Direction direction) {
		switch (direction) {
		case RIGHT:
			if (!rectangle.getFill().equals(Textures.PLAYER_RIGHT.getImagePattern())) {
				rectangle.setFill(Textures.PLAYER_RIGHT.getImagePattern());
			}
			break;

		case LEFT:
			if (!rectangle.getFill().equals(Textures.PLAYER_LEFT.getImagePattern())) {
				rectangle.setFill(Textures.PLAYER_LEFT.getImagePattern());
			}
			break;

		case UP:
			if (!rectangle.getFill().equals(Textures.PLAYER_UP.getImagePattern())) {
				rectangle.setFill(Textures.PLAYER_UP.getImagePattern());
			}
			break;

		case DOWN:
			if (!rectangle.getFill().equals(Textures.PLAYER_DOWN.getImagePattern())) {
				rectangle.setFill(Textures.PLAYER_DOWN.getImagePattern());
			}
			break;

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
		this.updateImage(getRandomDirection());
	}
	
	@Override
	public void render() {
	}
}
