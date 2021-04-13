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
import javafx.util.Duration;
import ryleh.common.Config;
import ryleh.view.GraphicComponent;
import ryleh.view.Textures;

public class EnemyDrunkGraphicComponent implements GraphicComponent{

	private Rectangle rectangle;
	private FadeTransition enemyFade;

	public EnemyDrunkGraphicComponent() {
		this.rectangle = new Rectangle(Textures.ENEMY_DRUNK.getWidth(), Textures.ENEMY_DRUNK.getHeight());
		this.rectangle.setFill(Textures.ENEMY_DRUNK.getImagePattern());
		this.enemyFade = new FadeTransition(Duration.millis(2000), rectangle);
	    this.enemyFade.setFromValue(1.0);
	    this.enemyFade.setToValue(0.0);
	    this.enemyFade.setCycleCount(4);
	    this.enemyFade.setAutoReverse(true);
	}

	public EnemyDrunkGraphicComponent(final Point2D position) {
		this.rectangle = new Rectangle(Textures.ENEMY_DRUNK.getWidth(), Textures.ENEMY_DRUNK.getHeight());
		this.rectangle.setX(position.getX());
		this.rectangle.setY(position.getY());
		this.rectangle.setFill(Textures.ENEMY_DRUNK.getImagePattern());
		this.enemyFade = new FadeTransition(Duration.millis(200), rectangle);
	    this.enemyFade.setFromValue(1.0);
	    this.enemyFade.setToValue(0.0);
	    this.enemyFade.setCycleCount(4);
	    this.enemyFade.setAutoReverse(true);
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
	
	public void onRemoved(final EventHandler<ActionEvent> event) {
		enemyFade.setOnFinished(event);
		this.enemyFade.play();
	}

	@Override
	public Object getNode() {
		return rectangle;
	}



}
