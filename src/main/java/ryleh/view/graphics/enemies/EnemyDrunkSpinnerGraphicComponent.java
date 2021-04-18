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
import ryleh.common.GameMath;
import ryleh.view.Textures;
import ryleh.view.graphics.GraphicComponent;

/**
 * A class that provides the GraphicComponent of the view related to the
 * EnemyDrunkSpinner Entity.
 */
public class EnemyDrunkSpinnerGraphicComponent implements GraphicComponent {

    private Rectangle rectangle;
    private double angle;
    private FadeTransition enemyFade;
    private static final int FADE_DURATION = 200;

    /**
     * Creates a new Instance of EnemyDrunkSpinnerGraphicComponent.
     */
    public EnemyDrunkSpinnerGraphicComponent() {
        this(new Point2D(0, 0));
    }

    /**
     * Creates a new Instance of EnemyDrunkSpinnerGraphicComponent with the given
     * initial position.
     * 
     * @param position the position at which this GraphicComponent needs to be
     *                 initialized.
     */
    public EnemyDrunkSpinnerGraphicComponent(final Point2D position) {
        this.rectangle = new Rectangle(Textures.ENEMY_DRUNKSPINNER.getWidth(), Textures.ENEMY_DRUNKSPINNER.getHeight());
        this.rectangle.setX(position.getX() - rectangle.getWidth() / 2);
        this.rectangle.setY(position.getY() - rectangle.getHeight() / 2);
        this.rectangle.setFill(Textures.ENEMY_DRUNKSPINNER.getImagePattern());
        this.enemyFade = new FadeTransition(Duration.millis(FADE_DURATION), rectangle);
        this.enemyFade.setFromValue(1.0);
        this.enemyFade.setToValue(0.0);
        this.enemyFade.setCycleCount(4);
        this.enemyFade.setAutoReverse(true);
        this.angle = 0;
    }

    /**
     * A method to update the state of the graphicComponent in the view.
     */
    private void updateImage() {
        angle = angle + Math.PI / 40;
        if (angle >= Math.PI * 2) {
            angle = 0;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final Point2D position, final double deltaTime) {
        rectangle.setX(position.getX() - rectangle.getWidth() / 2);
        rectangle.setY(position.getY() - rectangle.getHeight() / 2);
        rectangle.setRotate(GameMath.toDegrees(angle));
        this.updateImage();
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
