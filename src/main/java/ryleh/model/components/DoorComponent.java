package ryleh.model.components;

import ryleh.model.GameObject;
import ryleh.model.World;

public class DoorComponent extends Component{

	private boolean isCollidable;
	
	public DoorComponent(World world) {
		super(world);
		this.isCollidable = false;
		
	}

	@Override
	public void onAdded(GameObject object) {
		isCollidable = false;
		super.onAdded(object);
	}

	@Override
	public void onUpdate(double deltaTime) {
		// TODO Auto-generated method stub
		super.onUpdate(deltaTime);
	}
	
	public boolean getCollidble() {
		return isCollidable;
	}


}
