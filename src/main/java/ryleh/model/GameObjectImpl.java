package ryleh.model;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;
import ryleh.controller.GameObjectController;
import ryleh.model.components.Component;

public class GameObjectImpl implements GameObject {
    private Type type;
    private Point2D position;
    private List<? extends Component> components;

    public GameObjectImpl() {
        position = new Point2D(0,0);
        components = new ArrayList<>();
    }

    public void onUpdate () {
        position = position.add(new Point2D (1, 1));
    }
    public Point2D getPosition() {
        return position;
    }
    public void setPosition(Point2D position) {
        this.position = position;
    }
    @Override
    public List<? extends Component> getComponents() {
        return components;
    }
    @Override
    public Component getComponent(final Class<? extends Component> type) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public void addComponent(final Component component) {
        // TODO Auto-generated method stub
    }
    @Override
    public Type getType() {
        return type;
    }
}
