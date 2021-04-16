package ryleh.model.components;

import ryleh.common.Timer;
import ryleh.common.TimerImpl;
import ryleh.controller.events.GameOverEvent;
import ryleh.controller.events.RemoveEntityEvent;
import ryleh.model.Type;
import ryleh.model.World;

public class HealthIntComponent extends Component {
	
	private int currentHp;
    private int maxHp;
    private boolean isImmortal = false;
	private Timer timer;
    private static final double WAIT_TIME = 2000;

	public HealthIntComponent(final World world, final int maxHp) {
        super(world);
        this.maxHp = maxHp;
        this.currentHp = maxHp;
        timer = new TimerImpl(WAIT_TIME);
    }

    @Override
	public void onUpdate(final double deltaTime) {
		super.onUpdate(deltaTime);
		if (timer.isElapsed()) {
			this.isImmortal = false;
		}
		//TODO should treat GameOver not as an event
		if (this.currentHp <= 0) {
			if (object.getType().equals(Type.PLAYER)) {
				 world.notifyWorldEvent(new GameOverEvent());
			}
			else {
				world.notifyWorldEvent(new RemoveEntityEvent(object));
			}
	    }
	}

    /**
     * Decreases currentHp. If it's below zero, it will call a death event.
     */
    public void damage(final int dmg) {
    	if (object.getType().equals(Type.PLAYER) ) {
    		if (!this.isImmortal) {
                this.currentHp -= dmg;
            	this.setImmortality();
    		}
    	} else {
    		this.currentHp -= dmg;
    	}
    }
    /**
     * Increases currentHp only if not exceeds maxHp.
     * @param heal Amount of HP healed
     */
    public void heal(final int heal) {
        this.currentHp = this.currentHp >= this.maxHp ? this.maxHp : this.currentHp + heal;
    }

    public void increaseMaxHp(final int inc) {
        this.maxHp += inc;
    }
    /**
     * Decreases max health. Can't have maximum health below 1.
     * If current HP is bigger than new maxHp, currentHp is automatically set to maximum.
     * @param dec Amount of HP decreased
     */
    public void decreaseMaxHp(final int dec) {
        this.maxHp = this.maxHp == 1 ? 1 :  this.maxHp - dec;
        this.currentHp = this.currentHp > this.maxHp ? this.maxHp : this.currentHp;
    }
    public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}
	
	public int getCurrentHp() {
		return currentHp;
	}
	
	public void setCurrentHp(int currentHp) {
		this.currentHp = currentHp;
	}
	public boolean isImmortal() {
		return isImmortal;
	}
	public void setImmortality() {
		timer.startTimer();
    	this.isImmortal = true;
	}

}
