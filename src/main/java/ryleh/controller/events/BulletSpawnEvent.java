package ryleh.controller.events;

import ryleh.common.P2d;
import ryleh.common.V2d;
import ryleh.model.GameObject;

public class BulletSpawnEvent extends AbstractEvent {

	private P2d position;
	private V2d velocity;
	public BulletSpawnEvent(final GameObject spawner, final P2d position, final V2d velocity) {
		super(spawner);
		this.position = position;
		this.velocity = velocity;
	}
	public void handle() {
	}
	public P2d getPosition() {
		return this.position;
	}
	public V2d getVelocity() {
		return this.velocity;
	}
}
