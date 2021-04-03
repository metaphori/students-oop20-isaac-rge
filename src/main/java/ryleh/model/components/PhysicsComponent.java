package ryleh.model.components;

import ryleh.common.P2d;
import ryleh.common.V2d;
import ryleh.model.GameObject;
import ryleh.model.World;
import ryleh.model.physics.Direction;
import ryleh.model.physics.HitBox;

public class PhysicsComponent extends Component {
    private V2d velocity;
    private P2d position;
    private int speed;
    private Direction direction;

    public PhysicsComponent(final World world, final int speed) {
        super(world);
        this.position = new P2d(300, 300);
        this.velocity = new V2d(0, 0);
        this.speed = speed;
        this.direction = Direction.IDLE;
    }

    @Override
    public void onAdded(final GameObject object) {
        // TODO Auto-generated method stub
        super.onAdded(object);
        this.position = object.getPosition();
    }

    @Override
    public void onUpdate(final int dt) {
        P2d temp = this.position;
        this.position.x = this.position.x + velocity.x * dt * 0.001;
        final HitBox box = object.getHitBox();
        box.getForm().setPosition(position);
        //System.out.println(box);

        if (box.isOutOfBounds(world.getBounds())) {
            //System.exit(0);
            this.position = temp;
            box.getForm().setPosition(position);
        } else {
            object.setPosition(this.position);
        }
        temp = this.position;
        this.position.y = this.position.y + velocity.y * dt * 0.001;
        box.getForm().setPosition(this.position);

        if (box.isOutOfBounds(world.getBounds())) {
            //System.exit(0);
            this.position = temp;
            box.getForm().setPosition(position);
        } else {
            object.setPosition(this.position);
        }

       // System.out.println("Position of " + super.object + " is " + position);
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
