package ryleh.model.components;

import ryleh.common.Point2d;
import ryleh.common.Vector2d;
import ryleh.model.GameObject;
import ryleh.model.Type;
import ryleh.model.World;
import ryleh.model.physics.Direction;

public class PhysicsComponent extends AbstractComponent {
    private Vector2d velocity;
    private Point2d position;
    private int speed;
    private Direction direction;
    private Direction lastDirection;
    private Direction blocked;
    private Point2d lastPos;

    public PhysicsComponent(final World world, final int speed) {
        super(world);
        this.position = new Point2d(0, 0);
        this.velocity = new Vector2d(0, 0);
        this.lastPos = new Point2d(0, 0);
        this.speed = speed;
        this.direction = Direction.IDLE;
        this.lastDirection = Direction.DOWN;
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
        lastPos = new Point2d(this.position.getX(), this.position.getY());
        this.position = this.position.sum(velocity.multiply(0.001 * dt));
        super.getObject().setPosition(this.position);
    }

    protected void resetPos() {
        this.position = lastPos;
        this.setVelocityY(0);
        this.setVelocityX(0);
        this.direction = Direction.IDLE;
        super.getObject().setPosition(this.position);
    }

    protected boolean canMove() {
        return !(super.getObject().getHitBox().isOutOfBounds(super.getWorld().getBounds()) 
                || super.getWorld().getGameObjects().stream()
                .filter(i -> i.getType().equals(Type.ROCK))
                .anyMatch(r -> super.getObject().getHitBox().isCollidingWith(r.getHitBox())));
    }

    public void setVelocityX(final int sign) {
        this.velocity.setX(sign * speed); 
    }

    public void setVelocityY(final int sign) {
        this.velocity.setY(sign * speed);
    }

    public void setVelocity(final Vector2d velocity) {
        this.velocity = velocity.multiply(speed);
    }

    public void setDirection(final Direction direction) { 
    	if(!this.direction.equals(Direction.IDLE)) {
    		this.lastDirection = this.direction;
    	}
        this.direction = direction;
    }
    public Direction getLastDirection() {
        return this.lastDirection;
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
    //THIS IS A PRIMITIVE METHOD WITH NO WAY OF CHECKING BOUNDS OR HITBOXES
	public void setPosition(Point2d position) {
		this.lastPos = position;
		this.position = position;
		super.getObject().setPosition(this.position);
	}
}
