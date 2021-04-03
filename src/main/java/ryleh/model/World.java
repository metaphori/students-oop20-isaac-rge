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
    private final Rectangle2d bounds; 
    private int rylehId = 0;
    private static final int WORLD_WIDTH = 1920;
    private static final int WORLD_HEIGHT = 1080;
    //private static double boundsWidthProportion = 0.900;
    //private static double boundsHeightProportion = 0.800;

    private final EventListener eventListener;

    public World(final EventListener eventListener) {
    	this.eventListener = eventListener;
        this.gameObjects = new ArrayList<>();
        //final int upperLeftX =  (int) Math.round((this.WORLD_WIDTH * (1 - boundsWidthProportion)) / 2);
        //final int upperLeftY = (int) Math.round((World.WORLD_HEIGHT - boundsHeightProportion * World.WORLD_HEIGHT) / 2);

        //bounds = new Rectangle2d((int) Math.round(boundsWidthProportion * WORLD_WIDTH), 
        //                (int) Math.round(boundsHeightProportion * WORLD_HEIGHT),
        //                upperLeftX, upperLeftY);
        bounds = new Rectangle2d(1407, 736, 252, 172);

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
    	return bounds.width;
    }
    public double getHeightBound() {
    	return bounds.height;
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
