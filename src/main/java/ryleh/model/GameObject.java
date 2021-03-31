package ryleh.model;

import java.util.List;

import javafx.geometry.Point2D;
import ryleh.model.components.Component;

public interface GameObject {
    void onUpdate();

    Point2D getPosition();

    List<? extends Component> getComponents();

    Component getComponent(Class<? extends Component> type);

    void addComponent(Component component);

    Type getType();
}
