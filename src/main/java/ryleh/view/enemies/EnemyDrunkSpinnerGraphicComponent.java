package ryleh.view.enemies;

import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import ryleh.common.Config;
import ryleh.common.GameMath;
import ryleh.common.P2d;
import ryleh.common.V2d;
import ryleh.view.GraphicComponent;
import ryleh.view.Textures;

public class EnemyDrunkSpinnerGraphicComponent implements GraphicComponent{

	private Rectangle rectangle;
	private long adjustDirectionTimer = System.currentTimeMillis();
    private long adjustDelay = 100;
    private double moveSpeed = 50;
    private double angle=0;
	
	public EnemyDrunkSpinnerGraphicComponent() {
		this.rectangle = new Rectangle(Textures.ENEMY_DRUNKSPINNER.getWidth(), Textures.ENEMY_DRUNKSPINNER.getHeight());
		this.rectangle.setFill(Textures.ENEMY_DRUNKSPINNER.getImagePattern());
	}

	private void updateImage() {
		angle=angle + Math.PI/40;
		if(angle>=Math.PI*2) {
			angle=0;
		}
	}

	@Override
	public void render(final Point2D position, final double deltaTime) {
		rectangle.setX(position.getX()-rectangle.getWidth()/2);
		rectangle.setY(position.getY()-rectangle.getHeight()/2);
		rectangle.setRotate(GameMath.toDegrees(angle));
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
