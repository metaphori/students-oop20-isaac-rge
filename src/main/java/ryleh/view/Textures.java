package ryleh.view;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public enum Textures {
	PLAYER_DOWN("assets/texture/player/PlayerDown.png"),
	PLAYER_UP("assets/texture/player/PlayerUp.png"),
	PLAYER_RIGHT("assets/texture/player/PlayerRight.png"),
	PLAYER_LEFT("assets/texture/player/PlayerLeft.png"),
	FIRE("assets/texture/obstacles/fire.png"),
	ROCK("assets/texture/obstacles/rock.png"),
	ROCK2("assets/texture/obstacles/rock2.png"),
	DOOR("assets/texture/levels/door.png"), // da fixare, animazoine non valida
	BACKGROUND("assets/texture/levels/background.png"),
	ITEMS("assets/texture/items/chest.png"), // da fixare, animazione non valida
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
