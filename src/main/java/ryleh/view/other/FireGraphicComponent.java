package ryleh.view.other;

import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import ryleh.view.GraphicComponent;
import ryleh.view.Textures;

public class FireGraphicComponent implements GraphicComponent{

	private Rectangle rectangle;
	
	public FireGraphicComponent() {
		rectangle = new Rectangle(190, 190);
		rectangle.setFill(Textures.FIRE.getImagePattern());
	}

	private void updateImage() {

	}

	@Override
	public void render(final Point2D position, final int deltaTime) {
		rectangle.setX(position.getX());
		rectangle.setY(position.getY());
		this.updateImage();
	}

	@Override
	public void onAdded(final Scene scene) {
		Parent root = scene.getRoot();
        ((AnchorPane) root).getChildren().add(rectangle);
	}

}
