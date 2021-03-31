package ryleh.view;

import javafx.geometry.Point2D;

public interface View {
    public void render();

    public void render(Point2D position);
}
