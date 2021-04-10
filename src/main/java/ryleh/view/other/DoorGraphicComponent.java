package ryleh.view.other;

import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import ryleh.view.AnimationLoop;
import ryleh.view.GraphicComponent;
import ryleh.view.Textures;

public class DoorGraphicComponent implements GraphicComponent{

	private Rectangle rectangle;
	private boolean animPlayed;
	private AnimationLoop animDoor = new AnimationLoop(List.of(Textures.DOOR1.getImagePattern(), 
															   Textures.DOOR2.getImagePattern(), 
															   Textures.DOOR3.getImagePattern(), 
															   Textures.DOOR4.getImagePattern(), 
															   Textures.DOOR5.getImagePattern()), 
														8);
	
	public DoorGraphicComponent() {
		this.rectangle = new Rectangle(190, 190);
		this.rectangle.setFill(Textures.DOOR1.getImagePattern());
		this.animPlayed = false;
	}
	
	public void setAnimPlayed() {
		this.animPlayed = true;
	}

	public void playAnimation() {
		rectangle = animDoor.setFrame(rectangle);
		animDoor.incTimer();
	}
	
	public boolean isAnimFinished() { // questo metodo serve negli eventi, per sapere quando cancellare l'entit� dal mondo ( sia view che model, credo)
		return animDoor.isCycleFinished();
	}

	@Override
	public void render(final Point2D position, final double deltaTime) {
		rectangle.setX(position.getX()-rectangle.getWidth()/2);
		rectangle.setY(position.getY()-rectangle.getHeight()/2);
		if (this.animPlayed) {
			this.playAnimation();
		}
	}

	@Override
	public void onAdded(final Scene scene) {
		Parent root=scene.getRoot();
        ((AnchorPane) root).getChildren().add(rectangle);
	}
	@Override
	public Object getNode() {
		return rectangle;
	}
}
