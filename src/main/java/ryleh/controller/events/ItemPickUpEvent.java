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
	
	/**
	 * Constructor for the collision with an Item.
	 * @param target Player, only this game object can collide with items
	 */
	public ItemPickUpEvent(final GameObject target) {
		this.target = target;
	}

	/**
	 * {@inheritDoc}
	 * Starts the opening animation of the item
	 */
	@Override
	public void handle(final GameState state) {
		randomItem();
		((ItemGraphicComponent) state.getEntityByType(Type.ITEM).get().getView()).setAnimPlayed();
	}
	/**
	 * Method to generate a random buff with pseudo probability.
	 */
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
	/**
	 * Heal the target.
	 */
	private void healthUp() {
		health.heal(1);
	}
	/**
	 * Increase max health of the target.
	 */
	private void maxHealthUp() {
		health.setMaxHp(health.getMaxHp() + 1);
	}
}
