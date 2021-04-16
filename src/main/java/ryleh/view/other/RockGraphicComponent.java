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
 * A class that provides the GraphicComponent of the view related to the Rock Entity.
 */
public class RockGraphicComponent implements GraphicComponent {

	private Rectangle rectangle;
	
	/**
	 * Creates a new Instance of the RockGraphicComponent.
	 */
	public RockGraphicComponent() {
		this(new Point2D(0, 0));
	}

	/**
	 * Creates a new Instance of the RockGraphicComponent with the given initial position.
	 * @param position The position at which the RockGraphicComponent needs to be initialized.
	 */
	public RockGraphicComponent(final Point2D position) {
		this.rectangle = new Rectangle(Textures.ROCK.getWidth(), Textures.ROCK.getHeight());
		this.rectangle.setX(position.getX() - rectangle.getWidth() / 2);
		this.rectangle.setY(position.getY() - rectangle.getHeight() / 2);
		this.rectangle.setFill(Textures.ROCK2.getImagePattern());
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
	public Rectangle getNode() {
		return rectangle;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onRemoved(final EventHandler<ActionEvent> event) {
		event.handle(null);
	}
}
