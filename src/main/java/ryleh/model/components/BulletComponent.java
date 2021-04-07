package ryleh.model.components;

import ryleh.common.P2d;
import ryleh.common.V2d;
import ryleh.model.GameObject;
import ryleh.model.World;
import ryleh.view.ViewHandler;

public class BulletComponent extends Component{
	private P2d position;
	private int speed = 10;
	private V2d velocity;

	public BulletComponent(World world , P2d origin, V2d direction) {
		super(world);
		System.out.println("Sono Spawnato");
		this.position = origin;
		this.velocity = direction.mul(speed);
	   // this.velocity = new V2d(0, 0);
	}
	@Override
	public void onAdded(GameObject object) {
		super.onAdded(object);
		object.setPosition(this.position);
	}
	public void onUpdate(final double dt) {
	    move(dt);
	    cheeckCollision();
	 }
	private void cheeckCollision() {
	}
	protected void move(final double dt) {
		this.position = this.position.sum(velocity.mul(dt * 0.1));
		object.setPosition(this.position);
	}

}
