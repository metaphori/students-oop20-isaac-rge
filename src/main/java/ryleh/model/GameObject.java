package ryleh.model;

import java.util.List;
import java.util.Optional;

import javafx.geometry.Point2D;
import ryleh.common.P2d;
import ryleh.model.components.Component;

public interface GameObject {
    /*
     * 
     */
    void onAdded(World world);
    /*
     * 
     */
    void onUpdate();
    /*
     * 
     */
    P2d getPosition();
    /*
     * 
     */
    void setPosition(P2d position);

    /*
     * 
     */
    List<Component> getComponents();

    /*
     * 
     */
    Optional<Component> getComponent(Class<Component> type);

    /*
     * 
     */
    void addComponent(Component component);

    /*
     * 
     */
    Type getType();
    /*
     * 
     */
    void setType(Type type);
}
