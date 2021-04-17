package ryleh.common;

public interface Shape2d {
    boolean contains(Point2d point);

    void setPosition(Point2d position);

    Point2d getPosition();

    boolean intersect(Shape2d shape);
    
    Point2d getCenter();
}
