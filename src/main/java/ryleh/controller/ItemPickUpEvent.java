package ryleh.controller;

import java.util.Random;

import ryleh.core.GameState;
import ryleh.model.GameObject;
import ryleh.model.components.ItemComponent;
import ryleh.model.components.PhysicsComponent;

public class ItemPickUpEvent extends AbstractEvent {
	
	private GameObject item;
	
	public ItemPickUpEvent(final GameObject player, final GameObject item) {
		super(player);
		this.item = item;
	}
	
	public void handle() {
		ItemComponent comp = (ItemComponent) item.getComponent(ItemComponent.class).get();
		//comp.randomItem();
		randomItem();
		System.out.println("Hai raccolto un oggeto");
	}

	private void randomItem() {
		Random item = new Random();
		int nextItem = item.nextInt(3);
		switch (nextItem) {
		case 0: //healthUp();
			System.out.println("una vita in più");
			break;
		case 1: //speedUp();
			System.out.println("SpeedUp");
			break;
		case 2:  //shootSpeedUp();
			System.out.println("Spara spara");
			break;
		default:
			break;
		}
	}
	private void speedUp() {
		PhysicsComponent physics = (PhysicsComponent) this.getPlayer().getComponent(PhysicsComponent.class).get();
		//physics.setSpeedUp();
	}
}
