package ryleh.view.enemies;



import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import ryleh.common.Config;
import ryleh.common.GameMath;
import ryleh.view.GraphicComponent;
import ryleh.view.Textures;

public class EnemySpinnerGraphicComponent implements GraphicComponent{
	private Rotate rotation = new Rotate();
	private Rectangle rectangle;
	
	public EnemySpinnerGraphicComponent() {
		this.rectangle = new Rectangle(Textures.ENEMY_SPINNER.getWidth(), Textures.ENEMY_SPINNER.getHeight());
		rectangle.setFill(Textures.ENEMY_SPINNER.getImagePattern());
		rotation.setAngle(GameMath.toDegrees(Math.PI / 60));
	}

	public EnemySpinnerGraphicComponent(final Point2D position) {
		this.rectangle = new Rectangle(Textures.ENEMY_SPINNER.getWidth(), Textures.ENEMY_SPINNER.getHeight());
		this.rectangle.setX(position.getX() - rectangle.getWidth() / 2);
		this.rectangle.setY(position.getY() - rectangle.getHeight() / 2);
		this.rectangle.setFill(Textures.ENEMY_SPINNER.getImagePattern());
		this.rotation.setAngle(GameMath.toDegrees(Math.PI / 60));
	}

	private void updateImage() {

	}
	
	@Override
	public void render(final Point2D position, final double deltaTime) {
		rectangle.setX(position.getX() - rectangle.getWidth() / 2);
		rectangle.setY(position.getY() - rectangle.getHeight() / 2);
		rotation.setPivotX(position.getX());
		rotation.setPivotY(position.getY());
		rectangle.getTransforms().add(rotation);
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
