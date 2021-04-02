package ryleh.view;

import javafx.geometry.Point2D;
import javafx.scene.Scene;

public interface GraphicComponent {
    public void render();

    public void render(Point2D position);

    public void onAdded(Scene scene);
}
