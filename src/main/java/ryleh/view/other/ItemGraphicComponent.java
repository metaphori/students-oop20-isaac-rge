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
	private AnimationLoop animItem = new AnimationLoop(List.of(Textures.ITEM1.getImagePattern(), Textures.ITEM2.getImagePattern(), Textures.ITEM3.getImagePattern()), 4);
	private boolean fixed = false;
	private boolean animPlayed = false;
	
	public ItemGraphicComponent() {
		rectangle = new Rectangle(190, 190);
		rectangle.setFill(Textures.ITEM1.getImagePattern());
	}
	
	@Override
	public void render(final Point2D position, final double deltaTime) {
		//if (!fixed) {
			rectangle.setX(position.getX());
			rectangle.setY(position.getY());
		//}
			this.updateImage();

		/*if (!fixed) {
		}
		if (animPlayed) {
			timer++;
		}
		rectangle.setX(position.getX());
		rectangle.setY(position.getY());*/

	}

	private void updateImage() {
		animItem.incTimer();
		rectangle = animItem.setFrame(rectangle);
	}

	@Override
	public void onAdded(final Scene scene) {
		Parent root = scene.getRoot();
        ((AnchorPane) root).getChildren().add(rectangle);
        //this.fixed = true;
	}
	
	//in teoria questo metodo dovrebbe essere utile per sapere quando eliminarlo dal mondo, ovvero quando termina l''animazione 
	public boolean isAnimFinished() {
		return animItem.isCycleFinished();
	}
	
	@Override
	public Object getNode() {
		return rectangle;
	}
}
