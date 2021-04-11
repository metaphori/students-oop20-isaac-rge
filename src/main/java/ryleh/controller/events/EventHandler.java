package ryleh.controller.events;

import java.util.ArrayList;
import java.util.List;
import ryleh.core.GameState;
import ryleh.core.factories.BasicFactory;
import ryleh.model.Type;
import ryleh.view.other.ItemGraphicComponent;

public class EventHandler implements EventListener {
	
	private final GameState gameState;
	private final List<Event> eventQueue;

	public EventHandler(final GameState gameState) {
		this.gameState = gameState;
		this.eventQueue = new ArrayList<>();
	}
	/**
	 * This method is called once every game loop. It checks all events inside the Event Queue and handles their behavior.
	 */
	public void checkEvents() {
		this.eventQueue.forEach(e -> {
			if (e instanceof EnemyCollisionEvent) {
				final EnemyCollisionEvent enemyEvent = (EnemyCollisionEvent) e;
				enemyEvent.handle();
			} else if (e instanceof ItemPickUpEvent) {
				final ItemPickUpEvent pickUpEvent = (ItemPickUpEvent) e;
				pickUpEvent.handle();
				final ItemGraphicComponent graphic = (
				        ItemGraphicComponent) this.gameState.getEntityByType(Type.ITEM).get().getView();
				graphic.setAnimPlayed();
			} else if (e instanceof GameOverEvent) {
				final GameOverEvent gameOver = (GameOverEvent) e;
				gameOver.handle();
			} else if (e instanceof BulletSpawnEvent) {
				final BulletSpawnEvent spawn = (BulletSpawnEvent) e;
				this.gameState.addEntity(
				        BasicFactory.getInstance().createBullet(this.gameState, 
				                spawn.getPosition(), spawn.getVelocity()));
			} else if (e instanceof FireCollisionEvent) {
				final FireCollisionEvent fire = (FireCollisionEvent) e;
				fire.handle();
			} else if (e instanceof NewLevelEvent) {
				this.gameState.generateNewLevel();
			} else if (e instanceof EnemiesDefeatedEvent) {
			    this.gameState.getLevelHandler().spawnItem();
			    this.gameState.getLevelHandler().spawnDoor();
			}
		});
		this.eventQueue.clear();
	}
	
	@Override
	public void notifyEvent(final Event e) {
		this.eventQueue.add(e);
	}
	
}
