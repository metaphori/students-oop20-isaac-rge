package ryleh.controller;

import ryleh.model.GameObject;

public class ObstacleCollisionEvent extends AbstractEvent {

	protected ObstacleCollisionEvent(final GameObject player, final GameObject obstacle) {
		super(player);
		// TODO Auto-generated constructor stub
	}
	public void handle() {
		//sse obstacle è fire allora perdo vita, altrimenti mi blocco solamente)
	}

}
