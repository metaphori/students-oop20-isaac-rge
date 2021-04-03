package ryleh.view.other;

import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import ryleh.view.GraphicComponent;
import ryleh.view.Textures;

public class DoorGraphicComponent implements GraphicComponent{

	private Rectangle rectangle;
	
	public DoorGraphicComponent() {
		rectangle = new Rectangle(); // da settare dimensioni porta
		rectangle.setFill(Textures.DOOR.getImagePattern());
	}

	private void updateImage() {

	}
	
	@Override
	public void render() {
	}

	@Override
	public void render(final Point2D position) {
		rectangle.setX(position.getX());
		rectangle.setY(position.getY());
		this.updateImage();
	}

	@Override
	public void onAdded(final Scene scene) {
		Parent root=scene.getRoot();
        ((AnchorPane) root).getChildren().add(rectangle);
	}

}
