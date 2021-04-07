package ryleh.controller;

import java.util.ArrayList;
import java.util.List;

import ryleh.core.GameState;

public class EventHandler implements EventListener {
	
	private GameState gameState;
	private List<Event> eventQueue;

	public EventHandler(final GameState gameState) {
		this.gameState = gameState;
		this.eventQueue = new ArrayList<>();
	}
	
	public void checkEvents() {
		eventQueue.forEach(e -> {
			if (e instanceof EnemyCollisionEvent) {
				EnemyCollisionEvent enemyEvent = (EnemyCollisionEvent) e;
				enemyEvent.handle();
			} else if (e instanceof ItemPickUpEvent) {
				ItemPickUpEvent pickUpEvent = (ItemPickUpEvent) e;
				pickUpEvent.handle();
			} else if (e instanceof BulletSpawnEvent) {
				BulletSpawnEvent bulletSpawn = (BulletSpawnEvent) e;
				bulletSpawn.handle();
			} else if (e instanceof ObstacleCollisionEvent) {
				ObstacleCollisionEvent obstacleCollision = (ObstacleCollisionEvent) e;
				obstacleCollision.handle();
			}
		});
	}
	
	@Override
	public void notifyEvent(final Event e) {
		this.eventQueue.add(e);
	}
	
}
