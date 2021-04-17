package ryleh.model;

import java.util.List;

import ryleh.common.Point2d;
import ryleh.common.Shape2d;
import ryleh.controller.events.Event;

public interface World {

    List<GameObject> getGameObjects();

    String generateId(String type);

    boolean isOutOfBounds(Point2d position);

    void addGameObject(GameObject object);

    Shape2d getBounds();

    void removeGameObject(GameObject gameObject);

    void notifyWorldEvent(Event event);

}