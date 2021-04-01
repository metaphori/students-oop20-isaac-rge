package ryleh.view;

import javafx.geometry.Point2D;

public interface GraphicComponent {
    public void render();

    public void render(Point2D position);
}
