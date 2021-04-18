package ryleh.model.components;

import java.util.Optional;
import ryleh.controller.events.EnemyCollisionEvent;
import ryleh.controller.events.ItemPickUpEvent;
import ryleh.model.GameObject;
import ryleh.model.Type;
import ryleh.model.World;

public class CollisionComponent extends AbstractComponent {

    private final Type type;
    private boolean hasAlreadyColided = false;
    // private boolean doorCollidable;

    /**
     * Add a component to check the collision between enemies and player.
     * 
     * @param world
     * @param type  The type of the concerned GameObject
     */
    public CollisionComponent(final World world, final Type type) {
        super(world);
        this.type = type;
        this.hasAlreadyColided = false;
    }

    public void setDoorCollidable() {
        // this.doorCollidable = true;
    }

    /**
     * {@inheritDoc}
     * 
     * A method which checks for collisions with other GameObjects and notifies
     * world instance in case of event.
     * 
     */
    public void onUpdate(final double deltaTime) {
        if (!this.hasAlreadyColided) {
            Optional<GameObject> colliding = super.getWorld().getGameObjects().stream()
                    .filter(obj -> obj.getType().equals(Type.PLAYER))
                    .filter(obj -> obj.getHitBox().isCollidingWith(super.getObject().getHitBox())).findFirst();
            if (colliding.isPresent()) {
                if (type.equals(Type.ITEM)) {
                    super.getWorld().notifyWorldEvent(new ItemPickUpEvent());
                    this.hasAlreadyColided = true;
                } else {
                    super.getWorld().notifyWorldEvent(new EnemyCollisionEvent(colliding.get()));
                }
            }
        }
    }

}
