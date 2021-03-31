package ryleh.model;

import java.util.List;

public class World {

    List<GameObjectImpl> gameObjects;
    
    public List<GameObjectImpl> getGameObjects() {
        return gameObjects;
    }

    public void setGameObjects(List<GameObjectImpl> gameObjects) {
        this.gameObjects = gameObjects;
    }

}
