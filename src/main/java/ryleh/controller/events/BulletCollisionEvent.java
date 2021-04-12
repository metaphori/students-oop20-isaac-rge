package ryleh.controller.events;

import java.util.Optional;

import ryleh.model.GameObject;
import ryleh.model.components.HealthIntComponent;

public class BulletCollisionEvent extends AbstractEvent {

	public BulletCollisionEvent(final GameObject target, final GameObject bullet) {
		super(target);
		// TODO Auto-generated constructor stub
	}

	@Override
	void handle() {
		if (this.getTarget().getComponent(HealthIntComponent.class).isPresent()) {
			HealthIntComponent comp = (HealthIntComponent) this.getTarget().getComponent(HealthIntComponent.class).get();
			comp.damage(1);
		}
	}

}
