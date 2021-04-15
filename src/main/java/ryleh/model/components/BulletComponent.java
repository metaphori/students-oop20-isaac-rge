package ryleh.model.components;

import java.util.Optional;

import ryleh.common.P2d;
import ryleh.common.V2d;
import ryleh.controller.events.EnemyCollisionEvent;
import ryleh.controller.events.RemoveEntityEvent;
import ryleh.model.GameObject;
import ryleh.model.Type;
import ryleh.model.World;

public class BulletComponent extends Component {
	
	private P2d position;
	private int speed = 10;
	private V2d velocity;

	public BulletComponent(final World world, final P2d origin, final V2d direction) {
		super(world);
		this.position = origin;
		this.velocity = direction.mul(speed);
	   // this.velocity = new V2d(0, 0);
	}
	@Override
	public void onAdded(final GameObject object) {
		super.onAdded(object);
		object.setPosition(this.position);
	}
	public void onUpdate(final double dt) {
	    move(dt);
	    checkCollision();
	 }
	private void checkCollision() {
		Optional<GameObject> colliding = Optional.empty();
		if (!object.getType().equals(Type.PLAYER_BULLET)) {
			colliding = this.checkPlayerCollision();
		} else {
			colliding = this.checkEnemyCollisiom();
		}
		if (colliding.isPresent()) {
			world.notifyWorldEvent(new EnemyCollisionEvent(colliding.get()));
		}
		if (colliding.isPresent() || object.getHitBox().isOutOfBounds(world.getBounds())) {
			world.notifyWorldEvent(new RemoveEntityEvent(object));
		}
	}
	/**
	 * Check if the bullet of an enemy is colliding with player or with an obstacle.
	 * @return An Optional that represents the game object which is colliding with the current bullet
	 */
	private Optional<GameObject> checkPlayerCollision() {
		return world.getGameObjects().stream()
				.filter(obj -> obj.getType().equals(Type.PLAYER) || obj.getType().equals(Type.ROCK)
						|| obj.getType().equals(Type.ITEM))
				.filter(obj -> obj.getHitBox().isCollidingWith(object.getHitBox()))
				.findFirst();
	}
	/**
	 * Check if the bullet of an enemy is colliding with enemies or with an obstacle.
	 * @return n Optional that represents the game object which is colliding with the current bullet
	 */
	private Optional<GameObject> checkEnemyCollisiom() {
		return world.getGameObjects().stream()
				.filter(obj -> obj.getType().equals(Type.ENEMY_DRUNK) || obj.getType().equals(Type.ENEMY_DRUNKSPINNER) 
						|| obj.getType().equals(Type.ENEMY_LURKER) || obj.getType().equals(Type.ENEMY_SHOOTER) 
						|| obj.getType().equals(Type.ENEMY_SPINNER) || obj.getType().equals(Type.ROCK) || obj.getType().equals(Type.ITEM))
				.filter(obj -> obj.getHitBox().isCollidingWith(object.getHitBox()))
				.findFirst();
	}
	protected void move(final double dt) {
		this.position = this.position.sum(velocity.mul(dt * 0.1));
		object.setPosition(this.position);
	}

}
