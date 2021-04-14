package ryleh.model.components;

import ryleh.common.P2d;
import ryleh.common.V2d;
import ryleh.controller.Entity;
import ryleh.model.GameObject;
import ryleh.model.World;

public class ShooterComponent extends Component {
	private ShootingComponent shooting;
	private P2d position;
	private final double bulletSpeed;
	private final Entity player;

	public ShooterComponent(final World world, final Entity player) {
		super(world);
		this.bulletSpeed = 0.15;
		this.position = new P2d(0, 0);
		this.player = player;
		shooting = new ShootingComponent(world, 0.5);
	}
	@Override
	public void onAdded(final GameObject object) {
		super.onAdded(object);
		shooting.onAdded(object);
		this.position = object.getPosition();
		//object.setPosition(this.position);
	}
	@Override
	public void onUpdate(final double dt) {
	    shoot();
	}
	public void shoot() {
	        //if (shooting.canShoot()) {
	            V2d directionToPlayer = new V2d(player.getGameObject().getPosition().x, player.getGameObject().getPosition().y)
	            		.sub(new V2d(this.position.x, this.position.y))
	            		.getNormalized()
	            		.mulLocal(bulletSpeed);
	            shooting.shoot(directionToPlayer);
	        //}
	    }
	public V2d getDirection() {
	    V2d directionToPlayer = new V2d(player.getGameObject().getPosition().x, player.getGameObject().getPosition().y)
           	.sub(new V2d(this.position.x, this.position.y))
            	.getNormalized()
            	.mulLocal(bulletSpeed);
	    return directionToPlayer;
	}
}
