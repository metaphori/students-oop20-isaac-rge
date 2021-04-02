package ryleh.controller;

import ryleh.core.EntityBuilder;
import ryleh.model.GameObject;
import ryleh.view.GraphicComponent;

public class Entity {

    private GraphicComponent view;
    private GameObject gameObject;

    public Entity(final GraphicComponent view, final GameObject gameObject) {
        super();
        this.view = view;
        this.gameObject = gameObject;
    }

    public GraphicComponent getView() {
        return view;
    }
    public void setView(final GraphicComponent view) {
        this.view = view;
    }
    public GameObject getGameObject() {
        return gameObject;
    }
    public void setGameObject(final GameObject gameObject) {
        this.gameObject = gameObject;
    }
}
