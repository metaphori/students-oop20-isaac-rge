package ryleh.model.components;

import java.util.Optional;
import java.util.Random;

import ryleh.common.P2d;
import ryleh.controller.ItemPickUpEvent;
import ryleh.model.GameObject;
import ryleh.model.Type;
import ryleh.model.World;

public class ItemComponent extends Component {

	private P2d position;
	public ItemComponent(final World world) {
		super(world);
		this.position = new P2d(0, 0);
	}

	@Override
	public void onAdded(final GameObject object) {
		// TODO Auto-generated method stub
		super.onAdded(object);
		this.position = object.getPosition();
	}

	@Override
	public void onUpdate(final double dt) {
		// TODO Auto-generated method stub
		super.onUpdate(dt);
		isCollidingWithPlayer();
	}

	private void isCollidingWithPlayer() {
		Optional<GameObject> colliding = world.getGameObjects().stream()
				.filter(obj -> obj.getType().equals(Type.PLAYER))
				.filter(obj -> obj.getHitBox().isCollidingWith(object.getHitBox()))
				.findFirst();
		if (colliding.isPresent()) {
			world.notifyWorldEvent(new ItemPickUpEvent(colliding.get(), object));
		}
	}
}
