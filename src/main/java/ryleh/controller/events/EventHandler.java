package ryleh.controller.events;

import java.util.ArrayList;
import java.util.List;

import ryleh.core.GameFactory;
import ryleh.core.GameState;
import ryleh.model.GameObject;
import ryleh.model.Type;
import ryleh.view.other.ItemGraphicComponent;

public class EventHandler implements EventListener {
	
	private GameState gameState;
	private List<Event> eventQueue;

	public EventHandler(final GameState gameState) {
		this.gameState = gameState;
		this.eventQueue = new ArrayList<>();
	}
	
	public void checkEvents() {
		this.eventQueue.forEach(e -> {
			if (e instanceof EnemyCollisionEvent) {
				EnemyCollisionEvent enemyEvent = (EnemyCollisionEvent) e;
				enemyEvent.handle();
			} else if (e instanceof ItemPickUpEvent) {
				ItemPickUpEvent pickUpEvent = (ItemPickUpEvent) e;
				pickUpEvent.handle();
				ItemGraphicComponent graphic = (ItemGraphicComponent) this.gameState.getEntityByType(Type.ITEM).get().getView();
				graphic.setAnimPlayed();
			} else if (e instanceof GameOverEvent) {
				GameOverEvent gameOver = (GameOverEvent) e;
				gameOver.handle();
			} else if (e instanceof BulletSpawnEvent) {
				BulletSpawnEvent spawn = (BulletSpawnEvent) e;
				this.gameState.addEntity(GameFactory.getInstance().createBullet(this.gameState.getWorld(), this.gameState.getScene(),
						spawn.getPosition(), spawn.getVelocity()));
			} else if (e instanceof FireCollisionEvent) {
				FireCollisionEvent fire = (FireCollisionEvent) e;
				fire.handle();
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
