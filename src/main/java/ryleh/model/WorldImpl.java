package ryleh.model;

import java.util.ArrayList;
import java.util.List;
import ryleh.common.Point2d;
import ryleh.common.Rectangle2d;
import ryleh.common.Shape2d;
import ryleh.controller.core.GameEngine;
import ryleh.controller.events.Event;
import ryleh.controller.events.EventListener;

public class WorldImpl implements World {

    private List<GameObject> gameObjects;
    private final Rectangle2d bounds; 
    private int rylehId;
    private static final int BOUNDS_WIDTH = 1407;
    private static final int BOUNDS_HEIGHT = 736;
    private static final int BOUNDS_UPPER_LEFT_X = 252;
    private static final int BOUNDS_UPPER_LEFT_Y = 172;


    private final EventListener eventListener;

    public WorldImpl(final EventListener eventListener) {
        this.eventListener = eventListener;
        this.gameObjects = new ArrayList<>();
        bounds = new Rectangle2d(BOUNDS_WIDTH, BOUNDS_HEIGHT, BOUNDS_UPPER_LEFT_X, BOUNDS_UPPER_LEFT_Y);
        GameEngine.runDebugger(() -> System.out.println(bounds.getUpperLeft() + " " + bounds.getLowerRight()));
    }

    @Override
    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    @Override
    public String generateId(final String type) {
        rylehId++;
        return type + "RY" + this.rylehId;
    }

    @Override
    public boolean isOutOfBounds(final Point2d position) {
        return !bounds.contains(position);
    }

    @Override
    public void addGameObject(final GameObject object) {
        gameObjects.add(object);
        object.onAdded(this);
    }

    @Override
    public Shape2d getBounds() {
        return this.bounds;
    }

    @Override
    public void removeGameObject(final GameObject gameObject) {
    	this.gameObjects.remove(gameObject);
    }
    @Override
    public void notifyWorldEvent(Event e) {
    	this.eventListener.notifyEvent(e);
    }

}
