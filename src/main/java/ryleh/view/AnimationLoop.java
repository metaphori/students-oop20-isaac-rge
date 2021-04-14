package ryleh.view;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 * A class that provides the implementation of an Animation, given the list of frames and the Rectangle to be filled.
 */
public class AnimationLoop {

	private int timer;
	private int frameDuration;
	private List<ImagePattern> frames;
	private boolean cycleFinished;
	
	/**
	 * Creates a new Instance of AnimationLoop with the given list of Frames and duration of each frame.
	 * @param frames An ordered list of the frames that form the animation.
	 * @param duration The number of loops needed to wait between each frame of the animation.
	 */
	public AnimationLoop(final List<ImagePattern> frames, final int duration) {
		this.frames = new ArrayList<>(frames);
		this.timer = 0;
		this.frameDuration = duration;
		this.cycleFinished = false;
	}
	
	/**
	 * A method to increment the loop Counter of the animation.
	 */
	public void incTimer() {
		this.timer++;
	}
	
	/**
	 * A method to reset the loop counter of the animation.
	 */
	public void resetTimer() {
		this.timer = 0;
	}

	/**
	 * A method to set the rectangle fillProperty with the correct frame based on the current loop counter.
	 * @param rectangle The rectangle that needs to update its fillProperty with che current frame of the animation.
	 * @return The given rectangle with the correct fillProperty and the correct frame of the animation.
	 */
	public Rectangle setFrame(final Rectangle rectangle) {
		for (int i = 1; i <= this.frames.size() + 1; i++) {
			if (timer == (frameDuration * frames.size()) + 1) {
				this.resetTimer();
				this.cycleFinished = true;
			} else {
				if (timer == frameDuration * i) {
					rectangle.setFill(this.frames.get(i - 1));
					return rectangle;
				}
			}
		}
		return rectangle;
	}
	
	/**
	 * A method to check if the complete animation has finished.
	 * @return true if the current animation is fineshed,
	 */
	public boolean isCycleFinished() {
		return this.cycleFinished;
	}
}
