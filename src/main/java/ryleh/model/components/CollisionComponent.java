package ryleh.model.components;

import java.util.Optional;
import ryleh.model.GameObject;
import ryleh.model.Type;
import ryleh.model.World;
import ryleh.model.events.EnemyCollisionEvent;
import ryleh.model.events.ItemPickUpEvent;
/**
 *A class used as a Component for GameObjects to get collisions.
 */
public class CollisionComponent extends Component {
	private final Type type;
	private boolean hasAlreadyColided = false;
	/** 
	 * A constructor method.
	 * @param world world instance.
	 * @param type type of GameObject.
	 */
	public CollisionComponent(final World world, final Type type) {
		super(world);
		this.type = type;
		this.hasAlreadyColided = false;
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
				if(type.equals(Type.ITEM)) {
					world.notifyWorldEvent(new ItemPickUpEvent(colliding.get(), object));
					this.hasAlreadyColided = true;
				}
				else {
					world.notifyWorldEvent(new EnemyCollisionEvent(colliding.get(), object));
				}
		    }
		}
	}

}
