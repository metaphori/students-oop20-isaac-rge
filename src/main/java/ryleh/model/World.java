package ryleh.model;

import java.util.ArrayList;
import java.util.List;
import ryleh.common.P2d;
import ryleh.common.Rectangle2d;
import ryleh.common.Shape2d;
import ryleh.controller.events.Event;
import ryleh.controller.events.EventListener;
import ryleh.core.GameEngine;

public class World {

    private List<GameObject> gameObjects;
    private final Rectangle2d bounds; 
    private int rylehId;
    private static final int BOUNDS_WIDTH = 1407;
    private static final int BOUNDS_HEIGHT = 736;
    private static final int BOUNDS_UPPER_LEFT_X = 252;
    private static final int BOUNDS_UPPER_LEFT_Y = 172;


    private final EventListener eventListener;

    public World(final EventListener eventListener) {
        this.eventListener = eventListener;
        this.gameObjects = new ArrayList<>();
        bounds = new Rectangle2d(BOUNDS_WIDTH, BOUNDS_HEIGHT, BOUNDS_UPPER_LEFT_X, BOUNDS_UPPER_LEFT_Y);
        GameEngine.runDebugger(() -> System.out.println(bounds.upperLeft + " " + bounds.lowerRight));
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void setGameObjects(final List<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    public String generateId(final String type) {
        rylehId++;
        return type + "RY" + this.rylehId;
    }

    public boolean isOutOfBounds(final P2d position) {
        return !bounds.contains(position);
    }

    public void addGameObject(GameObject object) {
        gameObjects.add(object);
        object.onAdded(this);
    }
    public double getWidthBound() {
    	return bounds.width;
    }
    public double getHeightBound() {
    	return bounds.height;
    }

    public Shape2d getBounds() {
        return this.bounds;
    }

    public void removeGameObject(final GameObject gameObject) {
    	this.gameObjects.remove(gameObject);
    }
    public void notifyWorldEvent(Event e) {
    	this.eventListener.notifyEvent(e);
    }

}
