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
    private Type type;

    /**
     * Creates a new Instance of BulletGraphicComponent.
     * 
     * @param type The type of the generated bullet.
     */
    public BulletGraphicComponent(final Type type) {
        this(new Point2D(0, 0), type);
    }

    /**
     * Creates a new Instance of BulletGraphicComponent with the given initial
     * position.
     * 
     * @param position The position at which the BulletGraphicComponent needs to be
     *                 initialized.
     * @param type     The type of the generated bullet.
     */
    public BulletGraphicComponent(final Point2D position, final Type type) {
        this.rectangle = new Rectangle(Textures.PLAYER_BULLET.getWidth(), Textures.PLAYER_BULLET.getHeight());
        this.type = type;
        this.rectangle.setX(position.getX() - rectangle.getWidth() / 2);
        this.rectangle.setY(position.getY() - rectangle.getHeight() / 2);
        this.setBulletType();
    }

    /**
     * A method to set the correct type of bullet and the related Texture.
     */
    public void setBulletType() {
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
