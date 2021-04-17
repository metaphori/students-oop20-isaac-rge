package ryleh.model.items;

import ryleh.controller.core.GameState;
import ryleh.model.components.HealthIntComponent;

public class HealItem implements Item {
    @Override
    public void apply(final GameState state) {
        ((HealthIntComponent) state.getPlayer().getGameObject().getComponent(HealthIntComponent.class).get()).heal(1);
        System.out.println("heal");
    }

}
