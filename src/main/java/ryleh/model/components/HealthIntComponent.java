package ryleh.model.components;

import ryleh.controller.GameOverEvent;
import ryleh.model.World;

public class HealthIntComponent extends Component {
	
	private int currentHp;
    private int maxHp;
    private double currentTime = 0;
    private double startTime = 0;
    private boolean isInvincible = false;

	public HealthIntComponent(final World world, final int maxHp) {
        super(world);
        this.maxHp = maxHp;
        this.currentHp = maxHp;
    }

    @Override
	public void onUpdate(final double deltaTime) {
		// TODO Auto-generated method stub
		super.onUpdate(deltaTime);
		 if (this.currentHp <= 0) {
	           world.notifyWorldEvent(new GameOverEvent(object));
	        }
	}

    /*
     * Decreases currentHp. If it's below zero, it will call a death event
     */
    public void damage(final int dmg) {
    	this.updateInvicible();
    	if(!this.isInvincible) {
            this.currentHp -= dmg;
    	} 
    }
	/*
     * Increases currentHp only if not exceeds maxHp
     */
    public void heal(final int heal) {
        this.currentHp = this.currentHp >= this.maxHp ? this.maxHp : this.currentHp + heal;
    }

    public void increaseMaxHp(final int inc) {
        this.maxHp += inc;
    }

    /*
     * Decreases max health. Can't have maximum health below 1
     * If current hp is bigger than new maxHp, currentHp is automatically set to maximum
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
	private void updateInvicible() {
		if (this.startTime == 0) {
			this.startTime = System.currentTimeMillis();
			setInvincible(true);
		} else {
			this.currentTime = System.currentTimeMillis();
			if (this.currentTime - this.startTime < 2000) {
				System.out.println("Sono Invincibile");
				setInvincible(true);
			} else {
				setInvincible(false);
				this.startTime = 0;
				this.currentTime = 0;
			}
		}
	}

	private void setInvincible(final boolean bool) {
		this.isInvincible = bool;
	}


}
