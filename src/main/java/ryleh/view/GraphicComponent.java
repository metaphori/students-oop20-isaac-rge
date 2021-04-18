package ryleh.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;

/**
 * This is the interface for the graphic model of the view of any type of
 * entity.
 */
public interface GraphicComponent {
    /**
     * Updates the view of a GraphicComponent associated to an entity.
     * 
     * @param position The position at which the view needs to be located.
     * @param deltaT   Time elapsed between each update of the game loop.
     */
    void render(Point2D position, double deltaT);

    /**
     * The method that specifies the actions to be done when a GraphicComponent is
     * added to the View.
     * 
     * @param scene The scene in which this graphicComponent is to be added.
     */
    void onAdded(Scene scene);

    /**
     * @return The node related to the GraphicComponent.
     */
    Rectangle getNode();

    /**
     * The method that specifies the actions to be done when a GraphicComponent
     * needs to be removed from the view.
     * 
     * @param event The event that needs to be handled when the GraphicComponent is
     *              to be removed from the view.
     */
    void onRemoved(EventHandler<ActionEvent> event);
}
