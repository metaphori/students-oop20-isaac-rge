package ryleh.model.components;

import ryleh.common.P2d;
import ryleh.common.V2d;
import ryleh.model.GameObject;
import ryleh.model.World;
import ryleh.view.ViewHandler;

public class BulletComponent extends Component{
	private P2d position;
	private double speed = 25;
	private V2d velocity;

	public BulletComponent(World world , P2d origin, V2d direction) {
		super(world);
		this.position = origin;
		this.velocity = direction.mul(speed);
	}
	@Override
	public void onAdded(GameObject object) {
		super.onAdded(object);
		this.position = object.getPosition();
	}
	public void onUpdate() {
	    move();
	    cheeckCollision();
	 }
	private void cheeckCollision() {
		// TODO Auto-generated method stub
		
	}
	private void move() {
		
		
	}

}
