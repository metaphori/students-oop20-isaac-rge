package ryleh.controller.events;

import ryleh.model.GameObject;

public class EnemiesDefeatedEvent extends AbstractEvent {

    /**
     * {@inheritDoc}.
     * @param target
     */
    protected EnemiesDefeatedEvent(final GameObject target) {
        super(target);
        // TODO Auto-generated constructor stub
    }

    /**
     * {@inheritDoc}
     */
    @Override
    void handle() {
    }

}
