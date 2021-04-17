package ryleh.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.geometry.Point2D;
import ryleh.common.Point2d;
import ryleh.model.components.AbstractComponent;
import ryleh.model.physics.CircleHitBox;
import ryleh.model.physics.HitBox;

public class GameObjectImpl implements GameObject {
    private Type type;
    private String id;
    private Point2d position;
    private World world;
    private HitBox box;
    private List<AbstractComponent> components;
    private int zIndex;

    public GameObjectImpl() {
        position = new Point2d(0,0);
        components = new ArrayList<>();
        this.box = new CircleHitBox(190);
    }

    @Override
    public void onAdded(final World world) {
        this.world = world;
        this.id = world.generateId("gameObject");
        box.getForm().setPosition(position);
    }

    @Override
    public void onUpdate(final double deltaTime) {
        components.forEach(i -> i.onUpdate(deltaTime));
    }

    @Override
    public Point2d getPosition() {
        return position;
    }
    @Override
    public void setPosition(final Point2d position) {
        this.position = position;
        box.getForm().setPosition(position);
    }
    @Override
    public List<AbstractComponent> getComponents() {
        return components;
    }
    @Override
    public Optional<? extends AbstractComponent> getComponent(final Class<? extends AbstractComponent> type) {
        return components.stream().filter(type::isInstance).findAny();
    }
    @Override
    public void addComponent(final AbstractComponent component) {
        if (this.components.stream().anyMatch(i -> component.getClass().isInstance(i))) {
            throw new IllegalStateException();
        }
        else {
            this.components.add(component);
            component.onAdded(this);
        }
    }
    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void setType(final Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "GameObjectImpl [id=" + id + ", type=" + type + "]";
    }

    @Override
    public void setHitBox(final HitBox box) {
        this.box = box;
    }

    @Override
    public HitBox getHitBox() {
        return this.box;
    }

	@Override
	public void setzIndex(int zIndex) {
		this.zIndex = zIndex;
	}

	@Override
	public int getzIndex() {
		return this.zIndex;
	}

}
