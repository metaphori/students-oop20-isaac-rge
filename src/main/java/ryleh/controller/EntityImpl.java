package ryleh.controller;

import ryleh.model.GameObject;
import ryleh.view.graphics.GraphicComponent;

public class EntityImpl implements Entity {

    private GraphicComponent view;
    private GameObject gameObject;

    public EntityImpl(final GraphicComponent view, final GameObject gameObject) {
        super();
        this.view = view;
        this.gameObject = gameObject;
    }

    /**
     * Creates an Entity whose purpose is still not clear. His view and game object
     * need to be set.
     */
    public EntityImpl() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GraphicComponent getView() {
        return view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setView(final GraphicComponent view) {
        this.view = view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameObject getGameObject() {
        return gameObject;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGameObject(final GameObject gameObject) {
        this.gameObject = gameObject;
    }
}
