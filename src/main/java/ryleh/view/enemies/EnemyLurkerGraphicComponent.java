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
import ryleh.common.P2d;
import ryleh.common.V2d;
import ryleh.controller.Entity;
import ryleh.model.GameObject;
import ryleh.view.GraphicComponent;
import ryleh.view.Textures;

public class EnemyLurkerGraphicComponent implements GraphicComponent{

	private Rectangle rectangle;
	private long adjustDirectionTimer = System.currentTimeMillis();
    private long adjustDelay = 500;
	private V2d velocity;
	private int moveSpeed;
	private GameObject player;
	private FadeTransition enemyFade;
	
	public EnemyLurkerGraphicComponent(final GameObject player) {
		this.rectangle = new Rectangle(Textures.ENEMY_LURKER.getWidth(), Textures.ENEMY_LURKER.getHeight());
		this.rectangle.setFill(Textures.ENEMY_LURKER.getImagePattern());
		this.player = player;
		this.moveSpeed = 50;
		this.adjustDelay = 500;
		this.velocity = new V2d(0,0);
		this.enemyFade = new FadeTransition(Duration.millis(200), rectangle);
	    this.enemyFade.setFromValue(1.0);
	    this.enemyFade.setToValue(0.0);
	    this.enemyFade.setCycleCount(4);
	    this.enemyFade.setAutoReverse(true);
	}

	public EnemyLurkerGraphicComponent(final GameObject player, final Point2D position) {
		this.rectangle = new Rectangle(Textures.ENEMY_LURKER.getWidth(), Textures.ENEMY_LURKER.getHeight());
		this.rectangle.setX(position.getX() - rectangle.getWidth() / 2);
		this.rectangle.setY(position.getY() - rectangle.getHeight() / 2);
		this.rectangle.setFill(Textures.ENEMY_LURKER.getImagePattern());
		this.player = player;
		this.velocity = new V2d(0, 0);
		this.enemyFade = new FadeTransition(Duration.millis(200), rectangle);
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
		if (System.currentTimeMillis() - adjustDirectionTimer >= adjustDelay) {
			V2d directionToPlayer = new V2d(this.player.getPosition(), new P2d(position.getX() - rectangle.getWidth() / 2, position.getY() - rectangle.getHeight() / 2))
					.getNormalized()
					.mul(moveSpeed);
			rectangle.setRotate(GameMath.toDegrees((Math.atan(directionToPlayer.y / directionToPlayer.x))));
    		adjustDirectionTimer = System.currentTimeMillis();
    	}
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
	public void onRemoved(EventHandler<ActionEvent> event) {
		enemyFade.setOnFinished(event);
		enemyFade.play();
	}
}
