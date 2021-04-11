package ryleh.view.enemies;

import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import ryleh.common.Config;
import ryleh.common.GameMath;
import ryleh.common.P2d;
import ryleh.common.V2d;
import ryleh.controller.Entity;
import ryleh.model.GameObject;
import ryleh.view.GraphicComponent;
import ryleh.view.Textures;

public class EnemyLurkerGraphicComponent implements GraphicComponent{

	private Rectangle rectangle;
	private long adjustDirectionTimer = System.currentTimeMillis();
    private long adjustDelay = 500;
	private P2d playerDirection;
	private Rotate rotation = new Rotate();
	private V2d velocity;
	private int moveSpeed=50;
	private GameObject player;
	private int width;
	private int height;
	
	public EnemyLurkerGraphicComponent(final GameObject player) {
		this.height = Textures.ENEMY_LURKER.getHeight();
		this.width = Textures.ENEMY_LURKER.getWidth();
		this.rectangle = new Rectangle(width, height);
		this.rectangle.setFill(Textures.ENEMY_LURKER.getImagePattern());
		this.player=player;
		this.velocity=new V2d(0,0);
	}

	private void updateImage() {

	}

	@Override
	public void render(final Point2D position, final double deltaTime) {
		rectangle.setX(position.getX()-rectangle.getWidth()/2);
		rectangle.setY(position.getY()-rectangle.getHeight()/2);
		if (System.currentTimeMillis() - adjustDirectionTimer >= adjustDelay) {
			V2d directionToPlayer = new V2d(this.player.getPosition(),new P2d(position.getX()-rectangle.getWidth()/2,position.getY()-rectangle.getHeight()/2))
					.getNormalized()
					.mul(moveSpeed);
//			rotation.setAngle(GameMath.toDegrees((Math.atan(directionToPlayer.y/ directionToPlayer.x))));
//			rotation.setPivotX(position.getX());
//	  		rotation.setPivotY(position.getY());
//			rectangle.getTransforms().add(rotation);
			rectangle.setRotate(GameMath.toDegrees((Math.atan(directionToPlayer.y/ directionToPlayer.x))));
    		adjustDirectionTimer = System.currentTimeMillis();
    	}
//		V2d directionToPlayer = new V2d(this.player.getPosition(),new P2d(position.getX()-rectangle.getWidth()/2,position.getY()-rectangle.getHeight()/2))
//					.getNormalized()
//					.mul(moveSpeed);
//		System.out.println(directionToPlayer.toString());
//		rotation.setAngle(GameMath.toDegrees((Math.atan(directionToPlayer.y/ directionToPlayer.x))));
//        rotation.setPivotX(position.getX());
//  		rotation.setPivotY(position.getY());
//     	rectangle.getTransforms().add(rotation);
		this.updateImage();
	}

	@Override
	public void onAdded(final Scene scene) {
		Parent root = scene.getRoot();
        ((AnchorPane) root).getChildren().add(rectangle);
	}
	
	@Override
	public Object getNode() {
		return rectangle;
	}
}
