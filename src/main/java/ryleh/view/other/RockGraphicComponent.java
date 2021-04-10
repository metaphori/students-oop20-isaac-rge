package ryleh.view.other;

import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import ryleh.common.Config;
import ryleh.view.GraphicComponent;
import ryleh.view.Textures;

public class RockGraphicComponent implements GraphicComponent{

	private Rectangle rectangle;
	private int width;
	private int height;
	
	public RockGraphicComponent() {
		this.height = Textures.ROCK.getHeight();
		this.width = Textures.ROCK.getWidth();
		this.rectangle = new Rectangle(width, height);
		rectangle.setFill(Textures.ROCK2.getImagePattern());
	}

	private void updateImage() {

	}

	@Override
	public void render(final Point2D position, final double deltaTime) {
		rectangle.setX(position.getX());
		rectangle.setY(position.getY());
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
