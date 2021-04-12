package ryleh.view.other;

import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import ryleh.common.Config;
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
		this.animPlayed = true;
	}
	
	public void playAnimation() {
		rectangle = animItem.setFrame(rectangle);
		animItem.incTimer();
	}
	
	@Override
	public void render(final Point2D position, final double deltaTime) {
		rectangle.setX(position.getX() - rectangle.getWidth() / 2);
		rectangle.setY(position.getY() - rectangle.getHeight() / 2);
		//TODO if the entity is removed this check shouldn't be done
		if (this.animPlayed) {
			if (this.isAnimFinished()) {
				this.rectangle.setFill(Textures.ITEM3.getImagePattern());
			} else {
				this.playAnimation();
			}
		}
	}

	@Override
	public void onAdded(final Scene scene) {
		Parent root = scene.getRoot();
        ((AnchorPane) root).getChildren().add(rectangle);
        //this.fixed = true;
	}
	
	public boolean isAnimFinished() { // questo metodo serve negli eventi, per sapere quando cancellare l'entit� dal mondo ( sia view che model, credo)
		return animItem.isCycleFinished();
	}

  @Override
	public Object getNode() {
		return this.rectangle;
	}	
}
