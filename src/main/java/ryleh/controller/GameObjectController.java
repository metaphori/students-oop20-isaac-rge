package ryleh.controller;

import ryleh.model.GameObjectImpl;
import ryleh.view.GraphicComponent;

public class GameObjectController {

    private GraphicComponent view;
    private GameObjectImpl gameObject;

    public GraphicComponent getView() {
        return view;
    }
    public void setView(GraphicComponent view) {
        this.view = view;
    }
    public GameObjectImpl getGameObject() {
        return gameObject;
    }
    public void setGameObject(GameObjectImpl gameObject) {
        this.gameObject = gameObject;
    }
}
