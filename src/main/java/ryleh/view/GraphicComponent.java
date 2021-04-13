package ryleh.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Scene;

public interface GraphicComponent {
    public void render(Point2D position, double deltaF);

    public void onAdded(Scene scene);

    public Object getNode();

    public void onRemoved(final EventHandler<ActionEvent> event);
}
