package ryleh.model.physics;

import ryleh.common.Vector2d;

public enum Direction {
	UP(new Vector2d(0, -1)),
	DOWN(new Vector2d(0, 1)),
	LEFT(new Vector2d(-1, 0)),
	RIGHT(new Vector2d(1, 0)),
	IDLE(new Vector2d(0, 0));

	private Vector2d point;
	Direction(final Vector2d point) {
		this.setPoint(point);
	}
	public Vector2d getPoint() {
		return point;
	}
	public void setPoint(final Vector2d point) {
		this.point = point;
	}
}
