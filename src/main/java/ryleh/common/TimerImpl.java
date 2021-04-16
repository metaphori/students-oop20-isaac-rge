package ryleh.common;

public class TimerImpl implements Timer {
	
	private double startMills;
	private double elapsedMills;
	
	public TimerImpl(final double waitTime) {
		this.reset();
		this.elapsedMills = waitTime;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
    public boolean isElapsed() {
		if (this.isStarted() && System.currentTimeMillis() - this.startMills >  this.elapsedMills) {
			reset();
			return true;
		}
		return false;
	}
	/**
     * {@inheritDoc}
     */
	@Override
    public void startTimer()  {
		if (!isStarted()) {
			this.startMills = System.currentTimeMillis();
		}
	}
	/**
	 * Checks if the timer is already working.
	 * @return True if it's going on, false otherwise
	 */
	private boolean isStarted() {
		return this.startMills != 0;
	}
	/**
	 * Resets the Timer.
	 */
	private void reset() {
		this.startMills = 0;
	}
}
