package ryleh.model.physics;

import ryleh.common.P2d;

public enum Direction {
	UP(new P2d(0, -1)),
	DOWN(new P2d(0, 1)),
	LEFT(new P2d(-1, 0)),
	RIGHT(new P2d(1, 0)),
	IDLE(new P2d(0, 0));

	private P2d point;
	Direction(final P2d point) {
		this.setPoint(point);
	}
	public P2d getPoint() {
		return point;
	}
	public void setPoint(final P2d point) {
		this.point = point;
	}
}
