package ryleh.view;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public enum Textures {
	PLAYER_DOWN("assets/texture/player/PlayerDown.png"),
	PLAYER_DOWN2("assets/texture/player/PlayerDown2.png"),
	PLAYER_DOWN4("assets/texture/player/PlayerDown4.png"),
	
	PLAYER_UP("assets/texture/player/PlayerUp.png"),
	PLAYER_UP2("assets/texture/player/PlayerUp2.png"),
	PLAYER_UP4("assets/texture/player/PlayerUp4.png"),
	
	PLAYER_RIGHT("assets/texture/player/PlayerRight.png"),
	PLAYER_RIGHT2("assets/texture/player/PlayerRight2.png"),
	PLAYER_RIGHT4("assets/texture/player/PlayerRight4.png"),
	
	PLAYER_LEFT("assets/texture/player/PlayerLeft.png"),
	PLAYER_LEFT2("assets/texture/player/PlayerLeft2.png"),
	PLAYER_LEFT4("assets/texture/player/PlayerLeft4.png"),
	
	FIRE1("assets/texture/obstacles/Fire1.png"),
	FIRE2("assets/texture/obstacles/Fire2.png"),
	
	ROCK("assets/texture/obstacles/rock.png"),
	ROCK2("assets/texture/obstacles/rock2.png"),
	
	DOOR1("assets/texture/levels/Door1.png"),
	DOOR2("assets/texture/levels/Door2.png"),
	DOOR3("assets/texture/levels/Door3.png"),
	DOOR4("assets/texture/levels/Door4.png"),
	DOOR5("assets/texture/levels/Door5.png"),
	
	BACKGROUND("assets/texture/levels/background.png"),
	
	ITEM1("assets/texture/items/Chest1.png"),
	ITEM2("assets/texture/items/Chest2.png"),
	ITEM3("assets/texture/items/Chest3.png"),
	
	ENEMY_DRUNK("assets/texture/enemies/Enemy1.png"),
	ENEMY_SHOOTER("assets/texture/enemies/Enemy2.png"),
	ENEMY_LURKER("assets/texture/enemies/Enemy3.png"),
	ENEMY_SPINNER("assets/texture/enemies/Enemy4.png"),
	ENEMY_DRUNKSPINNER("assets/texture/enemies/Enemy5.png");

	String texture;
	
	Textures(String string) {
		this.texture = string;
	}
	
	public ImagePattern getImagePattern() {
		return new ImagePattern(new Image(this.texture));
	}
	public ImagePattern getScaledTexture() {
		return null;
	}
}
