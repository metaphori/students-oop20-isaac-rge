package ryleh.view;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PlayerGraphicComponent implements GraphicComponent {
	
	private Rectangle rectangle;
	
	public PlayerGraphicComponent() {
		rectangle = new Rectangle();
		rectangle.setFill(new ImagePattern(new Image("PlayerDown.png")));
	}
	
	private void updateImage( ? direction) {
		switch(direction) {
		case RIGHT:
			rectangle.setFill(new ImagePattern(new Image("PlayerRight.png")));
			break;
			
		case LEFT:
			rectangle.setFill(new ImagePattern(new Image("PlayerLeft.png")));
			break;
			
		case UP:
			rectangle.setFill(new ImagePattern(new Image("PlayerUp.png")));
			break;
			
		case DOWN:
			rectangle.setFill(new ImagePattern(new Image("PlayerDown.png")));
			break;
			
		default:
			break;
		}
	}
	
	@Override
	public void onAdded(final Scene scene) {
		scene.getRoot().getChildren().add(rectangle);
	}

	@Override
	public void render(final Point2D position) { // onUpdate
		rectangle.setX(position.getX());
		rectangle.setY(position.getY());
		this.updateImage( ? direction); // aggiorna la texture in base alla direzione in cui si sta muovendo (eventualmente con animazione)
	}
	
	@Override
	public void render() {


	}

}
