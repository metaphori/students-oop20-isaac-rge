package ryleh.controller;

import ryleh.model.GameObject;

public class ItemPickUpEvent extends AbstractEvent {
	
	private GameObject item;
	
	public ItemPickUpEvent(final GameObject player, final GameObject item) {
		super(player);
		this.item = item;
	}
	
	public void handle() {

	}

}
