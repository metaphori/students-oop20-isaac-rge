package ryleh.common;

public class Circle2d implements Shape2d {

    private int radius;
    private P2d center;

    public Circle2d(final P2d center, final int radius) {
        this.radius = radius;
        this.center = center;
    }

    public Circle2d(final int radius) {
        this.radius = radius;
    }

    @Override
    public boolean contains(final P2d point) {
        return new V2d(this.center, point).module() <= this.radius;
    }

    @Override
    public void setPosition(final P2d position) {
        this.center = position;
    }

    @Override
    public P2d getPosition() {
        return this.center;
    }

    @Override
    public boolean intersect(Shape2d shape) {
        if (shape instanceof Circle2d) {
            return new V2d(this.center, shape.getPosition()).module() <= this.radius + ((Circle2d) shape).getRadius();
        }
        return false;
    }

    public int getRadius() {
        return this.radius;
    }

    @Override
    public String toString() {
        return "Circle2d [radius=" + radius + ", center=" + center + "]";
    }

}
