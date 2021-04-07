package ryleh.controller;

import ryleh.model.GameObject;

public class FireCollisionEvent extends AbstractEvent {

	private GameObject fire;
	protected FireCollisionEvent(final GameObject player, final GameObject fire) {
		super(player);
		this.fire = fire;
	}

	void handle() {
		// TODO Auto-generated method stub
		
	}

}
