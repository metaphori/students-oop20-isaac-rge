package ryleh.view.other;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import ryleh.view.GraphicComponent;
import ryleh.view.Textures;

/**
 * A class that provides the GraphicComponent of the view related to the Bullet Entity.
 */
public class BulletGraphicComponent implements GraphicComponent {

	private Rectangle rectangle;
	
	/**
	 * Creates a new Instance of BulletGraphicComponent.
	 */
	public BulletGraphicComponent() {
		this.rectangle = new Rectangle(Textures.BULLET.getWidth(), Textures.BULLET.getHeight());
		this.rectangle.setFill(Textures.BULLET.getImagePattern());
	}
	
	/**
	 * Creates a new Instance of BulletGraphicComponent with the given initial position.
	 * @param position The position at which the BulletGraphicComponent needs to be initialized.
	 */
	public BulletGraphicComponent(final Point2D position) {
		this.rectangle = new Rectangle(Textures.BULLET.getWidth(), Textures.BULLET.getHeight());
		this.rectangle.setX(position.getX() - rectangle.getWidth() / 2);
		this.rectangle.setY(position.getY() - rectangle.getHeight() / 2);
		this.rectangle.setFill(Textures.BULLET.getImagePattern());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void render(final Point2D position, final double deltaTime) {
		rectangle.setX(position.getX() - rectangle.getWidth() / 2);
		rectangle.setY(position.getY() - rectangle.getHeight() / 2);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onAdded(final Scene scene) {
		Parent root = scene.getRoot();
        ((AnchorPane) root).getChildren().add(rectangle);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onRemoved(final EventHandler<ActionEvent> event) {
		event.handle(null);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getNode() {
		return rectangle;
	}
}
