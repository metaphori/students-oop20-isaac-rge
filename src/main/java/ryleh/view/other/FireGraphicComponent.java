package ryleh.view.other;

import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import ryleh.view.AnimationLoop;
import ryleh.view.GraphicComponent;
import ryleh.view.Textures;

/**
 * A class that provides the GraphicComponent of the view related to the Fire Entity.
 */
public class FireGraphicComponent implements GraphicComponent {

	private Rectangle rectangle;
	private static final int ANIM_DURATION = 10;

	private AnimationLoop animFire = new AnimationLoop(List.of(Textures.FIRE1.getImagePattern(),
															   Textures.FIRE2.getImagePattern()), 
													   ANIM_DURATION);
	
	/**
	 * Creates the new Instance of FireGraphicComponent.
	 */
	public FireGraphicComponent() {
		this.rectangle = new Rectangle(Textures.FIRE1.getWidth(), Textures.FIRE1.getHeight());
		this.rectangle.setFill(Textures.FIRE1.getImagePattern());
	}

	/**
	 * Creates a new Instance of FireGraphicCOmponent with the given initial position.
	 * @param position The position at which the FireGraphicComponent needs to be initialized.
	 */
	public FireGraphicComponent(final Point2D position) {
		this.rectangle = new Rectangle(Textures.FIRE1.getWidth(), Textures.FIRE1.getHeight());
		this.rectangle.setX(position.getX() - rectangle.getWidth() / 2);
		this.rectangle.setY(position.getY() - rectangle.getHeight() / 2);
		this.rectangle.setFill(Textures.FIRE1.getImagePattern());
	}

	/**
	 * A method to update the state of this GraphicComponent in the view.
	 */
	private void updateImage() {
		rectangle = animFire.setFrame(rectangle);
		animFire.incTimer();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void render(final Point2D position, final double deltaTime) {
		rectangle.setX(position.getX() - rectangle.getWidth() / 2);
		rectangle.setY(position.getY() - rectangle.getHeight() / 2);
		this.updateImage();
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
