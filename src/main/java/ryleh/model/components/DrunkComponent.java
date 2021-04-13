package ryleh.model.components;

import java.util.Optional;

import javafx.geometry.Rectangle2D;
import ryleh.common.GameMath;
import ryleh.common.P2d;
import ryleh.common.V2d;
import ryleh.model.GameObject;
import ryleh.model.Type;
import ryleh.model.World;
import ryleh.model.events.EnemyCollisionEvent;

public class DrunkComponent extends Component {
	
	private V2d velocity;
	private P2d position;
    private double angleAdjustRate = GameMath.randomInRange(0, 0.15);

    private double directionAngle = GameMath.toDegrees(GameMath.randomInRange(-1, 1) * Math.PI*2);

    private double moveSpeed = 0.05;
    private int rotationSpeed = GameMath.randomInt(-100, 100);

    private double tx = GameMath.randomInRange(1000, 10000);
    private double tpf = 33.3;

	
	public DrunkComponent(final World world) {
		super(world);
		this.position = new P2d(0, 0);
		this.velocity = new V2d(0, 0);
	}


	@Override
	public void onAdded(final GameObject object) {
		super.onAdded(object);
		this.position = object.getPosition();
	}

	@Override
	 public void onUpdate(final double deltaTime) {
	        adjustAngle();
	        move(deltaTime);

	       // entity.setRotation(entity.getRotation() * 0.9 + directionAngle * 0.1);

	       // tx += tpf;

	       checkScreenBounds();
	    }

	    private void adjustAngle() {
	        if (GameMath.randomBoolean(angleAdjustRate)) {
	            // never rotate further than 20 degrees
	            directionAngle += Math.min(GameMath.toDegrees((GameMath.smoothNoise(tx) - 0.5)), 45);
	        }
	    }
	    private void move(double deltaTime) {
	        V2d directionVector = V2d.fromAngle(directionAngle).mulLocal(moveSpeed);
	        this.velocity.addLocal(directionVector).getNormalized().mulLocal(deltaTime * 0.01); //add time per frame value to mulLocal
	        this.position = this.position.sum(this.velocity);
	        object.setPosition(this.position);
	    }

	    //TODO to rewrite
	    private void checkScreenBounds() {
	    	if (object.getHitBox().isOutOfBounds(world.getBounds()) || this.isCollidingWithRock()){
	            this.velocity.x = -this.velocity.x;
	            this.velocity.y = -this.velocity.y;
	        }
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
