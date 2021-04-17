package ryleh.controller.events;

import java.util.Random;

import ryleh.core.GameState;
import ryleh.model.GameObject;
import ryleh.model.Type;
import ryleh.model.components.HealthIntComponent;
import ryleh.model.components.ShootingComponent;
import ryleh.model.items.FireSpeedItem;
import ryleh.model.items.HealItem;
import ryleh.model.items.Item;
import ryleh.model.items.MaxHealthItem;
import ryleh.view.other.ItemGraphicComponent;

public class ItemPickUpEvent implements Event {
	private final GameObject target;
	private HealthIntComponent health;
	private Item item;
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
		randomItem(state);
		((ItemGraphicComponent) state.getEntityByType(Type.ITEM).get().getView()).setAnimPlayed();
	}
	/**
	 * Method to generate a random buff with pseudo probability.
	 */
	private void randomItem(final GameState state) {
		Random random = new Random();
		switch (random.nextInt(3)) {
		case 0: item = new HealItem();
		    state.getView().getItemPickUp().setText("Item effect: health up!");
		    state.getView().playFt();
			break;
		case 1: item = new MaxHealthItem();
		    state.getView().getItemPickUp().setText("Item effect: max health up!");
		    state.getView().playFt();
			break;
		case 2: item = new FireSpeedItem();
			break;
		default:
			break;
		}
		item.apply(state);
	}
	/**
	 * Heal the target and updates game UI with item effect.
	 */
//	private void healthUp() {
//		gs.getView().getItemPickUp().setText("Item effect: health up!");
//		gs.getView().playFt();
//		health.heal(1);
//	}
//	/**
//	 * Increase max health of the target and updates game UI with item effect.
//	 */
//	private void maxHealthUp() {
//		gs.getView().getItemPickUp().setText("Item effect: max health up!");
//		gs.getView().playFt();
//		health.setMaxHp(health.getMaxHp() + 1);
//	}
}
