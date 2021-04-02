package ryleh.model.physics;

public interface HitBox<T> {

    T getForm();
    boolean isCollidingWith(HitBox<T> box);
}
