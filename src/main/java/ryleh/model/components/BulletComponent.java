package ryleh.model.components;

import java.util.Optional;

import ryleh.common.P2d;
import ryleh.common.V2d;
import ryleh.controller.events.BulletCollisionEvent;
import ryleh.controller.events.FireCollisionEvent;
import ryleh.controller.events.RemoveEntityEvent;
import ryleh.model.GameObject;
import ryleh.model.Type;
import ryleh.model.World;
import ryleh.view.ViewHandler;

public class BulletComponent extends Component {
	private P2d position;
	private int speed = 10;
	private V2d velocity;

	public BulletComponent(World world , P2d origin, V2d direction) {
		super(world);
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
	    checkCollision();
	 }
	//TODO could extract an interface for this method
	private void checkCollision() {
		Optional<GameObject> colliding = Optional.empty();
		if (!object.getType().equals(Type.PLAYER_BULLET)) {
			colliding = world.getGameObjects().stream()
					.filter(obj -> obj.getType().equals(Type.PLAYER) || obj.getType().equals(Type.ROCK) )
					.filter(obj -> obj.getHitBox().isCollidingWith(object.getHitBox()))
					.findFirst();
		} else {
			colliding = world.getGameObjects().stream()
					.filter(obj -> obj.getType().equals(Type.ENEMY_DRUNK) || obj.getType().equals(Type.ENEMY_DRUNKSPINNER) 
							|| obj.getType().equals(Type.ENEMY_LURKER) || obj.getType().equals(Type.ENEMY_SHOOTER) 
							|| obj.getType().equals(Type.ENEMY_SPINNER) || obj.getType().equals(Type.ROCK) || obj.getType().equals(Type.ITEM))
					.filter(obj -> obj.getHitBox().isCollidingWith(object.getHitBox()))
					.findFirst();
		}
		if (colliding.isPresent()) {
			world.notifyWorldEvent(new BulletCollisionEvent(colliding.get(), object));
		}
		if (colliding.isPresent() || object.getHitBox().isOutOfBounds(world.getBounds())) {
			world.notifyWorldEvent(new RemoveEntityEvent(object));
		}
	}
	protected void move(final double dt) {
		this.position = this.position.sum(velocity.mul(dt * 0.1));
		object.setPosition(this.position);
	}

}
