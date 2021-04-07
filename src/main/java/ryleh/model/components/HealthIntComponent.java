package ryleh.model.components;

import ryleh.model.World;

public class HealthIntComponent extends Component {

    private int maxHp;
    private int currentHp;
    public HealthIntComponent(final World world, final int maxHp) {
        super(world);
        this.maxHp = maxHp;
        this.currentHp = maxHp;
    }

    /*
     * Decreases currentHp. If it's below zero, it will call a death event
     */
    public void damage(final int dmg) {
        this.currentHp -= dmg;
        if (this.currentHp <= 0) {
            //TODO call death event
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

}
