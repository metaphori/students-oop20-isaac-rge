package ryleh.view.enemies;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import ryleh.view.GraphicComponent;
import ryleh.view.Textures;

public class EnemyShooterGraphicComponent implements GraphicComponent{

	private Rectangle rectangle;
	private FadeTransition enemyFade;
	
	public EnemyShooterGraphicComponent() {
		this.rectangle = new Rectangle(Textures.ENEMY_SHOOTER.getHeight(), Textures.ENEMY_SHOOTER.getHeight());
		this.rectangle.setFill(Textures.ENEMY_SHOOTER.getImagePattern());
		this.enemyFade = new FadeTransition(Duration.millis(2000), rectangle);
	    this.enemyFade.setFromValue(1.0);
	    this.enemyFade.setToValue(0.0);
	    this.enemyFade.setCycleCount(4);
	    this.enemyFade.setAutoReverse(true);
	}

	public EnemyShooterGraphicComponent(final Point2D position) {
		this.rectangle = new Rectangle(Textures.ENEMY_SHOOTER.getHeight(), Textures.ENEMY_SHOOTER.getHeight());
		this.rectangle.setX(position.getX() - rectangle.getWidth() / 2);
		this.rectangle.setY(position.getY() - rectangle.getHeight() / 2);
		this.rectangle.setFill(Textures.ENEMY_SHOOTER.getImagePattern());
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
		enemyFade.play();
	}

}
