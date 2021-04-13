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

	public EnemyDrunkGraphicComponent() {
		this.rectangle = new Rectangle(Textures.ENEMY_DRUNK.getWidth(), Textures.ENEMY_DRUNK.getHeight());
		this.rectangle.setFill(Textures.ENEMY_DRUNK.getImagePattern());
	}

	public EnemyDrunkGraphicComponent(final Point2D position) {
		this.rectangle = new Rectangle(Textures.ENEMY_DRUNK.getWidth(), Textures.ENEMY_DRUNK.getHeight());
		this.rectangle.setX(position.getX());
		this.rectangle.setY(position.getY());
		this.rectangle.setFill(Textures.ENEMY_DRUNK.getImagePattern());
	}

	private void updateImage() {
		//TODO
	}
	
	@Override
	public void render(final Point2D position, final double deltaTime) {
		rectangle.setX(position.getX() - rectangle.getWidth() / 2);
		rectangle.setY(position.getY() - rectangle.getHeight() / 2);
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
