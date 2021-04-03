package ryleh.model.components;

import ryleh.common.GameMath;
import ryleh.common.P2d;
import ryleh.common.V2d;
import ryleh.model.GameObject;
import ryleh.model.World;
import ryleh.view.ViewHandler;

public class SpinnerComponent extends Component{
	   private long weaponTimer = System.currentTimeMillis();
	   private P2d position;
	   private V2d velocity;
	   private ViewHandler view;

	   public SpinnerComponent(World world , ViewHandler view) {
		   super(world);
		   this.position = new P2d(700,200);
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
	    	spin();
	    }
		    
		public void shoot() {
			if (weaponTimer - System.currentTimeMillis() >= 2000) {
				V2d directionToPlayer = new V2d(-1,0);
		            //GameFactory.getInstance().createBullet(world, view, position, directionToPlayer);
		            weaponTimer = System.currentTimeMillis();
		        }
		    }
		private void spin() {
			//spinner.rotateBy(GameMath.toDegrees(Math.PI/(Math.PI)/30));
		}
}
