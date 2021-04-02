package ryleh.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.geometry.Point2D;
import ryleh.common.P2d;
import ryleh.model.components.Component;

public class GameObjectImpl implements GameObject {
    private Type type;
    private String id;
    private P2d position;
    private World world;
    private List<Component> components;

    public GameObjectImpl() {
        position = new P2d(0,0);
        components = new ArrayList<>();
    }

    @Override
    public void onAdded(final World world) {
        this.world = world;
        this.id = world.generateId("gameObject");
    }

    @Override
    public void onUpdate() {
        components.forEach(i -> i.onUpdate());
    }

    @Override
    public P2d getPosition() {
        return position;
    }
    @Override
    public void setPosition(final P2d position) {
        this.position = position;
    }
    @Override
    public List<Component> getComponents() {
        return components;
    }
    @Override
    public Optional<Component> getComponent(final Class<? extends Component> type) {
        return components.stream().filter(type::isInstance).findAny();
    }
    @Override
    public void addComponent(final Component component) {
        this.components.add(component);
        component.setGameObject(this);
    }
    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "GameObjectImpl [id=" + id + ", type=" + type + "]";
    }

}
