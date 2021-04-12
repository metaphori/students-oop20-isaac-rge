package ryleh.model.components;

import java.util.Optional;

import ryleh.model.GameObject;
import ryleh.model.Type;
import ryleh.model.World;
import ryleh.model.events.FireCollisionEvent;

public class FireComponent extends Component {
	
	public FireComponent(final World world) {
		super(world);
	}

	@Override
	public void onUpdate(final double deltaTime) {
		super.onUpdate(deltaTime);
		isColliding();
	}

	@Override
	public void onAdded(final GameObject object) {
		super.onAdded(object);
	}
	private void isColliding() {
		Optional<GameObject> colliding = world.getGameObjects().stream()
				.filter(obj -> obj.getType().equals(Type.PLAYER))
				.filter(obj -> obj.getHitBox().isCollidingWith(object.getHitBox()))
				.findFirst();
		if (colliding.isPresent()) {
			world.notifyWorldEvent(new FireCollisionEvent(colliding.get(), object));
		}

	}
	
}
