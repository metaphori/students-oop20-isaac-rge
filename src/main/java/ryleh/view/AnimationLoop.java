package ryleh.view;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class AnimationLoop {

	int timer;
	private int frameDuration;
	private List<ImagePattern> frames;
	
	public AnimationLoop(final List<ImagePattern> frames, final int duration) {
		this.frames = new ArrayList<>(frames);
		this.timer = 0;
		this.frameDuration = duration;
	}
	
	public void incTimer() {
		this.timer++;
	}
	public void resetTimer() {
		this.timer = 0;
	}

	public Rectangle setFrame(final Rectangle rectangle) {
		for (int i = 1; i <= this.frames.size() + 1; i++) {
			if (timer == (frameDuration * frames.size()) + 1) {
				this.resetTimer();
			} else {
				if (timer == frameDuration * i) {
					rectangle.setFill(this.frames.get(i - 1));
					return rectangle;
				}
			}
		}
		return rectangle;
	}
	
	//-----------//
	//OLD CLASS//
	//-----------//

	//static int timer = 0;

	/*public static void incTimer() {
		timer++;
	}*/
	
	/*public static void resetTimer() {
		timer = 0;
	}*/

	
	/*public static Rectangle setFrame(Rectangle rectangle, Direction direction) {
		switch (direction) {
		case RIGHT:
			switch (timer) {
			case 4:
				rectangle.setFill(Textures.PLAYER_RIGHT.getImagePattern());
				break;
			case 8:
				rectangle.setFill(Textures.PLAYER_RIGHT2.getImagePattern());
				break;
			case 12:
				rectangle.setFill(Textures.PLAYER_RIGHT.getImagePattern());
				break;
			case 16:
				rectangle.setFill(Textures.PLAYER_RIGHT4.getImagePattern());
				break;
			case 17:
				AnimationLoop.resetTimer();
				break;
			default:
				break;
			}
			break;
		case LEFT:
			switch (timer) {
			case 4:
				rectangle.setFill(Textures.PLAYER_LEFT.getImagePattern());
				break;
			case 8:
				rectangle.setFill(Textures.PLAYER_LEFT2.getImagePattern());
				break;
			case 12:
				rectangle.setFill(Textures.PLAYER_LEFT.getImagePattern());
				break;
			case 16:
				rectangle.setFill(Textures.PLAYER_LEFT4.getImagePattern());
				break;
			case 17:
				AnimationLoop.resetTimer();
				break;
			default:
				break;
			}
			break;
		case UP:
			switch (timer) {
			case 4:
				rectangle.setFill(Textures.PLAYER_UP.getImagePattern());
				break;
			case 8:
				rectangle.setFill(Textures.PLAYER_UP2.getImagePattern());
				break;
			case 12:
				rectangle.setFill(Textures.PLAYER_UP.getImagePattern());
				break;
			case 16:
				rectangle.setFill(Textures.PLAYER_UP4.getImagePattern());
				break;
			case 17:
				AnimationLoop.resetTimer();
				break;
			default:
				break;
			}
			break;
		case DOWN:
			switch (timer) {
			case 4:
				rectangle.setFill(Textures.PLAYER_DOWN.getImagePattern());
				break;
			case 8:
				rectangle.setFill(Textures.PLAYER_DOWN2.getImagePattern());
				break;
			case 12:
				rectangle.setFill(Textures.PLAYER_DOWN.getImagePattern());
				break;
			case 16:
				rectangle.setFill(Textures.PLAYER_DOWN4.getImagePattern());
				break;
			case 17:
				AnimationLoop.resetTimer();
				break;
			default:
				break;
			}
			break;
		default:
			break;
		}
		return rectangle;
	}*/
	
	
	
}
