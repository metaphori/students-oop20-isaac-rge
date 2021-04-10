package ryleh.view.enemies;

import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import ryleh.common.Config;
import ryleh.view.GraphicComponent;
import ryleh.view.Textures;

public class EnemyDrunkGraphicComponent implements GraphicComponent{

	private Rectangle rectangle;
	private int width;
	private int height;

	public EnemyDrunkGraphicComponent() {
		this.height = Textures.ENEMY_DRUNK.getHeight();
		this.width = Textures.ENEMY_DRUNK.getWidth();
		this.rectangle = new Rectangle(width, height);
		this.rectangle.setFill(Textures.ENEMY_DRUNK.getImagePattern());
	}

	private void updateImage() {
		//TODO
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
