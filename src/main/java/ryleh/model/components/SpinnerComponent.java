package ryleh.model.components;

import ryleh.common.P2d;
import ryleh.common.V2d;
import ryleh.model.GameObject;
import ryleh.model.World;

public class SpinnerComponent extends Component {
	
	   private final ShootingComponent shooting;
	   private P2d position;
	   private final double bulletSpeed;
	   private double angle;

	   public SpinnerComponent(final World world) {
		   super(world);
		   this.shooting = new ShootingComponent(world, 2);
		   this.position = new P2d(0, 0);
		   this.bulletSpeed = 0.15;
		   this.angle = 0;
		}
		@Override
		public void onAdded(final GameObject object) {
			super.onAdded(object);
			this.shooting.onAdded(object);
			this.position = object.getPosition();
		}
		@Override
	    public void onUpdate(final double dt) {
	    	shoot();
	    	angle = angle + Math.PI / 40;
		if (angle >= Math.PI * 2) {
		    angle = 0;
		}
	    }
	    public void shoot() {
			//if (System.currentTimeMillis()-weaponTimer >= 500) {
				V2d direction = new V2d(this.position.x * Math.cos(this.angle) 
				        - position.y * Math.sin(this.angle),
					position.x * Math.sin(this.angle) + position.y * Math.cos(this.angle))
						//.sub(new V2d(this.position.x, this.position.y))
	            		.getNormalized()
	            		.mulLocal(bulletSpeed);
				shooting.shoot(direction);
		    //}
	    }
}
