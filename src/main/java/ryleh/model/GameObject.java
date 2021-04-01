package ryleh.model;

import java.util.List;
import java.util.Optional;

import javafx.geometry.Point2D;
import ryleh.model.components.Component;

public interface GameObject {
    void onUpdate();

    Point2D getPosition();

    List<Component> getComponents();

    Optional<Component> getComponent(Class<Component> type);

    void addComponent(Component component);

    Type getType();
}
