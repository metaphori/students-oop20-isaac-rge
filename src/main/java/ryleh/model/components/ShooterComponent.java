package ryleh.model.components;

import ryleh.common.P2d;
import ryleh.common.V2d;
import ryleh.controller.Entity;
import ryleh.model.GameObject;
import ryleh.model.World;
/**
 * A class that provides the Component for shooter enemy type.
 */
public class ShooterComponent extends Component {
	private ShootingComponent shooting;
	private P2d position;
	private static final double BULLET_SPEED = 0.15;
	private final Entity player;
	/**
	 * Constructor method.
	 * @param world World instance.
	 * @param player Entity instance.
	 */
	public ShooterComponent(final World world, final Entity player) {
		super(world);
		this.position = new P2d(0, 0);
		this.player = player;
		shooting = new ShootingComponent(world, 0.5);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onAdded(final GameObject object) {
		super.onAdded(object);
		shooting.onAdded(object);
		this.position = object.getPosition();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onUpdate(final double dt) {
	    shoot();
	}
	/**
	 * A method that calls shoot method from ShootingComponent if the shooter is able to shoot.
	 */
	public void shoot() {
		shooting.shoot(this.getDirection());
	}
	/**
	 * A method to get the direction towards the player.
	 * @return a V2d value representing a direction.
	 */
	public V2d getDirection() {
	    return new V2d(player.getGameObject().getPosition().x, player.getGameObject().getPosition().y)
           	.sub(new V2d(this.position.x, this.position.y))
            	.getNormalized()
            	.mulLocal(BULLET_SPEED);
	}
}
