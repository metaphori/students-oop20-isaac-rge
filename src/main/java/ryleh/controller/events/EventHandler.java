package ryleh.controller.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import ryleh.controller.Entity;
import ryleh.core.GameState;
import ryleh.core.factories.BasicFactory;
import ryleh.model.GameObject;
import ryleh.model.Type;
import ryleh.model.components.HealthIntComponent;
import ryleh.view.PlayerGraphicComponent;
import ryleh.view.other.ItemGraphicComponent;

public class EventHandler implements EventListener {

    private final GameState gameState;
    private final List<Event> eventQueue;
    private HealthIntComponent comp;

    public EventHandler(final GameState gameState) {
        this.gameState = gameState;
        this.eventQueue = new ArrayList<>();
    }

    /**
     * This method is called once every game loop. It checks all events inside the
     * Event Queue and handles their behavior.
     */
    public void checkEvents() {
        this.eventQueue.forEach(e -> {
            if (e instanceof EnemyCollisionEvent) {
                final EnemyCollisionEvent enemyEvent = (EnemyCollisionEvent) e;
                enemyEvent.handle(this.gameState);
            } else if (e instanceof ItemPickUpEvent) {
                final ItemPickUpEvent pickUpEvent = (ItemPickUpEvent) e;
                pickUpEvent.handle(this.gameState);
            } else if (e instanceof GameOverEvent) {
                final GameOverEvent over = (GameOverEvent) e;
                over.handle(this.gameState);
            } else if (e instanceof BulletSpawnEvent) {
                final BulletSpawnEvent spawn = (BulletSpawnEvent) e;
                spawn.handle(this.gameState);
            } else if (e instanceof RemoveEntityEvent) {
                final RemoveEntityEvent remove = (RemoveEntityEvent) e;
                remove.handle(this.gameState);
            } else if (e instanceof EnemiesDefeatedEvent) {
                final EnemiesDefeatedEvent defeat = (EnemiesDefeatedEvent) e;
                defeat.handle(this.gameState);
            } else if (e instanceof NewLevelEvent) {
                final NewLevelEvent lvl = (NewLevelEvent) e;
                lvl.handle(this.gameState);
            }
        });
        this.eventQueue.clear();
        this.updateUI();
        this.checkPlayerState();
    }
    /**
     * Check the player state so that the graphic component can apply effects in special cases.
     */
    private void checkPlayerState() {
        final PlayerGraphicComponent playerGraphic = (PlayerGraphicComponent) this.gameState.getPlayer().getView();
        playerGraphic.setInvincible(comp.isImmortal());
    }
    /**
     * Update game UI after event handling.
     */
    private void updateUI() {
        comp = (HealthIntComponent) this.gameState.getPlayer().getGameObject()
                .getComponent(HealthIntComponent.class).get();
        gameState.getView().getLives().setText("Lives: " + comp.getCurrentHp());
        this.gameState.getView().getLevel().setText("Level: " + this.gameState.getLevelHandler().getnRooms());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyEvent(final Event e) {
        this.eventQueue.add(e);
    }
}
