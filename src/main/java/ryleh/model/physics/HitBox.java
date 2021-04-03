package ryleh.model.physics;

import ryleh.common.Shape2d;

public interface HitBox {
    Shape2d getForm();

    boolean isCollidingWith(HitBox box);
    
    boolean isOutOfBounds(Shape2d bounds);

}
