package ryleh.model.events;

import java.util.Random;

import ryleh.model.GameObject;
import ryleh.model.components.HealthIntComponent;

public class ItemPickUpEvent extends AbstractEvent {
	
	private GameObject item;
	
	public ItemPickUpEvent(final GameObject player, final GameObject item) {
		super(player);
		this.item = item;
	}
	
	public void handle() {
		randomItem();
		System.out.println("Hai raccoolto un oggetto");
	}
	public GameObject getItem() {
		return this.item;
	}

	private void randomItem() {
		Random item = new Random();
		int nextItem = item.nextInt(3);
		switch (nextItem) {
		case 0: healthUp();
			break;
		case 1: healthUp();
			break;
		case 2: healthUp();
			break;
		default:
			break;
		}
	}
	private void healthUp() {
		HealthIntComponent health = (HealthIntComponent) this.getTarget().getComponent(HealthIntComponent.class).get();
		health.heal(1);
	}
}
