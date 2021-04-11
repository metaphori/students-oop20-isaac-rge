package ryleh.model.components;

import java.util.Optional;

import javafx.geometry.Rectangle2D;
import ryleh.common.GameMath;
import ryleh.common.P2d;
import ryleh.common.V2d;
import ryleh.controller.events.EnemyCollisionEvent;
import ryleh.model.GameObject;
import ryleh.model.Type;
import ryleh.model.World;

public class DrunkComponent extends Component {
	
	private V2d velocity;
	private P2d position;
    private double angleAdjustRate = GameMath.randomInRange(0, 0.15);

    private double directionAngle = GameMath.toDegrees(GameMath.randomInRange(-1, 1) * Math.PI*2);

    private double moveSpeed = 0.15;
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
	        //move(deltaTime);

	       // entity.setRotation(entity.getRotation() * 0.9 + directionAngle * 0.1);

	       // tx += tpf;

	       checkScreenBounds();
//	       this.isCollidingWithPlayer();
	    }

	    private void adjustAngle() {
	        if (GameMath.randomBoolean(angleAdjustRate)) {
	            // never rotate further than 20 degrees
	            directionAngle += Math.min(GameMath.toDegrees((GameMath.smoothNoise(tx) - 0.5)), 45);
	        }
	    }
	    private void move(double deltaTime) {
	        V2d directionVector = V2d.fromAngle(directionAngle).mulLocal(moveSpeed);
	        this.velocity.addLocal(directionVector).mulLocal(deltaTime * 0.001); //add time per frame value to mulLocal
	        this.position = this.position.sum(this.velocity);
	        object.setPosition(this.position);
	        //System.out.println("VELOCITY : \t " + this.velocity.toString());
	        //System.out.println("Position of " + super.object + " is " + this.position);
	    }

	    //TODO to rewrite
	    private void checkScreenBounds() {
	    	if (object.getHitBox().isOutOfBounds(world.getBounds())){
	    		//System.out.println("Position of " + super.object + " is " + this.position);
	            //P2d newDirectionVector = new P2d(world.getWidthBound()/2,world.getHeightBound()/2);//.subPoint(object.getPosition());
	            //System.out.println("Position of " + super.object + " is " + newDirectionVector);
	            //double angle = GameMath.toDegrees((Math.atan(newDirectionVector.y/ newDirectionVector.x)));
	            //directionAngle = newDirectionVector.x> 0 ? angle : 180 - angle;
	            this.velocity.x = -this.velocity.x;
	            this.velocity.y = -this.velocity.y;
	        }
	    }
//	    private void isCollidingWithPlayer() {
//			Optional<GameObject> colliding = world.getGameObjects().stream()
//					.filter(obj -> obj.getType().equals(Type.PLAYER))
//					.filter(obj -> obj.getHitBox().isCollidingWith(object.getHitBox()))
//					.findFirst();
//			if (colliding.isPresent()) {
//				world.notifyWorldEvent(new EnemyCollisionEvent(colliding.get(), object));
//			}
//		}
}
