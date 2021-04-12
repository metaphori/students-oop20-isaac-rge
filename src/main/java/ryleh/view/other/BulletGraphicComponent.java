package ryleh.view.other;

import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ryleh.common.Config;
import ryleh.view.GraphicComponent;
import ryleh.view.Textures;

public class BulletGraphicComponent implements GraphicComponent{

	private Rectangle rectangle;
	
	public BulletGraphicComponent() {
		this.rectangle = new Rectangle(Textures.BULLET.getWidth(), Textures.BULLET.getHeight());
		rectangle.setFill(Textures.BULLET.getImagePattern());
	}
	@Override
	public void render(final Point2D position, final double deltaTime) {
		rectangle.setX(position.getX());
		rectangle.setY(position.getY());
	}

	@Override
	public void onAdded(final Scene scene) {
		Parent root = scene.getRoot();
        ((AnchorPane) root).getChildren().add(rectangle);
	}
	@Override
	public Object getNode() {
		return rectangle;
	}
}
