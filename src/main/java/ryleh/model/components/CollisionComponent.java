package ryleh.model.components;

import java.util.Optional;
import ryleh.controller.events.EnemyCollisionEvent;
import ryleh.controller.events.ItemPickUpEvent;
import ryleh.model.GameObject;
import ryleh.model.Type;
import ryleh.model.World;

public class CollisionComponent extends Component {
	
	private final Type type;
	private boolean hasAlreadyColided = false;
	//private boolean doorCollidable;
	
	/**
	 * Add a component to check the collision between enemies and player.
	 * @param world
	 * @param type The type of the concerned GameObject 
	 */
	public CollisionComponent(final World world, final Type type) {
		super(world);
		this.type = type;
		this.hasAlreadyColided = false;
		//this.doorCollidable = false;
	}
	
	public void setDoorCollidable() {
		//this.doorCollidable = true;
	}
	/**
	 * A method which checks for collisions with other GameObjects and notifies world instance in case of event.
	 */
	public void onUpdate(final double deltaTime) {
		super.onUpdate(deltaTime);
		if (!this.hasAlreadyColided) {
			Optional<GameObject> colliding = world.getGameObjects().stream()
					.filter(obj -> obj.getType().equals(Type.PLAYER))
					.filter(obj -> obj.getHitBox().isCollidingWith(object.getHitBox()))
					.findFirst();
			if (colliding.isPresent()) {
				if (type.equals(Type.ITEM)) {
					world.notifyWorldEvent(new ItemPickUpEvent(colliding.get()));
					this.hasAlreadyColided = true;
				/*} else if (type.equals(Type.DOOR)) {
					world.notifyWorldEvent(new NewLevelEvent());
				*/} else {
					world.notifyWorldEvent(new EnemyCollisionEvent(colliding.get()));
				}
		    }
		}
	}

}
