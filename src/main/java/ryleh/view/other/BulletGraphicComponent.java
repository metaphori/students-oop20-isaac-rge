package ryleh.view.other;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import ryleh.common.Config;
import ryleh.view.GraphicComponent;
import ryleh.view.Textures;

public class BulletGraphicComponent implements GraphicComponent{

	private Rectangle rectangle;
	
	public BulletGraphicComponent() {
		this.rectangle = new Rectangle(Textures.BULLET.getWidth(), Textures.BULLET.getHeight());
		this.rectangle.setFill(Textures.BULLET.getImagePattern());
	}
	
	public BulletGraphicComponent(final Point2D position) {
		this.rectangle = new Rectangle(Textures.BULLET.getWidth(), Textures.BULLET.getHeight());
		this.rectangle.setX(position.getX() - rectangle.getWidth() / 2);
		this.rectangle.setY(position.getY() - rectangle.getHeight() / 2);
		this.rectangle.setFill(Textures.BULLET.getImagePattern());
	}
	
	@Override
	public void render(final Point2D position, final double deltaTime) {
		rectangle.setX(position.getX() - rectangle.getWidth() / 2);
		rectangle.setY(position.getY() - rectangle.getHeight() / 2);
	}

	@Override
	public void onAdded(final Scene scene) {
		Parent root = scene.getRoot();
        ((AnchorPane) root).getChildren().add(rectangle);
	}
	
	public void onRemoved(final EventHandler<ActionEvent> event) {
		event.handle(null);
	}
	
	@Override
	public Object getNode() {
		return rectangle;
	}
}
