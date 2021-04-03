package ryleh.model;

import java.util.ArrayList;
import java.util.List;
import ryleh.common.P2d;
import ryleh.common.Rectangle2d;
import ryleh.common.Shape2d;
import ryleh.controller.Event;
import ryleh.controller.EventListener;

public class World {

    private List<GameObject> gameObjects;
    private Rectangle2d bounds; 
    private int rylehId = 0;
    private int worldWidth = 1920;
    private int worldHeight = 1080;
    private static double boundsWidthProportion = 0.710;
    private static double boundsHeightProportion = 0.650;

    private EventListener eventListener;

    public World(EventListener eventListener) {
    	this.eventListener = eventListener;
        this.gameObjects = new ArrayList<>();
        final int upperLeftX =  (int) Math.round((this.worldWidth * (1 - boundsWidthProportion)) / 2);
        final int upperLeftY = (int) Math.round((this.worldHeight - boundsHeightProportion * this.worldHeight) / 2);

        bounds = new Rectangle2d((int) Math.round(boundsWidthProportion * worldWidth), 
                        (int) Math.round(boundsHeightProportion * worldHeight),
                        upperLeftX, upperLeftY);

        System.out.println(bounds.upperLeft + " " + bounds.lowerRight);
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
    	return  Math.round((this.worldWidth * (1 - boundsWidthProportion)) / 2);
    }
    public double getHeightBound() {
    	return Math.round((this.worldHeight - boundsHeightProportion * this.worldHeight) / 2);
    }

    public Shape2d getBounds() {
        return this.bounds;
    }

	public void removeGameObject(GameObject gameObject) {
		this.gameObjects.remove(gameObject);
		//to  check if needed to remove components
	}
	
	public void notifyWorldEvent(Event e) {
		this.eventListener.notifyEvent(e);
	}

}
