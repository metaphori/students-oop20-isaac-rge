package ryleh.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.geometry.Point2D;
import ryleh.common.P2d;
import ryleh.model.components.Component;
import ryleh.model.physics.CircleHitBox;
import ryleh.model.physics.HitBox;

public class GameObjectImpl implements GameObject {
    private Type type;
    private String id;
    private P2d position;
    private World world;
    private HitBox box;
    private List<Component> components;

    public GameObjectImpl() {
        position = new P2d(0,0);
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
    public void onUpdate(final int deltaTime) {
        components.forEach(i -> i.onUpdate(deltaTime));
    }

    @Override
    public P2d getPosition() {
        return position;
    }
    @Override
    public void setPosition(final P2d position) {
        this.position = position;
        box.getForm().setPosition(position);
    }
    @Override
    public List<Component> getComponents() {
        return components;
    }
    @Override
    public Optional<? extends Component> getComponent(final Class<? extends Component> type) {
        return components.stream().filter(type::isInstance).findAny();
    }
    @Override
    public void addComponent(final Component component) {
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

}
