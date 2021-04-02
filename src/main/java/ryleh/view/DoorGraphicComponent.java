package ryleh.view;

import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class DoorGraphicComponent implements GraphicComponent{

	private Rectangle rectangle;
	
	public DoorGraphicComponent() {
		rectangle = new Rectangle();
		rectangle.setFill(new ImagePattern(new Image("Door.png")));
	}

	private void updateImage() {

	}
	
	@Override
	public void render() {
	}

	@Override
	public void render(final Point2D position) { //onUpdate()
		rectangle.setX(position.getX());
		rectangle.setY(position.getY());
		this.updateImage();
	}

	@Override
	public void onAdded(final Scene scene) {
		scene.getRoot().getChildren().add(rectangle);
	}

}
