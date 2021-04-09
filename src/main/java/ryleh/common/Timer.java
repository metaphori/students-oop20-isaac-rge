package ryleh.common;

public class Timer {
	
	private double startTime;
	private double waitTime;
	
	public Timer(final double waitTime) {
		this.reset();
		this.waitTime = waitTime;
	}
	public boolean isElapsed() {
		if (this.isStarted() && System.currentTimeMillis() - this.startTime >  this.waitTime) {
			reset();
			return true;
		}
		return false;
	}
	public void startTimer()  {
		if (!isStarted()) {
			this.startTime = System.currentTimeMillis();
		}
	}
	private boolean isStarted() {
		return this.startTime != 0;
	}
	private void reset() {
		this.startTime = 0;
	}
}
