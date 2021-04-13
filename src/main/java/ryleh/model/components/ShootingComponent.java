package ryleh.model.components;

import ryleh.common.P2d;
import ryleh.common.Timer;
import ryleh.common.V2d;
import ryleh.model.World;
import ryleh.model.events.BulletSpawnEvent;

public class ShootingComponent extends Component {

    private double attackSpeed;
    private Timer timer;
    /**
     * @param world 
     * @param attackSpeed Number of shoots per second
     */
    public ShootingComponent(final World world, final double attackSpeed) {
        super(world);
        this.attackSpeed = attackSpeed;
        timer = new Timer(1.0 / this.attackSpeed);
        timer.startTimer();
    }
    /**
     * Shoots a bullet at given origin and with the given velocity only if attack speed's timer is elapsed.
     * @param velocity given to the bullet.
     * @param origin of bullet spawning.
     */
    public void shoot(final V2d velocity, final P2d origin) {
        if (timer.isElapsed()) {
            world.notifyWorldEvent(new BulletSpawnEvent(object, origin, velocity));
            this.timer.startTimer();
        }
    }
    /**
     * Shoot a bullet at game object's position with the given velocity only if attack speed's timer is elapsed.
     * @param velocity
     */
    public void shoot(final V2d velocity) {
        if (timer.isElapsed()) {
            world.notifyWorldEvent(new BulletSpawnEvent(object, object.getPosition(), velocity));
            this.timer.startTimer();
        }
    }
    /**
     * Multiplies attack speed by a factor, scaling it. 
     * @param factor multiplies old attack speed.
     */
    public void multiplyAtkSpeed(final double factor) {
        this.attackSpeed *= factor;
        this.timer = new Timer(1.0 / this.attackSpeed);
    }
    /**
     * Increases attack speed linearly, adding an amount to the number of shoots per second.
     * @param amount number of shoots per second added to the current attack speed.
     */
    public void increaseAtkSpeed(final double amount) {
        this.attackSpeed += amount;
        this.timer = new Timer(1.0 / this.attackSpeed);
    }

}
