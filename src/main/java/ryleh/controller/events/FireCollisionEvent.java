package ryleh.controller.events;

import ryleh.model.GameObject;
import ryleh.model.components.HealthIntComponent;

public class FireCollisionEvent extends AbstractEvent {
	private GameObject player;

	private GameObject fire;
	public FireCollisionEvent(final GameObject player, final GameObject fire) {
		super(player);
		this.player = player;
		this.fire = fire;
	}

	void handle() {
		HealthIntComponent comp = (HealthIntComponent) this.player.getComponent(HealthIntComponent.class).get();
		comp.damage(1);
	}

}
