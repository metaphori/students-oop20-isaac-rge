package ryleh.controller.events;

import java.util.Optional;

import ryleh.controller.Entity;
import ryleh.core.GameState;
import ryleh.model.GameObject;
import ryleh.model.Type;

public class RemoveEntityEvent implements Event {

    private GameObject target;
    public RemoveEntityEvent(final GameObject target) {
        this.target = target;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(final GameState state) {
        this.removeEntity(target, state);
        if (isEnemy(this.target.getType())) {
            state.getLevelHandler().decreaseEnemies();
        }
    }
    private void removeEntity(final GameObject target, final GameState state) {
        final Optional<Entity> removable = state.getEntities().stream().filter(e -> e.getGameObject().equals(target)).findAny();
        if (removable.isPresent()) {
            state.removeEntity(removable.get());
        }
    }

    private boolean isEnemy(final Type type) {
        return type.equals(Type.ENEMY_DRUNK) || type.equals(Type.ENEMY_DRUNKSPINNER) || type.equals(Type.ENEMY_LURKER)
                || type.equals(Type.ENEMY_SHOOTER) || type.equals(Type.ENEMY_SPINNER);
    }
}
