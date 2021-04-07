package ryleh.controller;

import ryleh.model.GameObject;

public class EnemyCollisionEvent extends AbstractEvent {

	private GameObject enemy;
	
	public EnemyCollisionEvent(final GameObject player, final GameObject enemy) {
		super(player);
		this.enemy = enemy;
	}
	
	public void handle() {
	}

}
