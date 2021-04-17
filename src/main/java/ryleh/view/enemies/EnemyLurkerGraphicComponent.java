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
import ryleh.common.GameMath;
import ryleh.common.Point2d;
import ryleh.common.Vector2d;
import ryleh.view.GraphicComponent;
import ryleh.view.PlayerGraphicComponent;
import ryleh.view.Textures;

/**
 * A class that provides a GraphicComponent of the view related to the EnemyLurker Entity.
 */
public class EnemyLurkerGraphicComponent implements GraphicComponent {

	private Rectangle rectangle;
	private long adjustDirectionTimer = System.currentTimeMillis();
    private static final long ADJUST_DELAY = 500;
    private static final int FADE_DURATION = 200;
	private static final int MOVE_SPEED = 50;
	private PlayerGraphicComponent player;
	private FadeTransition enemyFade;
	
	
	/**
	 * Creates a new Instance of EnemyLurkerGraphicComponent with the given player to be followed.
	 * @param graphicComponent The PlayerGraphicComponent that needs to be followed by the EnemyLurker.
	 */
	public EnemyLurkerGraphicComponent(final PlayerGraphicComponent graphicComponent) {
		this(graphicComponent, new Point2D(0, 0));
	}

	/**
	 * Creates a new Instance of EnemyLurkerGraphicComponent, with the given player to be followed and the initial position.
	 * @param graphicComponent The PlayerGraphicComponent that needs to be followed by the EnemyLurker
	 * @param position The position at which this GraphicComponent needs to be initialized in the view.
	 */
	public EnemyLurkerGraphicComponent(final PlayerGraphicComponent graphicComponent, final Point2D position) {
		this.rectangle = new Rectangle(Textures.ENEMY_LURKER.getWidth(), Textures.ENEMY_LURKER.getHeight());
		this.rectangle.setX(position.getX() - rectangle.getWidth() / 2);
		this.rectangle.setY(position.getY() - rectangle.getHeight() / 2);
		this.rectangle.setFill(Textures.ENEMY_LURKER.getImagePattern());
		this.player = graphicComponent;
		this.enemyFade = new FadeTransition(Duration.millis(FADE_DURATION), rectangle);
	    this.enemyFade.setFromValue(1.0);
	    this.enemyFade.setToValue(0.0);
	    this.enemyFade.setCycleCount(4);
	    this.enemyFade.setAutoReverse(true);
	}

	/**
	 * A method to update the state of this GraphicComponent in the view.
	 * @param position The position at which the GraphicComponent needs to be moved.
	 */
	private void updateImage(final Point2D position) {
		rectangle.setX(position.getX() - rectangle.getWidth() / 2);
		rectangle.setY(position.getY() - rectangle.getHeight() / 2);
		if (System.currentTimeMillis() - adjustDirectionTimer >= ADJUST_DELAY) {
			Vector2d directionToPlayer = new Vector2d(new Point2d(player.getNode().getX(), player.getNode().getY()), new Point2d(position.getX() - rectangle.getWidth() / 2, position.getY() - rectangle.getHeight() / 2))
					.getNormalized()
					.multiply(MOVE_SPEED);
			rectangle.setRotate(GameMath.toDegrees(Math.atan(directionToPlayer.getY() / directionToPlayer.getX())));
    		adjustDirectionTimer = System.currentTimeMillis();
    	}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void render(final Point2D position, final double deltaTime) {
		this.updateImage(position);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onAdded(final Scene scene) {
		Parent root = scene.getRoot();
        ((AnchorPane) root).getChildren().add(rectangle);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Rectangle getNode() {
		return rectangle;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onRemoved(final EventHandler<ActionEvent> event) {
		enemyFade.setOnFinished(event);
		enemyFade.play();
	}
}
