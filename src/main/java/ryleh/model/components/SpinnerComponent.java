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
	   private double angle = 0;

	   public SpinnerComponent(final World world) {
		   super(world);
		   this.position = new P2d(0, 0);
		   this.velocity = new V2d(0, 0);
		}
		@Override
		public void onAdded(final GameObject object) {
			super.onAdded(object);
			this.position = object.getPosition();
		}
		@Override
	    public void onUpdate(final double dt) {
	    	shoot();
	    	angle=angle + Math.PI/40;
			if(angle>=Math.PI*2) {
				angle=0;
			}
	    	this.isCollidingWithPlayer();
	    }
		public void shoot() {
			if (System.currentTimeMillis()-weaponTimer >= 500) {
				V2d direction = new V2d(this.position.x * Math.cos(this.angle)-position.y * Math.sin(this.angle)
						,position.x * Math.sin(this.angle)+position.y * Math.cos(this.angle))
						//.sub(new V2d(this.position.x, this.position.y))
	            		.getNormalized()
	            		.mulLocal(bulletSpeed);;
				world.notifyWorldEvent(new BulletSpawnEvent(object, object.getHitBox().getForm().getCenter(), direction));
		        weaponTimer = System.currentTimeMillis();
		    }
		}
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
