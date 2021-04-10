package ryleh.controller.events;

import ryleh.model.GameObject;
import ryleh.model.components.HealthIntComponent;

public class EnemyCollisionEvent extends AbstractEvent {
	
	private GameObject enemy;
	
	public EnemyCollisionEvent(final GameObject player, final GameObject enemy) {
		super(player);
		this.enemy = enemy;
	}
	@Override
	public void handle() {
		HealthIntComponent comp = (HealthIntComponent) this.getPlayer().getComponent(HealthIntComponent.class).get();
		comp.damage(1);
	}

}
