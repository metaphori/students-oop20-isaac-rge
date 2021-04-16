package ryleh.model.components;

import java.util.Optional;
import ryleh.common.P2d;
import ryleh.common.V2d;
import ryleh.controller.Entity;
import ryleh.model.GameObject;
import ryleh.model.Type;
import ryleh.model.World;
/**
 * A class that provides the Component for lurker enemy type.
 */
public class LurkerComponent extends Component {
	private long adjustDirectionTimer = System.currentTimeMillis();
	private static final long ADJUST_DELAY = 250;
	private static final int MOVE_SPEED = 50;
	private static final double TPF = 0.016;
	private P2d position;
	private V2d velocity;
	private final GameObject player; 
	/**
	 * Constructor method.
	 * @param world World instance.
	 * @param entity Entity instance.
	 */
	public LurkerComponent(final World world, final Entity entity) {
		super(world);
	    player = entity.getGameObject();
	    this.position = new P2d(0, 0);
	    this.velocity = new V2d(0, 0);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onAdded(final GameObject object) {
		adjustVelocity();
	    super.onAdded(object);
	    this.position = object.getPosition();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onUpdate(final double deltaTime) {
		move();
		this.checkScreenBounds();
	}
	/**
	 * Generates new position to move to.
	 */
	private void move() {
		if (System.currentTimeMillis() - adjustDirectionTimer >= ADJUST_DELAY) {
			adjustVelocity();
			adjustDirectionTimer = System.currentTimeMillis();
		}
	    this.position.x = this.position.x + this.velocity.x;
	    this.position.y = this.position.y + this.velocity.y;
	    object.setPosition(this.position);
	}
	/**
	 * Checks game world bounds or collision with a rock and if true bounces back.
	 */
	private void checkScreenBounds() {
		if (object.getHitBox().isOutOfBounds(world.getBounds()) || this.isCollidingWithRock()) {
			this.velocity.x = -this.velocity.x;
	        this.velocity.y = -this.velocity.y;
	    }
	}
	/**
	 * Adjusts velocity vector to follow player direction.
	 */
	private void adjustVelocity() {
		final V2d directionToPlayer = new V2d(player.getPosition(), this.position)
								.getNormalized()
								.mul(MOVE_SPEED);
		velocity = velocity.addLocal(directionToPlayer).mul(TPF);
	}
	/**
	 * Checks if the GameObject collided with a rock.
	 * @return boolean value of collision check.
	 */
	private boolean isCollidingWithRock() {
		final Optional<GameObject> colliding = world.getGameObjects().stream()
				.filter(obj -> obj.getType().equals(Type.ROCK))
				.filter(obj -> obj.getHitBox().isCollidingWith(object.getHitBox()))
	    		.findFirst();
	    return colliding.isPresent();
	}
}
