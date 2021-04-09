package ryleh.controller;

import java.util.ArrayList;
import java.util.List;

import ryleh.core.GameState;
import ryleh.model.GameObject;
import ryleh.model.Type;
import ryleh.model.World;
import ryleh.model.components.HealthIntComponent;

public class EventHandler implements EventListener {
	
	private GameState gameState;
	private List<Event> eventQueue;

	public EventHandler(final GameState gameState) {
		this.gameState = gameState;
		this.eventQueue = new ArrayList<>();
	}
	
	public void checkEvents() {
//		HealthIntComponent comp = (HealthIntComponent) gameState.getEntityByType(Type.PLAYER).get().getGameObject().getComponent(HealthIntComponent.class).get();
//		comp.damage(1);
		this.eventQueue.forEach(e -> {
			if (e instanceof EnemyCollisionEvent) {
				EnemyCollisionEvent enemyEvent = (EnemyCollisionEvent) e;
				enemyEvent.handle();
			} else if (e instanceof ItemPickUpEvent) {
				ItemPickUpEvent pickUpEvent = (ItemPickUpEvent) e;
				pickUpEvent.handle();
				this.removeEntity(pickUpEvent.getItem());
			} else if (e instanceof BulletSpawnEvent) {
				BulletSpawnEvent bulletSpawn = (BulletSpawnEvent) e;
				bulletSpawn.handle();
			} else if (e instanceof GameOverEvent) {
				GameOverEvent gameOver = (GameOverEvent) e;
				gameOver.handle();
			}
		});
		this.eventQueue.clear();
	}
	
	@Override
	public void notifyEvent(final Event e) {
		this.eventQueue.add(e);
	}
	private void removeEntity(final GameObject target) {
		this.gameState.removeEntity(this.gameState.getEntities().stream().filter(e -> e.getGameObject().equals(target)).findAny().get());
	}
	
}
