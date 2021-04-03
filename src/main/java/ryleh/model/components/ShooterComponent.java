package ryleh.model.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import ryleh.common.P2d;
import ryleh.common.V2d;
import ryleh.controller.Entity;
import ryleh.core.GameFactory;
import ryleh.model.GameObject;
import ryleh.model.World;
import ryleh.view.ViewHandler;


public class ShooterComponent extends Component{

	    private long weaponTimer = System.currentTimeMillis();
		private P2d position;
		private V2d velocity;
		private ViewHandler view;
	    
	    public ShooterComponent(World world , ViewHandler view) {
			super(world);
			this.position = new P2d(500,500);
			this.velocity = new V2d(0,0);
			this.view=view;
		}
		@Override
		public void onAdded(GameObject object) {
			super.onAdded(object);
			this.position = object.getPosition();
		}
	    public void onUpdate() {
	    	shoot();
	    }
	    
	    public void shoot() {
	        if (weaponTimer - System.currentTimeMillis() >= 2000) {
	            V2d directionToPlayer = new V2d(-1,0);/*control.getPlayer().getCenter()
	                    .subtract(shooter.getCenter())
	                    .normalize()
	                    .multiply(Config.ENEMY_BULLET_MOVE_SPEED);
	                    */
	            //GameFactory.getInstance().createBullet(world, view, position, directionToPlayer);
	            weaponTimer = System.currentTimeMillis();
	        }
	    }
}
