package ryleh.model.components;

import java.util.Optional;

import ryleh.common.GameMath;
import ryleh.common.P2d;
import ryleh.common.V2d;
import ryleh.controller.events.EnemyCollisionEvent;
import ryleh.controller.events.ItemPickUpEvent;
import ryleh.model.GameObject;
import ryleh.model.Type;
import ryleh.model.World;
import ryleh.view.ViewHandler;

public class SpinnerComponent extends Component {
	
	   private long weaponTimer = System.currentTimeMillis();
	   private P2d position;
	   private V2d velocity;
	   private ViewHandler view;

	   public SpinnerComponent(final World world, final ViewHandler view) {
		   super(world);
		   this.position = new P2d(0, 0);
		   this.velocity = new V2d(0, 0);
		   this.view = view;
		}
		@Override
		public void onAdded(final GameObject object) {
			super.onAdded(object);
			this.position = object.getPosition();
		}
		@Override
	    public void onUpdate(final double dt) {
	    	//shoot();
	    	this.isCollidingWithPlayer();
	    }
		public void shoot() {
			if (weaponTimer - System.currentTimeMillis() >= 2000) {
				V2d directionToPlayer = new V2d(-1, 0);
		            //GameFactory.getInstance().createBullet(world, view, position, directionToPlayer);
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
