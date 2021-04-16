package ryleh.model.components;

import java.util.Optional;

import ryleh.controller.Entity;
import ryleh.model.GameObject;
import ryleh.model.Type;
import ryleh.model.World;
import ryleh.model.events.EnemyCollisionEvent;
import ryleh.model.events.GameOverEvent;
import ryleh.model.events.ItemPickUpEvent;
import ryleh.model.events.NewLevelEvent;

public class CollisionComponent extends Component{
	private Type type;
	private boolean hasAlreadyColidedItem = false;
	private boolean doorCollidable;
	
	public CollisionComponent(World world, Type type) {
		super(world);
		this.type = type;
		this.hasAlreadyColidedItem = false;
		this.doorCollidable = false;
	}
	
	public void setDoorCollidable() {
		this.doorCollidable = true;
	}
	
	public void onUpdate(final double deltaTime) {
		super.onUpdate(deltaTime);
		if (!this.hasAlreadyColidedItem) {
			Optional<GameObject> colliding = world.getGameObjects().stream()
					.filter(obj -> obj.getType().equals(Type.PLAYER))
					.filter(obj -> obj.getHitBox().isCollidingWith(object.getHitBox()))
					.findFirst();
			if (colliding.isPresent()) {
				if (type.equals(Type.ITEM)) {
					world.notifyWorldEvent(new ItemPickUpEvent(colliding.get(), object));
					this.hasAlreadyColidedItem = true;
				} else if (type.equals(Type.DOOR) && doorCollidable) {
					world.notifyWorldEvent(new NewLevelEvent(colliding.get()));
					this.hasAlreadyColidedItem = true;
				}
				else {
					world.notifyWorldEvent(new EnemyCollisionEvent(colliding.get(), object));
				}
		    }
		}
	}

}
