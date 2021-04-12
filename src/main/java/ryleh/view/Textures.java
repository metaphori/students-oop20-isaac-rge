package ryleh.view;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import ryleh.common.Config;

public enum Textures {
	PLAYER_DOWN("assets/texture/player/PlayerDown.png", 120, 120),
	PLAYER_DOWN2("assets/texture/player/PlayerDown2.png", 120, 120),
	PLAYER_DOWN4("assets/texture/player/PlayerDown4.png", 120, 120),
	
	PLAYER_UP("assets/texture/player/PlayerUp.png", 120, 120),
	PLAYER_UP2("assets/texture/player/PlayerUp2.png", 120, 120),
	PLAYER_UP4("assets/texture/player/PlayerUp4.png", 120, 120),
	
	PLAYER_RIGHT("assets/texture/player/PlayerRight.png", 120, 120),
	PLAYER_RIGHT2("assets/texture/player/PlayerRight2.png", 120, 120),
	PLAYER_RIGHT4("assets/texture/player/PlayerRight4.png", 120, 120),
	
	PLAYER_LEFT("assets/texture/player/PlayerLeft.png", 120, 120),
	PLAYER_LEFT2("assets/texture/player/PlayerLeft2.png", 120, 120),
	PLAYER_LEFT4("assets/texture/player/PlayerLeft4.png", 120, 120),
	
	FIRE1("assets/texture/obstacles/Fire1.png", 140, 140),
	FIRE2("assets/texture/obstacles/Fire2.png", 140, 140),
	
	ROCK("assets/texture/obstacles/rock.png", 145, 145),
	ROCK2("assets/texture/obstacles/rock2.png", 145, 145),
	
	DOOR1("assets/texture/levels/Door1.png", 190, 190),
	DOOR2("assets/texture/levels/Door2.png", 190, 190),
	DOOR3("assets/texture/levels/Door3.png", 190, 190),
	DOOR4("assets/texture/levels/Door4.png", 190, 190),
	DOOR5("assets/texture/levels/Door5.png", 190, 190),
	
	BACKGROUND("assets/texture/levels/background.png", 1920, 1080),
	
	ITEM1("assets/texture/items/Chest1.png", 145, 145),
	ITEM2("assets/texture/items/Chest2.png", 145, 145),
	ITEM3("assets/texture/items/Chest3.png", 145, 145),
	
	ENEMY_DRUNK("assets/texture/enemies/Enemy1.png", 100, 100),
	ENEMY_SHOOTER("assets/texture/enemies/Enemy2.png", 100, 100),
	ENEMY_LURKER("assets/texture/enemies/Enemy3.png", 100, 100),
	ENEMY_SPINNER("assets/texture/enemies/Enemy4.png", 100, 100),
	ENEMY_DRUNKSPINNER("assets/texture/enemies/Enemy5.png", 100, 100),
	
	BULLET("assets/texture/enemies/Bullet.png",40, 40);

	String texture;
	private int width;
	private int height;
	
	Textures(String string, int width, int height) {
		this.texture = string;
		this.width = width;
		this.height = height;
	}
	
	public ImagePattern getImagePattern() {
		return new ImagePattern(new Image(this.texture));
	}

	public int getWidth() {
		return (int) (this.width * Config.SCALE_MODIFIER);
	}
	
	public int getHeight() {
		return (int) (this.height * Config.SCALE_MODIFIER);
	}
}
