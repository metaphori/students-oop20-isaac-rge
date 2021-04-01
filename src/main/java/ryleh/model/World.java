package ryleh.model;

import java.util.List;

public class World {

    private List<GameObjectImpl> gameObjects;
    private int rylehId = 0;

    public List<GameObjectImpl> getGameObjects() {
        return gameObjects;
    }

    public void setGameObjects(final List<GameObjectImpl> gameObjects) {
        this.gameObjects = gameObjects;
    }

    public String generateId(final String type) {
        rylehId++;
        return type + "RY" + this.rylehId;
    }

}
