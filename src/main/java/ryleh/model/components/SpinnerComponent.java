package ryleh.model.components;

import java.util.Optional;


import javafx.geometry.Point2D;
import ryleh.common.GameMath;
import ryleh.common.P2d;
import ryleh.common.V2d;
import ryleh.controller.Entity;
import ryleh.model.GameObject;
import ryleh.model.Type;
import ryleh.model.World;
import ryleh.model.events.BulletSpawnEvent;
import ryleh.model.events.EnemyCollisionEvent;
import ryleh.model.events.ItemPickUpEvent;
import ryleh.view.ViewHandler;

public class SpinnerComponent extends Component {
	
	   private long weaponTimer = System.currentTimeMillis();
	   private P2d position;
	   private V2d velocity;
	   private double bulletSpeed = 0.15;
	   private Entity player;

	   public SpinnerComponent(final World world,final Entity player) {
		   super(world);
		   this.position = new P2d(0, 0);
		   this.velocity = new V2d(0, 0);
		   this.player = player;
		}
		@Override
		public void onAdded(final GameObject object) {
			super.onAdded(object);
			this.position = object.getPosition();
		}
		@Override
	    public void onUpdate(final double dt) {
	    	shoot();
	    	this.isCollidingWithPlayer();
	    }
		public void shoot() {
			if (System.currentTimeMillis()-weaponTimer >= 2000) {
				V2d directionToPlayer = new V2d(player.getGameObject().getPosition().x, player.getGameObject().getPosition().y)
	            		.sub(new V2d(this.position.x, this.position.y))
	            		.getNormalized()
	            		.mulLocal(bulletSpeed);
		            //GameFactory.getInstance().createBullet(world, view, position, directionToPlayer);
				world.notifyWorldEvent(new BulletSpawnEvent(object, object.getHitBox().getForm().getCenter(), directionToPlayer));
		        weaponTimer = System.currentTimeMillis();
		    }
		}
		//TODO this method repeats in every enemy component
		private void isCollidingWithPlayer() {
			Optional<GameObject> colliding = world.getGameObjects().stream()
					.filter(obj -> obj.getType().equals(Type.PLAYER))
					.filter(obj -> obj.getHitBox().isCollidingWith(object.getHitBox()))
					.findFirst();
			if (colliding.isPresent()) {
				world.notifyWorldEvent(new EnemyCollisionEvent(colliding.get(), object));
			}
		}
}
