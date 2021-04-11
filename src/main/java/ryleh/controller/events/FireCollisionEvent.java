package ryleh.controller.events;

import ryleh.model.GameObject;
import ryleh.model.components.HealthIntComponent;

public class FireCollisionEvent extends AbstractEvent {

	private GameObject fire;
	public FireCollisionEvent(final GameObject player, final GameObject fire) {
		super(player);
		this.fire = fire;
	}

	void handle() {
		HealthIntComponent comp = (HealthIntComponent) this.getPlayer().getComponent(HealthIntComponent.class).get();
		comp.damage(1);
	}

}
