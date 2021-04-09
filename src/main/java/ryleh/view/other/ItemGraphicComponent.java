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

public class ItemGraphicComponent implements GraphicComponent{

	private Rectangle rectangle;
	private boolean animPlayed;
	private AnimationLoop animItem = new AnimationLoop(List.of(Textures.ITEM1.getImagePattern(), 
															   Textures.ITEM2.getImagePattern(), 
															   Textures.ITEM3.getImagePattern()), 
													   10);
	//private boolean fixed = false;
	
	
	public ItemGraphicComponent() {
		this.rectangle = new Rectangle(190, 190);
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
		//if (!fixed) {
			rectangle.setX(position.getX());
			rectangle.setY(position.getY());
		//}
			if (this.animPlayed) {
				this.playAnimation();
			}
		/*if (!fixed) {
		}
		if (animPlayed) {
			timer++;
		}
		rectangle.setX(position.getX());
		rectangle.setY(position.getY());*/
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
