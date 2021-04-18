package ryleh.model.components;

import java.util.Optional;

import ryleh.common.Timer;
import ryleh.common.TimerImpl;
import ryleh.controller.events.NewLevelEvent;
import ryleh.model.GameObject;
import ryleh.model.Type;
import ryleh.model.World;

public class DoorComponent extends AbstractComponent{

	private boolean isCollidable;
	private Timer timer;
	
	public DoorComponent(final World world, final int duration) {
		super(world);
		this.isCollidable = false;
		this.timer = new TimerImpl(duration);
	}

	@Override
	public void onAdded(final GameObject object) {
		//isCollidable = false;
		super.onAdded(object);
		timer.startTimer();
	}

	@Override
	public void onUpdate(final double deltaTime) {
		if (timer.isElapsed() || isCollidable) {
			Optional<GameObject> colliding = super.getWorld().getGameObjects().stream()
				.filter(obj -> obj.getType().equals(Type.PLAYER))
				.filter(obj -> obj.getHitBox().isCollidingWith(super.getObject().getHitBox()))
				.findFirst();
			this.isCollidable = true;
			if (colliding.isPresent()) {
			    super.getWorld().notifyWorldEvent(new NewLevelEvent());	
			}
		}
	}
	
	public boolean getCollidble() {
		return isCollidable;
	}


}
