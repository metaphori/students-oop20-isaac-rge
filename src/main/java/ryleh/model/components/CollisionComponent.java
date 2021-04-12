package ryleh.model.components;

import java.util.Optional;

import ryleh.controller.Entity;
import ryleh.model.GameObject;
import ryleh.model.Type;
import ryleh.model.World;
import ryleh.model.events.EnemyCollisionEvent;
import ryleh.model.events.GameOverEvent;

public class CollisionComponent extends Component{
	
	public CollisionComponent(World world) {
		super(world);
	}
	
	public void onUpdate(final double deltaTime) {
		super.onUpdate(deltaTime);
		Optional<GameObject> colliding = world.getGameObjects().stream()
				.filter(obj -> obj.getType().equals(Type.PLAYER))
				.filter(obj -> obj.getHitBox().isCollidingWith(object.getHitBox()))
				.findFirst();
		if (colliding.isPresent()) {
			world.notifyWorldEvent(new EnemyCollisionEvent(colliding.get(), object));
	    }
	}

}
