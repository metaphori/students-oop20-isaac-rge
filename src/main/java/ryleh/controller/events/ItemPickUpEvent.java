package ryleh.controller.events;

import java.util.Random;

import ryleh.core.GameState;
import ryleh.model.GameObject;
import ryleh.model.Type;
import ryleh.model.components.HealthIntComponent;
import ryleh.view.other.ItemGraphicComponent;

public class ItemPickUpEvent implements Event {
	private final GameObject target;
	private HealthIntComponent health;
	private GameState gs;
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
		this.gs = state;
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
	 * Heal the target and updates game UI with item effect.
	 */
	private void healthUp() {
		gs.getView().getItemPickUp().setText("Item effect: health up!");
		gs.getView().playFt();
		health.heal(1);
	}
	/**
	 * Increase max health of the target and updates game UI with item effect.
	 */
	private void maxHealthUp() {
		gs.getView().getItemPickUp().setText("Item effect: max health up!");
		gs.getView().playFt();
		health.setMaxHp(health.getMaxHp() + 1);
	}
}
