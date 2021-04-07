package ryleh.controller;

import ryleh.model.GameObject;

public class BulletSpawnEvent extends AbstractEvent {

	protected BulletSpawnEvent(final GameObject bullet) {
		super(bullet);
	}
	public void handle() {
	}

}
