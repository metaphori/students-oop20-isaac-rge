package ryleh.model.components;

import java.util.Optional;

import ryleh.common.P2d;
import ryleh.controller.events.ItemPickUpEvent;
import ryleh.model.GameObject;
import ryleh.model.Type;
import ryleh.model.World;

public class ItemComponent extends Component {
	
	private P2d position;
	private boolean isOpen;
	public ItemComponent(final World world) {
		super(world);
		this.position = new P2d(0, 0);
		this.isOpen = false;
	}

	@Override
	public void onAdded(final GameObject object) {
		super.onAdded(object);
		this.position = object.getPosition();
	}

	@Override
	public void onUpdate(final double dt) {
		super.onUpdate(dt);
		isCollidingWithPlayer();
	}

	private void isCollidingWithPlayer() {
		if (!this.isOpen) {
			Optional<GameObject> colliding = world.getGameObjects().stream()
					.filter(obj -> obj.getType().equals(Type.PLAYER))
					.filter(obj -> obj.getHitBox().isCollidingWith(object.getHitBox()))
					.findFirst();
			if (colliding.isPresent()) {
				world.notifyWorldEvent(new ItemPickUpEvent(colliding.get(), object));
				this.isOpen = true;
			}
		}
	}
}
