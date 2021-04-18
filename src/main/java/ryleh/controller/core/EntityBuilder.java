package ryleh.controller.core;

import ryleh.common.Point2d;
import ryleh.controller.EntityImpl;
import ryleh.model.GameObject;
import ryleh.model.GameObjectImpl;
import ryleh.model.Type;
import ryleh.model.components.AbstractComponent;
import ryleh.model.physics.HitBox;
import ryleh.view.graphics.GraphicComponent;

public class EntityBuilder {
    private final GameObject object;
    private GraphicComponent graphic;

    /**
     * Constructor method.
     */
    public EntityBuilder() {
        object = new GameObjectImpl();
        graphic = null; // TODO
    }

    /**
     * Constructor method.
     * 
     * @param type Type value
     * @return EntityBuilder instance.
     */
    public EntityBuilder type(final Type type) {
        object.setType(type);
        return this;
    }

    /**
     * Sets the position of a GameObject instance.
     * 
     * @param position Point2d instance.
     * @return EntityBuilder instance.
     */
    public EntityBuilder position(final Point2d position) {
        object.setPosition(position);
        return this;
    }

    /**
     * Sets the position of a GameObject instance.
     * 
     * @param x int value.
     * @param y int value.
     * @return EntityBuilder instance.
     */
    public EntityBuilder position(final int x, final int y) {
        object.setPosition(new Point2d(x, y));
        return this;
    }

    /**
     * Adds an AbstarctComponent instance to a GameObject.
     * 
     * @param component AbstactComponent instance.
     * @return EntityBuilder instance.
     */
    public EntityBuilder with(final AbstractComponent component) {
        try {
            object.addComponent(component);
        } catch (IllegalStateException e) {
            System.out.println("Something went wrong....Exception when trying to add component\n");
        }
        return this;
    }

    /**
     * Sets the GraphicComponent component.
     * 
     * @param view GraphicComponent instance.
     * @return EntityBuilder instance.
     */
    public EntityBuilder view(final GraphicComponent view) {
        graphic = view;
        return this;
    }

    /**
     * Sets the HitBox attribute.
     * 
     * @param bbox HitBox instance.
     * @return EntityBuilder instance.
     */
    public EntityBuilder bbox(final HitBox bbox) {
        object.setHitBox(bbox);
        return this;
    }

    /**
     * Creates a new EntityImpl instance with graphic and object attributes.
     * 
     * @return EntityBuilder instance.
     */
    public EntityImpl build() {
        return new EntityImpl(graphic, object);
    }
}
