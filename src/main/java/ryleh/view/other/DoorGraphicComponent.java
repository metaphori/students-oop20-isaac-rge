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
 * A class that provides the GraphicComponent of the view related to the Door Entity.
 */
public class DoorGraphicComponent implements GraphicComponent {

	private Rectangle rectangle;
	private boolean animPlayed;
	private static final int ANIM_DURATION = 10;
	private AnimationLoop animDoor = new AnimationLoop(List.of(Textures.DOOR1.getImagePattern(), 
															   Textures.DOOR2.getImagePattern(), 
															   Textures.DOOR3.getImagePattern(), 
															   Textures.DOOR4.getImagePattern(), 
															   Textures.DOOR5.getImagePattern()), 
														ANIM_DURATION);
	
	/**
	 * Creates a new Instance of BulletGraphicComponent.
	 */
	public DoorGraphicComponent() {
		this.rectangle = new Rectangle(Textures.DOOR1.getWidth(), Textures.DOOR1.getHeight());
		this.rectangle.setFill(Textures.DOOR1.getImagePattern());
		this.animPlayed = false;
	}
	
	/**
	 * Creates a new Instance of DoorGraphicComponent with the given initial position.
	 * @param position The position at which the DoorGraphicComponent needs to be initialized.
	 */
	public DoorGraphicComponent(final Point2D position) {
		this.rectangle = new Rectangle(Textures.DOOR1.getWidth(), Textures.DOOR1.getHeight());
		this.rectangle.setX(position.getX() - rectangle.getWidth() / 2);
		this.rectangle.setY(position.getY() - rectangle.getHeight() / 2);
		this.rectangle.setFill(Textures.DOOR1.getImagePattern());
		this.animPlayed = false;
	}
	
	/**
	 * A method to set the animation to played.
	 */
	public void setAnimPlayed() {
		animPlayed = true;
	}

	/**
	 * A method to play the animation when needed.
	 */
	public void playAnimation() {
		rectangle = animDoor.setFrame(rectangle);
		animDoor.incTimer();
	}
	
	/**
	 * A method that tells if the current animation is finished.
	 * @return True if the current animation is finished.
	 */
	public boolean isAnimFinished() {
		return animDoor.isCycleFinished();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void render(final Point2D position, final double deltaTime) {
		rectangle.setX(position.getX() - rectangle.getWidth() / 2);
		rectangle.setY(position.getY() - rectangle.getHeight() / 2);
		if (animPlayed) {
			if(this.isAnimFinished()) {
				rectangle.setFill(Textures.DOOR5.getImagePattern()); // setta l'ultimo frame. posso fare anche un metodo in animLoop, getLastFrame()
			} else {
				this.playAnimation();
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onAdded(final Scene scene) {
		Parent root = scene.getRoot();
        ((AnchorPane) root).getChildren().add(rectangle);
        this.animPlayed = true;
        this.playAnimation();
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
		// TODO Auto-generated method stub
	}
}
