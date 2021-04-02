package ryleh.model.components;

import ryleh.common.P2d;
import ryleh.common.V2d;
import ryleh.model.GameObject;
import ryleh.model.World;

public class PhysicsComponent extends Component {
    private V2d velocity;
    private P2d position;
    private static int SPEED = 5;

    public PhysicsComponent(final World world) {
        super(world);
        this.position = new P2d(960, 300);
        this.velocity = new V2d(0, 0);
    }

    @Override
    public void onAdded(final GameObject object) {
        // TODO Auto-generated method stub
        super.onAdded(object);
        this.position = object.getPosition();
    }

    @Override
    public void onUpdate() {
        P2d temp = this.position;
        this.position.x = this.position.x + velocity.x;

        if (world.isOutOfBounds(this.position)) {
            this.position = temp;
            this.setVelocityX(0);
        } else {
            object.setPosition(this.position);
        }
        temp = this.position;
        this.position.y = this.position.y + velocity.y;

        if (world.isOutOfBounds(this.position)) {
            this.position = temp;
            this.setVelocityY(0);
        } else {
            object.setPosition(this.position);
        }

       // System.out.println("Position of " + super.object + " is " + position);
    }

    public void setVelocityX(final int sign) {
        this.velocity.x = sign * SPEED;
    }

    public void setVelocityY(final int sign) {
        this.velocity.y = sign * SPEED;
    }

}
