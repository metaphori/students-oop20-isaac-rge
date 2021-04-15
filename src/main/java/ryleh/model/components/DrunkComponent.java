package ryleh.model.components;

import java.util.Optional;
import ryleh.common.GameMath;
import ryleh.common.P2d;
import ryleh.common.V2d;
import ryleh.model.GameObject;
import ryleh.model.Type;
import ryleh.model.World;
/**
 * A class that provides the Component for drunk enemy type.
 */
public class DrunkComponent extends Component {
	
	private V2d velocity;
	private P2d position;
    private double angleAdjustRate = GameMath.randomInRange(0, 0.15);
    private double directionAngle = GameMath.toDegrees(GameMath.randomInRange(-1, 1) * Math.PI * 2);
    private static final double MOVE_SPEED = 0.05;
    private final double tx = GameMath.randomInRange(1000, 10_000);
    private static final double ROTATION_ANGLE = 20;
    private static final double TPF = 0.01;
    /**
     * Constructor method.
     * @param world World instance.
     */
	public DrunkComponent(final World world) {
		super(world);
		this.position = new P2d(0, 0);
		this.velocity = new V2d(0, 0);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onAdded(final GameObject object) {
		super.onAdded(object);
		this.position = object.getPosition();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	 public void onUpdate(final double deltaTime) {
		adjustAngle();
	    move(deltaTime);
	    checkScreenBounds();
	}
	/**
	 * Adjusts the direction angle.
	 */
	private void adjustAngle() {
		if (GameMath.randomBoolean(angleAdjustRate)) {
			// never rotate further than 20 degrees
			directionAngle += Math.min(GameMath.toDegrees(GameMath.smoothNoise(tx) - 0.5), ROTATION_ANGLE);
		}
	}
	/**
	 * Generates new direction vector to move to.
	 * @param deltaTime time passed between each frame.
	 */
	private void move(final double deltaTime) {
		final V2d directionVector = V2d.fromAngle(directionAngle).mulLocal(MOVE_SPEED);
	    this.velocity.addLocal(directionVector).getNormalized().mulLocal(deltaTime * TPF); //add time per frame value to mulLocal
	    this.position = this.position.sum(this.velocity);
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
