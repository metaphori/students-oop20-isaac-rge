package ryleh.controller.events;

import java.util.Random;

import ryleh.core.GameState;
import ryleh.model.GameObject;
import ryleh.model.Type;
import ryleh.model.components.HealthIntComponent;
import ryleh.view.other.ItemGraphicComponent;

public class ItemPickUpEvent implements Event {
	
	private GameObject target;
	private HealthIntComponent health;
	
	public ItemPickUpEvent(final GameObject target) {
		this.target = target;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void handle(final GameState state) {
		randomItem();
		state.getView().getLives().setText("Lives: " +  ((HealthIntComponent) this.target.getComponent(HealthIntComponent.class).get()).getCurrentHp());
		final ItemGraphicComponent graphic = (ItemGraphicComponent) state
				.getEntityByType(Type.ITEM).get().getView();
        graphic.setAnimPlayed();
	}
	private void randomItem() {
		Random item = new Random();
		health = (HealthIntComponent) this.target.getComponent(HealthIntComponent.class).get();
		switch (item.nextInt(3)) {
		case 0: this.healthUp();
			break;
		case 1: this.maxHealthUp();
			break;
		case 2: healthUp();
			break;
		default:
			break;
		}
	}
	private void healthUp() {
		health.heal(1);
	}
	private void maxHealthUp() {
		health.setMaxHp(health.getMaxHp() + 1);
	}
}
