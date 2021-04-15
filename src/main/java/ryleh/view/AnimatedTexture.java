package ryleh.view;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


public class AnimatedTexture {

	//private double period;
	private Timeline loop;
	private KeyFrame[] frames;
	private Rectangle rectangle;
	
	//public AnimatedTexture(final KeyFrame[] frames) {};
	public AnimatedTexture(final List<ImagePattern> images, final Duration duration, final Rectangle rectangle) {
		this.frames = AnimatedTexture.getKeyFrames(images, duration, rectangle);
		/*this.frames = new KeyFrame[frames.length];
		for (int i = 0; i < frames.length; i++) {
			this.frames[i] = frames[i];
			System.out.println("Ecco la KeyFrames[] " + this.frames[i]);
		}*/
		this.rectangle = rectangle;
		this.loop = new Timeline(this.frames);
		loop.setCycleCount(Animation.INDEFINITE);
		
		loop.setOnFinished(e -> {
			System.out.print("helo ho finito il loop");
		});
	}
	

	public void playAnimation() {
	    loop.play();
	}
	
	public void stopAnimation() {
		loop.stop();
	}
	
	
	public static KeyFrame[] getKeyFrames(final List<ImagePattern> images, final Duration duration, final Rectangle rectangle) {
		KeyFrame[] frames = new KeyFrame[images.size()];
		for (int i = 0; i < images.size(); i++) {
			final int index = i;
			frames[i] = new KeyFrame(duration, new EventHandler<>() {
				private ImagePattern image = images.get(index);
				@Override
				public void handle(final ActionEvent event) {
					rectangle.setFill(image);
					//rectangle.setOpacity(1 - index);
					System.out.print("i: " + index + " ");
				}
			});
		}
		return frames;
	}
	
}
