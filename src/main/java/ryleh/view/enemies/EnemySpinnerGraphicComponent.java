package ryleh.view.enemies;



import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import ryleh.common.Config;
import ryleh.common.GameMath;
import ryleh.view.GraphicComponent;
import ryleh.view.Textures;

public class EnemySpinnerGraphicComponent implements GraphicComponent{
	private Rotate rotation = new Rotate();
	private Rectangle rectangle;
	private FadeTransition enemyFade;
	
	public EnemySpinnerGraphicComponent() {
		this.rectangle = new Rectangle(Textures.ENEMY_SPINNER.getWidth(), Textures.ENEMY_SPINNER.getHeight());
		rectangle.setFill(Textures.ENEMY_SPINNER.getImagePattern());
		rotation.setAngle(GameMath.toDegrees(Math.PI / 60));
		this.enemyFade = new FadeTransition(Duration.millis(2000), rectangle);
	    this.enemyFade.setFromValue(1.0);
	    this.enemyFade.setToValue(0.0);
	    this.enemyFade.setCycleCount(4);
	    this.enemyFade.setAutoReverse(true);
	}

	public EnemySpinnerGraphicComponent(final Point2D position) {
		this.rectangle = new Rectangle(Textures.ENEMY_SPINNER.getWidth(), Textures.ENEMY_SPINNER.getHeight());
		this.rectangle.setX(position.getX() - rectangle.getWidth() / 2);
		this.rectangle.setY(position.getY() - rectangle.getHeight() / 2);
		this.rectangle.setFill(Textures.ENEMY_SPINNER.getImagePattern());
		this.rotation.setAngle(GameMath.toDegrees(Math.PI / 60));
		this.enemyFade = new FadeTransition(Duration.millis(2000), rectangle);
	    this.enemyFade.setFromValue(1.0);
	    this.enemyFade.setToValue(0.0);
	    this.enemyFade.setCycleCount(4);
	    this.enemyFade.setAutoReverse(true);
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

	@Override
	public void onRemoved(final EventHandler<ActionEvent> event) {
		enemyFade.setOnFinished(event);
		this.enemyFade.play();
	}
}
