package ryleh.view.other;

import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import ryleh.view.GraphicComponent;
import ryleh.view.Textures;

public class ItemGraphicComponent implements GraphicComponent{

	private Rectangle rectangle;
	private boolean fixed = false;
	private int timer = 0;
	private boolean animPlayed = false;
	
	public ItemGraphicComponent() {
		rectangle = new Rectangle(190, 190);
		rectangle.setFill(Textures.ITEM1.getImagePattern());
	}

	//DA CONTROLLARE SE FUHNZIONA EFFETTIVAMENTE O MENO
	@Override
	public void render(final Point2D position, final double deltaTime) {
		if (!fixed) {
			rectangle.setX(position.getX());
			rectangle.setY(position.getY());
		}
		if (animPlayed) {
			timer++;
		}
	}
	
	//metodo che viene chiamato solo al momento della collisione
	public void playAnimation() {
		animPlayed = true;
		if (timer == 3) {
			rectangle.setFill(Textures.ITEM2.getImagePattern());
		} else {
			if (timer == 6) {
				rectangle.setFill(Textures.ITEM3.getImagePattern());
			}
		}
	}

	@Override
	public void onAdded(final Scene scene) {
		Parent root = scene.getRoot();
        ((AnchorPane) root).getChildren().add(rectangle);
        this.fixed = true;
	}

}
