package ryleh.model;

import java.util.List;
import java.util.Optional;

import javafx.geometry.Point2D;
import ryleh.common.Point2d;
import ryleh.model.components.AbstractComponent;
import ryleh.model.physics.HitBox;

public interface GameObject {
    /*
     * 
     */
    void onAdded(World world);
    /*
     * 
     */
    void onUpdate(double deltaU);
    /*
     * 
     */
    Point2d getPosition();
    /*
     * 
     */
    void setPosition(Point2d position);

    /*
     * 
     */
    List<AbstractComponent> getComponents();

    /*
     * 
     */
    Optional<? extends AbstractComponent> getComponent(Class<? extends AbstractComponent> type);

    /*
     * 
     */
    void addComponent(AbstractComponent component);

    /*
     * 
     */
    Type getType();
    /*
     * 
     */
    void setType(Type type);
    /*
     * 
     */
    void setHitBox(HitBox box);
    /*
     * 
     */
    HitBox getHitBox();
    /*
     * 
     */
    void setzIndex(int zIndex);
    /*
     * 
     */
    int getzIndex();
}
