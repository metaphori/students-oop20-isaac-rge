package ryleh.model.components;

import java.util.Optional;

import ryleh.common.P2d;
import ryleh.common.V2d;
import ryleh.controller.Entity;
import ryleh.model.GameObject;
import ryleh.model.Type;
import ryleh.model.World;
import ryleh.model.events.EnemyCollisionEvent;

public class LurkerComponent extends Component {
	
		private long adjustDirectionTimer = System.currentTimeMillis();
	    private long adjustDelay = 500;
	    private int moveSpeed = 50;
	    private P2d position;
	    private V2d velocity;
	    private final GameObject player; 

	    public LurkerComponent(final World world, final Entity entity) {
	    	super(world);
	    	player = entity.getGameObject();
	    	this.position = new P2d(0, 0);
	        this.velocity = new V2d(0, 0);
	    }

	    @Override
	    public void onAdded(final GameObject object) {
	        adjustVelocity();
	        super.onAdded(object);
	        this.position = object.getPosition();
	    }

	    @Override
	    public void onUpdate(final double deltaTime) {
	        move();
	        this.checkScreenBounds();
	    }

	    private void move() {
	    	if (System.currentTimeMillis() - adjustDirectionTimer >= adjustDelay) {
	    		adjustVelocity();
	    		adjustDirectionTimer = System.currentTimeMillis();
	    	}
	    	this.position.x = this.position.x + this.velocity.x;
	    	this.position.y = this.position.y + this.velocity.y;
	    	object.setPosition(this.position);
	    }
	    private void checkScreenBounds() {
	    	if (object.getHitBox().isOutOfBounds(world.getBounds()) || this.isCollidingWithRock()){
	            this.velocity.x = -this.velocity.x;
	            this.velocity.y = -this.velocity.y;
	        }
	    }
	    private void adjustVelocity() {
	        V2d directionToPlayer = new V2d(player.getPosition(), this.position)
	                .getNormalized()
	                .mul(moveSpeed);
	        velocity = velocity.addLocal(directionToPlayer).mul(0.016);
	    }
	    private boolean isCollidingWithRock() {
	    	Optional<GameObject> colliding = world.getGameObjects().stream()
	    			.filter(obj -> obj.getType().equals(Type.ROCK))
	    			.filter(obj -> obj.getHitBox().isCollidingWith(object.getHitBox()))
	    			.findFirst();
	    	if (colliding.isPresent()) {
	    		return true;
	    	}
	    	return false;
	    }
}
