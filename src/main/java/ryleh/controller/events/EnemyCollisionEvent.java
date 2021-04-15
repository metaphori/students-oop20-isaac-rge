package ryleh.controller.events;

import ryleh.core.GameState;
import ryleh.model.GameObject;
import ryleh.model.components.HealthIntComponent;

public class EnemyCollisionEvent implements Event {
	
	private GameObject target;
	public EnemyCollisionEvent(final GameObject target) {
		this.target = target;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void handle(final GameState state) {
		if (this.target.getComponent(HealthIntComponent.class).isPresent()) {
			HealthIntComponent comp = (HealthIntComponent) this.target.getComponent(HealthIntComponent.class).get();
			comp.damage(1);
			state.getView().getLives().setText("Lives: " + comp.getCurrentHp());
		}
	}
	
	

}
