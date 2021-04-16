package ryleh.model.events;

import ryleh.core.GameEngine;
import ryleh.model.GameObject;
import ryleh.model.components.HealthIntComponent;

public class EnemyCollisionEvent  extends AbstractEvent {
	
	public EnemyCollisionEvent(final GameObject player, final GameObject enemy) {
		super(player);
	}
	@Override
	public void handle() {
		if (this.getTarget().getComponent(HealthIntComponent.class).isPresent() 
		        && !GameEngine.isDeveloper()) {
			((HealthIntComponent) this.getTarget()
			        .getComponent(HealthIntComponent.class).get()).damage(1);
		}
	}

}
