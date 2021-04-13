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

public class ItemGraphicComponent implements GraphicComponent{

	private Rectangle rectangle;
	private boolean animPlayed;
	private AnimationLoop animItem = new AnimationLoop(List.of(Textures.ITEM1.getImagePattern(), 
															   Textures.ITEM2.getImagePattern(), 
															   Textures.ITEM3.getImagePattern()), 
													   10);	
	
	public ItemGraphicComponent() {
		this.rectangle = new Rectangle(Textures.ITEM1.getWidth(), Textures.ITEM1.getHeight());
		this.rectangle.setFill(Textures.ITEM1.getImagePattern());
		this.animPlayed = false;
	}
	
	public ItemGraphicComponent(final Point2D position) {
		this.rectangle = new Rectangle(Textures.ITEM1.getWidth(), Textures.ITEM1.getHeight());
		this.rectangle.setX(position.getX() - rectangle.getWidth() / 2);
		this.rectangle.setY(position.getY() - rectangle.getHeight() / 2);
		this.rectangle.setFill(Textures.ITEM1.getImagePattern());
		this.animPlayed = false;
	}

	public void setAnimPlayed() {
		animPlayed = true;
	}
	
	public void playAnimation() {
		rectangle = animItem.setFrame(rectangle);
		animItem.incTimer();
	}
	
	@Override
	public void render(final Point2D position, final double deltaTime) {
		rectangle.setX(position.getX() - rectangle.getWidth() / 2);
		rectangle.setY(position.getY() - rectangle.getHeight() / 2);
		if (animPlayed) {
			this.playAnimation();
		}
	}

	@Override
	public void onAdded(final Scene scene) {
		Parent root = scene.getRoot();
        ((AnchorPane) root).getChildren().add(rectangle);
	}
	
	public boolean isAnimFinished() {
		return animItem.isCycleFinished();
	}

	@Override
	public Object getNode() {
		return rectangle;
	}

	@Override
	public void onRemoved(final EventHandler<ActionEvent> event) {
		if (isAnimFinished()) {
			event.handle(null);
		}
		this.playAnimation();
	}	
}
