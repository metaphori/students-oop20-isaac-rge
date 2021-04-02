package ryleh.view.enemies;

import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import ryleh.view.GraphicComponent;

public class EnemyLurkerGraphicComponent implements GraphicComponent{

	private Rectangle rectangle;
	
	public EnemyLurkerGraphicComponent() {
		rectangle = new Rectangle();
		rectangle.setFill(new ImagePattern(new Image("Enemy3.png")));
	}

	private void updateImage() {

	}
	
	@Override
	public void render() {
	}

	@Override
	public void render(final Point2D position) { //onUpdate()
		rectangle.setX(position.getX());
		rectangle.setY(position.getY());
		this.updateImage();
	}

	@Override
	public void onAdded(final Scene scene) {
		scene.getRoot().getChildren().add(rectangle);
	}

}
