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
	private int width;
	private int height;
	
	public BulletGraphicComponent() {
		this.height = (int) (Config.SCALE_MODIFIER * 20);
		this.width = (int) (Config.SCALE_MODIFIER * 20);
		this.rectangle = new Rectangle(width, height);
		rectangle.setFill(Color.BLACK);
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
