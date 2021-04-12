package ryleh.controller.events;

import ryleh.model.GameObject;
import ryleh.model.components.HealthIntComponent;

public class EnemyCollisionEvent  extends AbstractEvent {
	
	public EnemyCollisionEvent(final GameObject player, final GameObject enemy) {
		super(player);
	}
	@Override
	public void handle() {
		HealthIntComponent comp = (HealthIntComponent) this.getTarget().getComponent(HealthIntComponent.class).get();
		comp.damage(1);
	}

}
