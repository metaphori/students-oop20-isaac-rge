package ryleh.view.other;

import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import ryleh.view.AnimationLoop;
import ryleh.view.GraphicComponent;
import ryleh.view.Textures;

public class FireGraphicComponent implements GraphicComponent{

	private Rectangle rectangle;
	private AnimationLoop animFire = new AnimationLoop(List.of(Textures.FIRE1.getImagePattern(), Textures.FIRE2.getImagePattern()), 10);
	
	public FireGraphicComponent() {
		rectangle = new Rectangle(190, 190);
		rectangle.setFill(Textures.FIRE1.getImagePattern());
	}

	private void updateImage() {
		rectangle = animFire.setFrame(rectangle);
		animFire.incTimer();
	}

	@Override
	public void render(final Point2D position, final double deltaTime) {
		rectangle.setX(position.getX());
		rectangle.setY(position.getY());
		this.updateImage();
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
