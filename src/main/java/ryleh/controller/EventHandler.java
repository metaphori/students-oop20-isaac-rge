package ryleh.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import ryleh.core.GameState;
import ryleh.core.factories.BasicFactory;
import ryleh.model.GameObject;
import ryleh.model.Type;
import ryleh.model.components.HealthIntComponent;
import ryleh.model.events.BulletSpawnEvent;
import ryleh.model.events.EnemiesDefeatedEvent;
import ryleh.model.events.EnemyCollisionEvent;
import ryleh.model.events.Event;
import ryleh.model.events.EventListener;
import ryleh.model.events.GameOverEvent;
import ryleh.model.events.ItemPickUpEvent;
import ryleh.model.events.NewLevelEvent;
import ryleh.model.events.RemoveEntityEvent;
import ryleh.view.PlayerGraphicComponent;
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
		HealthIntComponent comp = (HealthIntComponent) this.gameState.getPlayer().getGameObject().getComponent(HealthIntComponent.class).get();
		this.checkPlayerState();
		this.eventQueue.forEach(e -> {
			if (e instanceof EnemyCollisionEvent) {
				final EnemyCollisionEvent enemyEvent = (EnemyCollisionEvent) e;
				enemyEvent.handle();
				this.gameState.getView().getLives().setText("Lives: " + comp.getCurrentHp());
			} else if (e instanceof ItemPickUpEvent) {
				final ItemPickUpEvent pickUpEvent = (ItemPickUpEvent) e;
				pickUpEvent.handle();
				this.gameState.getView().getLives().setText("Lives: " + comp.getCurrentHp());
				final ItemGraphicComponent graphic = (ItemGraphicComponent) this.gameState.getEntityByType(Type.ITEM).get().getView();
				graphic.setAnimPlayed();
			} else if (e instanceof GameOverEvent) {
				final GameOverEvent gameOver = (GameOverEvent) e;
				gameOver.handle();
			} else if (e instanceof BulletSpawnEvent) {
				final BulletSpawnEvent spawn = (BulletSpawnEvent) e;
				this.gameState.addEntity(
				        BasicFactory.getInstance().createBullet(this.gameState, 
				                spawn.getPosition(), spawn.getVelocity(), spawn.getTarget().getType()));
			} else if (e instanceof RemoveEntityEvent) {
				RemoveEntityEvent remove = (RemoveEntityEvent) e;
				this.removeEntity(remove.getTarget());

			} else if (e instanceof EnemiesDefeatedEvent) {
			    this.gameState.getLevelHandler().spawnItem();
			    this.gameState.getLevelHandler().spawnDoor();
			} else if (e instanceof NewLevelEvent) {
				this.gameState.generateNewLevel();
				this.gameState.getView().getLevel().setText("Level: " + this.gameState.getLevelHandler().getnRooms());
			} 
		});
		this.eventQueue.clear();
	}
	
	@Override
	public void notifyEvent(final Event e) {
		this.eventQueue.add(e);
	}
	private void removeEntity(final GameObject target) {
		Optional<Entity> removable = this.gameState.getEntities().stream().filter(e -> e.getGameObject().equals(target)).findAny();
		if(removable.isPresent()) {
			this.gameState.removeEntity(removable.get());
		}
	}
	private void checkPlayerState() {
		HealthIntComponent comp = (HealthIntComponent) this.gameState.getPlayer().getGameObject().getComponent(HealthIntComponent.class).get();
		PlayerGraphicComponent playerGraphic = (PlayerGraphicComponent) this.gameState.getEntityByType(Type.PLAYER).get().getView();
		playerGraphic.setInvincible(comp.isImmortal());
	}
	
}
