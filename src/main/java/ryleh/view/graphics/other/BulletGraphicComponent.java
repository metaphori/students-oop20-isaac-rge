package ryleh.view.graphics.other;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import ryleh.model.Type;
import ryleh.view.Textures;
import ryleh.view.graphics.GraphicComponent;

/**
 * A class that provides the GraphicComponent of the view related to the Bullet
 * Entity.
 */
public class BulletGraphicComponent implements GraphicComponent {

    private Rectangle rectangle;

    /**
     * Creates a new Instance of BulletGraphicComponent.
     */
    public BulletGraphicComponent() {
        this(new Point2D(0, 0));
    }

    /**
     * Creates a new Instance of BulletGraphicComponent with the given initial
     * position.
     * 
     * @param position The position at which the BulletGraphicComponent needs to be
     *                 initialized.
     */
    public BulletGraphicComponent(final Point2D position) {
        this.rectangle = new Rectangle(Textures.PLAYER_BULLET.getWidth(), Textures.PLAYER_BULLET.getHeight());
        this.rectangle.setX(position.getX() - rectangle.getWidth() / 2);
        this.rectangle.setY(position.getY() - rectangle.getHeight() / 2);
    }

    /**
     * A method to set the correct type of bullet and the related Texture.
     * @param type Type to be set.
     */
    public void setBulletType(final Type type) {
        switch (type) {
        case PLAYER_BULLET:
            rectangle.setFill(Textures.PLAYER_BULLET.getImagePattern());
            break;
        case ENEMY_BULLET:
            rectangle.setFill(Textures.ENEMY_BULLET.getImagePattern());
            break;
        default:
            break;
        }
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
    public void onRemoved(final EventHandler<ActionEvent> event) {
        event.handle(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Rectangle getNode() {
        return rectangle;
    }
}
