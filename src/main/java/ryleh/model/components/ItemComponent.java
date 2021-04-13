package ryleh.model.components;

import java.util.Optional;

import ryleh.common.P2d;
import ryleh.model.GameObject;
import ryleh.model.Type;
import ryleh.model.World;
import ryleh.model.events.ItemPickUpEvent;

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
	}

}
