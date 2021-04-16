package ryleh.controller.events;

import ryleh.core.GameState;
import ryleh.model.GameObject;
import ryleh.model.components.HealthIntComponent;

public class EnemyCollisionEvent implements Event {
	
	private GameObject target;
	/**
	 * Constructor for a collision event.
	 * @param target The target of the event,
	 */
	public EnemyCollisionEvent(final GameObject target) {
		this.target = target;
	}
	/**
	 * {@inheritDoc}
	 * Decreases actual target health
	 */
	@Override
	public void handle(final GameState state) {
		if (this.target.getComponent(HealthIntComponent.class).isPresent()) {
			((HealthIntComponent) this.target.getComponent(HealthIntComponent.class).get()).damage(1);
		}
	}
	
	

}
