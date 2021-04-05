package ryleh.model.components;

import ryleh.common.P2d;
import ryleh.common.V2d;
import ryleh.model.GameObject;
import ryleh.model.Type;
import ryleh.model.World;
import ryleh.model.physics.Direction;
import ryleh.model.physics.HitBox;

public class PhysicsComponent extends Component {
    private V2d velocity;
    private P2d position;
    private int speed;
    private Direction direction;
    private P2d lastPos;

    public PhysicsComponent(final World world, final int speed) {
        super(world);
        this.position = new P2d(0, 0);
        this.velocity = new V2d(0, 0);
        this.lastPos = new P2d(0, 0);
        this.speed = speed;
        this.direction = Direction.IDLE;
    }

    @Override
    public void onAdded(final GameObject object) {
        super.onAdded(object);
        this.position = object.getPosition();
    }

    @Override
    public void onUpdate(final int dt) {
        if (this.canMove()) {
            move(dt);
        } else {
            resetPos();
        }
        object.setPosition(this.position);
    }

    protected void move (final int dt) {
        lastPos = new P2d(this.position.x, this.position.y);
        this.position = this.position.sum(velocity.mul(dt * 0.001));
    }

    protected void resetPos() {
        this.position = lastPos;
        this.setVelocityY(0);
        this.setVelocityX(0);
        this.direction = Direction.IDLE;
    }

    protected boolean canMove() {
        return !(object.getHitBox().isOutOfBounds(world.getBounds()) || world.getGameObjects().stream()
                .filter(i -> i.getType().equals(Type.ROCK)).anyMatch(r -> object.getHitBox().isCollidingWith(r.getHitBox())));
    }

    public void setVelocityX(final int sign) {
        this.velocity.x = sign * speed;
    }

    public void setVelocityY(final int sign) {
        this.velocity.y = sign * speed;
    }

    public void setVelocity(final V2d velocity) {
        this.velocity = velocity.mul(speed);
    }

    public void setDirection(final Direction direction) { 
        this.direction = direction;
    }
    public Direction getDirection() {
        return this.direction;
    }

}
