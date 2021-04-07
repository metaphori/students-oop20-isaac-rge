package ryleh.model.physics;

import ryleh.common.V2d;

public enum Direction {
	UP(new V2d(0, -1)),
	DOWN(new V2d(0, 1)),
	LEFT(new V2d(-1, 0)),
	RIGHT(new V2d(1, 0)),
	IDLE(new V2d(0, 0));

	private V2d point;
	Direction(final V2d point) {
		this.setPoint(point);
	}
	public V2d getPoint() {
		return point;
	}
	public void setPoint(final V2d point) {
		this.point = point;
	}
}
