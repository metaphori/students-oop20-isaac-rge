package ryleh.view.graphics.enemies;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import ryleh.view.Textures;
import ryleh.view.graphics.GraphicComponent;

/**
 * A class that provides the GraphicComponent of the view related to the
 * EnemyShooter Entity.
 */
public class EnemyShooterGraphicComponent implements GraphicComponent {

    private Rectangle rectangle;
    private FadeTransition enemyFade;
    private int zIndex;
    /**
     * The duration of the fade animation of this GraphicComponent.
     */
    private static final int FADE_DURATION = 200;

    /**
     * Creates a new Instance of EnemyGrapohicComponent.
     */
    public EnemyShooterGraphicComponent() {
        this(new Point2D(0, 0));
    }

    /**
     * Creates a new Instance of EnemyShooterComponent with the given initial
     * position.
     * 
     * @param position The position at which this GraphicComponent needs to be
     *                 initialized.
     */
    public EnemyShooterGraphicComponent(final Point2D position) {
        this.rectangle = new Rectangle(Textures.ENEMY_SHOOTER.getHeight(), Textures.ENEMY_SHOOTER.getHeight());
        this.rectangle.setX(position.getX() - rectangle.getWidth() / 2);
        this.rectangle.setY(position.getY() - rectangle.getHeight() / 2);
        this.rectangle.setFill(Textures.ENEMY_SHOOTER.getImagePattern());
        this.enemyFade = new FadeTransition(Duration.millis(FADE_DURATION), rectangle);
        this.enemyFade.setFromValue(1.0);
        this.enemyFade.setToValue(0.0);
        this.enemyFade.setCycleCount(4);
        this.enemyFade.setAutoReverse(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final Point2D position, final double deltaTime) {
        rectangle.setX(position.getX() - rectangle.getWidth() / 2);
        rectangle.setY(position.getY() - rectangle.getHeight() / 2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onAdded(final Scene scene) {
        final Parent root = scene.getRoot();
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void setZindex(final int zIndex) {
        this.zIndex = zIndex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getZindex() {
        return zIndex;
    }
}
