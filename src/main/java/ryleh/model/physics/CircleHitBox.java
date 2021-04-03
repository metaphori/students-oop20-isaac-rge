package ryleh.model.physics;

import ryleh.common.Circle2d;
import ryleh.common.Shape2d;
import ryleh.common.V2d;

public class CircleHitBox implements HitBox {

    private final Circle2d form;

    public CircleHitBox(final Circle2d form) {
        this.form = form;
    }
    public CircleHitBox(final int radius) {
        this.form = new Circle2d(radius);
    }

    @Override
    public Shape2d getForm() {
        return this.form;
    }

    @Override
    public boolean isCollidingWith(final HitBox box) {
        return this.form.intersect(box.getForm());
    }

    @Override
    public boolean isOutOfBounds(final Shape2d bounds) {
        return !(bounds.contains(this.form.getPosition()) 
                && bounds.contains(this.form.getPosition().sum(new V2d(0, form.getRadius())))
                && bounds.contains(this.form.getPosition().sum(new V2d(0, -form.getRadius())))
                && bounds.contains(this.form.getPosition().sum(new V2d(form.getRadius(), 0)))
                && bounds.contains(this.form.getPosition().sum(new V2d(-form.getRadius(), 0))));
    }

}
