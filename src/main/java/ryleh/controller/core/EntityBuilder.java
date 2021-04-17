package ryleh.controller.core;

import ryleh.common.Point2d;
import ryleh.controller.EntityImpl;
import ryleh.model.GameObject;
import ryleh.model.GameObjectImpl;
import ryleh.model.Type;
import ryleh.model.WorldImpl;
import ryleh.model.components.Component;
import ryleh.model.physics.HitBox;
import ryleh.view.GraphicComponent;

public class EntityBuilder {
    private GameObject object;
    private GraphicComponent graphic;

    public EntityBuilder() {
        object = new GameObjectImpl();
        graphic = null; //TODO
    }

    public EntityBuilder type(final Type type) {
        object.setType(type);
        return this;
    }

    public EntityBuilder position(final Point2d position) {
        object.setPosition(position);
        return this;
    }
    public EntityBuilder position(final int x, final int y) {
        object.setPosition(new Point2d(x, y));
        return this;
    }

    public EntityBuilder with(final Component component) {
        try {
            object.addComponent(component);
        } catch (IllegalStateException e){
            //TODO log system to show into JavaFX Scene
        }
        return this;
    }

    public EntityBuilder view(final GraphicComponent view) {
        graphic = view;
        return this;
    }

    public EntityBuilder bbox(final HitBox bbox) {
        object.setHitBox(bbox);
        return this;
    }

    public EntityImpl build() {
        return new EntityImpl(graphic, object);
    }

    public EntityBuilder zIndex(final int zIndex) {
	object.setzIndex(zIndex);
	return this;
    }
}
