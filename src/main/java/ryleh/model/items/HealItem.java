package ryleh.model.items;

import ryleh.controller.core.GameState;
import ryleh.model.components.HealthIntComponent;

public class HealItem implements Item {
    /**
     * {@inheritDoc}
     * 
     * Total recover of the player
     */
    @Override
    public void apply(final GameState state) {
        ((HealthIntComponent) state.getPlayer().getGameObject().getComponent(HealthIntComponent.class).get())
            .heal(((HealthIntComponent) state.getPlayer().getGameObject().getComponent(HealthIntComponent.class).get()).getMaxHp());
        System.out.println("heal");
    }

}
