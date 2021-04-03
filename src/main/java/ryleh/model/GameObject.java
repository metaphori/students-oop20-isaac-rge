package ryleh.model;

import java.util.List;
import java.util.Optional;

import javafx.geometry.Point2D;
import ryleh.common.P2d;
import ryleh.model.components.Component;
import ryleh.model.physics.HitBox;

public interface GameObject {
    /*
     * 
     */
    void onAdded(World world);
    /*
     * 
     */
    void onUpdate(int deltaTime);
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
    Optional<? extends Component> getComponent(Class<? extends Component> type);

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
    /*
     * 
     */
    void setHitBox(HitBox box);
    /*
     * 
     */
    HitBox getHitBox();
}
