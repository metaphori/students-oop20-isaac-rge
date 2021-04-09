package ryleh.model.components;

import ryleh.common.P2d;
import ryleh.common.V2d;
import ryleh.model.GameObject;
import ryleh.model.Type;
import ryleh.model.World;
import ryleh.model.physics.Direction;

public class PhysicsComponent extends Component {
    private V2d velocity;
    private P2d position;
    private int speed;
    private Direction direction;
    private Direction blocked;
    private P2d lastPos;

    public PhysicsComponent(final World world, final int speed) {
        super(world);
        this.position = new P2d(0, 0);
        this.velocity = new V2d(0, 0);
        this.lastPos = new P2d(0, 0);
        this.speed = speed;
        this.direction = Direction.IDLE;
        this.blocked = Direction.IDLE;
    }

    @Override
    public void onAdded(final GameObject object) {
        super.onAdded(object);
        this.position = object.getPosition();
        this.move(0);
    }

    @Override
    public void onUpdate(final double dt) {
        if (this.canMove() && !this.blocked.equals(this.direction)) {
            move(dt);
            return;
        } else {
            this.blocked = this.direction;
            resetPos();
        }
    }

    protected void move (final double dt) {
        lastPos = new P2d(this.position.x, this.position.y);
        this.position = this.position.sum(velocity.mul(0.001*dt));
        object.setPosition(this.position);
    }

    protected void resetPos() {
        this.position = lastPos;
        this.setVelocityY(0);
        this.setVelocityX(0);
        this.direction = Direction.IDLE;
        object.setPosition(this.position);
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
    public Direction getBlocked() {
        return this.blocked;
    }
    public void resetBlocked() {
        this.blocked = Direction.IDLE;
    }

}
