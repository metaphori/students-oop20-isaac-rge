package ryleh.common;

public interface Shape2d {
    boolean contains(P2d point);

    void setPosition(P2d position);

    P2d getPosition();

    boolean intersect(Shape2d shape);
    
    P2d getCenter();
}
