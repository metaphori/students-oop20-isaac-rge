package ryleh.model.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import javafx.application.Platform;
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
		private List<Entity> objects;
		private ViewHandler view;
		private double bulletSpeed = 0.15;
		Entity player;
	    
	    public ShooterComponent(World world , ViewHandler view, Entity player) {
			super(world);
			this.position = new P2d(500,500);
			this.velocity = new V2d(0,0);
			this.view = view;
			this.player = player;
		}
		@Override
		public void onAdded(GameObject object) {
			super.onAdded(object);
			this.position = object.getPosition();
		}
	    public void onUpdate(double dt) {
	    	shoot();
	    }
	    
	    public void shoot() {
	        if (System.currentTimeMillis() - weaponTimer >= 2000) {
	            V2d directionToPlayer = new V2d(player.getGameObject().getPosition().x,player.getGameObject().getPosition().y)
	            		.sub(new V2d(this.position.x,this.position.y))
	            		.getNormalized()
	            		.mulLocal(bulletSpeed);
	            System.out.println("Helo");
	            //objects.add(GameFactory.getInstance().createBullet(world, view, position, directionToPlayer));
	            weaponTimer = System.currentTimeMillis();
	        }
	    }
	    public V2d getDirection() {
	    	V2d directionToPlayer = new V2d(player.getGameObject().getPosition().x,player.getGameObject().getPosition().y)
            		.sub(new V2d(this.position.x,this.position.y))
            		.getNormalized()
            		.mulLocal(bulletSpeed);
	    	return directionToPlayer;
	    }
}
