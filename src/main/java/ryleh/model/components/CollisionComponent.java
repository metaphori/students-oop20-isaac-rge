package ryleh.model.components;

import java.util.Optional;

import ryleh.controller.Entity;
import ryleh.controller.events.EnemyCollisionEvent;
import ryleh.controller.events.GameOverEvent;
import ryleh.model.GameObject;
import ryleh.model.Type;
import ryleh.model.World;

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
			System.out.println("Sto collidendo \t");
			System.out.println(object.toString());
	    }
	}

}
