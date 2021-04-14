package ryleh.common;

public class Timer {
	
	private double startMills;
	private double elapsedMills;
	
	public Timer(final double waitTime) {
		this.reset();
		this.elapsedMills = waitTime;
	}
	public boolean isElapsed() {
		if (this.isStarted() && System.currentTimeMillis() - this.startMills >  this.elapsedMills) {
			reset();
			return true;
		}
		return false;
	}
	public void startTimer()  {
		if (!isStarted()) {
			this.startMills = System.currentTimeMillis();
		}
	}
	private boolean isStarted() {
		return this.startMills != 0;
	}
	private void reset() {
		this.startMills = 0;
	}
}
