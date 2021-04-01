package ryleh.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.geometry.Point2D;
import ryleh.model.components.Component;

public class GameObjectImpl implements GameObject {
    private Type type;
    private Point2D position;
    private List<Component> components;

    public GameObjectImpl() {
        position = new Point2D(0,0);
        components = new ArrayList<>();
    }

    /*
     * 
     */
    @Override
    public void onUpdate() {
        components.forEach(i -> i.onUpdate());
    }
    /*
     * 
     */
    @Override
    public Point2D getPosition() {
        return position;
    }
    public void setPosition(Point2D position) {
        this.position = position;
    }
    @Override
    public List<Component> getComponents() {
        return components;
    }
    @Override
    public Optional<Component> getComponent(final Class<Component> type) {
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((components == null) ? 0 : components.hashCode());
        result = prime * result + ((position == null) ? 0 : position.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GameObjectImpl other = (GameObjectImpl) obj;
        if (components == null) {
            if (other.components != null)
                return false;
        } else if (!components.equals(other.components))
            return false;
        if (position == null) {
            if (other.position != null)
                return false;
        } else if (!position.equals(other.position))
            return false;
        if (type != other.type)
            return false;
        return true;
    }
}
